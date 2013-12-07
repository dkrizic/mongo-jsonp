package com.prodyna.mongo.jsonp;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: dkrizic
 * Date: 07.12.13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class MPConverterTest {

    @Test
    public void testArrayFromMongo() {
        BasicDBList sub = new BasicDBList();
        sub.add("10");
        sub.add("20");

        BasicDBList list = new BasicDBList();
        list.add( "1" );
        list.add( "2" );
        list.add( sub );
        list.add( new BasicDBObject("a","1"));
        JsonArray ja = new MPConverter().convert( list );
        System.out.println( ja.toString() );
        assertEquals(4, ja.size());
    }

    @Test
    public void testObjectFromMongo() {
        BasicDBList sub = new BasicDBList();
        sub.add("10");
        sub.add("20");

        BasicDBObject object = new BasicDBObject();
        object.put("a","1");
        object.put("b","2");
        object.put("sub", sub );
        object.put("c", new BasicDBObject("d","42"));
        JsonObject jo = new MPConverter().convert( object );
        System.out.println( jo.toString() );
        assertEquals(4, jo.size() );
        assertEquals( "1", jo.getString("a") );
        assertEquals( 2, jo.getJsonArray("sub" ).size() );
        // assertEquals( new String("20"), jo.getJsonArray("sub").get(1) );
    }

}
