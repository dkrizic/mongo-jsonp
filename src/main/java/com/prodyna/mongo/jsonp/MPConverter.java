package com.prodyna.mongo.jsonp;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

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
            System.out.println("Array element is " + value);
        }
        return job.build();
    }

    public JsonArray convert(BasicDBList dbList) {
        final JsonArrayBuilder jab = createArrayBuilder();
        Iterator<Object> i = dbList.iterator();
        while (i.hasNext()) {
            Object value = i.next();
            System.out.println("Array element is " + value);
        }
        return jab.build();
    }
}
