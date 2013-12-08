package com.prodyna.mongo.jsonp;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Iterator;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: dkrizic
 * Date: 07.12.13
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
@Named
public class MPConverter {

    public DBObject convert(JsonObject jsonObject) {
        return null;
    }

    public JsonObject convert(DBObject dbObject) {
        final JsonObjectBuilder job = createObjectBuilder();
        for (String key : dbObject.keySet()) {
            final Object value = dbObject.get(key);
            if( value instanceof String ) {
                job.add( key, (String) value );
            } else if( value instanceof ObjectId ) {
                ObjectId id = (ObjectId) value;
                JsonObject obj = createObjectBuilder().add("$oid", id.toString() ).build();
                job.add( key, obj ) ;
            } else if( value instanceof BasicDBList ) {
                job.add( key, convert( (BasicDBList) value ) );
            } else if( value instanceof DBObject ) {
                job.add( key, convert( (DBObject) value ) );
            } else {
                throw new IllegalStateException("Unexpected value type " + value.getClass().getName() + " for DBObject found");
            }
        }
        return job.build();
    }

    public JsonArray convert(BasicDBList dbList) {
        final JsonArrayBuilder jab = createArrayBuilder();
        Iterator<Object> i = dbList.iterator();
        while (i.hasNext()) {
            Object value = i.next();
            if( value instanceof String ) {
                jab.add( (String) value );
            } else if( value instanceof BasicDBList ) {
                jab.add( convert( (BasicDBList) value ) );
            } else if( value instanceof DBObject ) {
                jab.add( convert( (DBObject) value ) );
            } else {
                throw new IllegalStateException("Unexpected value type " + value.getClass().getName() + " for DBList found");
            }
        }
        return jab.build();
    }
}
