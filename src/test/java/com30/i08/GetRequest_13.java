package com30.i08;

import com30.testBase.RestfulBookerHerokuappTestBase;
import com30.testData.RestfulBookerHerokuappTestData;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest_13 extends RestfulBookerHerokuappTestBase {
    /*
    https://restful-booker.herokuapp.com/booking/1 url'ine bir istek gonderildiginde,
    status code : 200,
    gelen body'de,

    {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 929,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2015-08-10",
        "checkout": "2016-03-22"
                     },
    "additionalneeds": "Breakfast"
     }
     oldugunu test edin.
     */

    @Test
    public void test() {
        // url olustur
        specRestful.pathParams("first","booking",
                "second",1);
        // expectedData olustur
        RestfulBookerHerokuappTestData expectedObje = new RestfulBookerHerokuappTestData();
        HashMap<String,Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // request gonder
        Response response = given()
                .accept("application/json")
                .spec(specRestful)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();
        
        // deserialization
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                     ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                     ((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
