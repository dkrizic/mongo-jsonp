package com.prodyna.mongo.jsonp;

import com.mongodb.BasicDBList;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonArray;

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
    public void testSimpleArray() {
        BasicDBList list = new BasicDBList();
        list.add( "1" );
        list.add( "2" );
        list.add( "3" );
        JsonArray ja = new MPConverter().convert( list );
        System.out.println( ja.toString() );
        assertEquals(3, ja.size());
    }

}
