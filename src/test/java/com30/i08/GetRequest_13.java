package com30.i08;

import com30.testBase.RestfulBookerHerokuappTestBase;
import com30.testData.RestfulBookerHerokuappTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

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
     seklinde oldugunu test edin.
     */
    @Test
    public void test() {
        // url olustur
        specRestful.pathParams("first", "booking",
                "second", 1);
        // expectedData olustur
        RestfulBookerHerokuappTestData expectedObje = new RestfulBookerHerokuappTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // request gonder
        Response response = given()
                .accept("application/json")
                .spec(specRestful)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // 1. yol deserialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDataMap.get("firstname"), expectedDataMap.get("firstname"));
        softAssert.assertEquals(actualDataMap.get("lastname"), expectedDataMap.get("lastname"));
        softAssert.assertEquals(actualDataMap.get("totalprice"), expectedDataMap.get("totalprice"));
        softAssert.assertEquals(actualDataMap.get("depositpaid"), expectedDataMap.get("depositpaid"));
        softAssert.assertEquals(((Map) actualDataMap.get("bookingdates")).get("checkin"),
                ((Map) expectedDataMap.get("bookingdates")).get("checkin"));
        softAssert.assertEquals(((Map) actualDataMap.get("bookingdates")).get("checkout"),
                ((Map) expectedDataMap.get("bookingdates")).get("checkout"));
        softAssert.assertAll();

        // 2. yol JsonPath
        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedDataMap.get("firstname"), jsonPath.getString("firstname"));
        assertEquals(expectedDataMap.get("lastname"), jsonPath.getString("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), jsonPath.getInt("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), jsonPath.getBoolean("depositpaid"));
        assertEquals(((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
        assertEquals(((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                jsonPath.getString("bookingdates.checkout"));

    }
}
