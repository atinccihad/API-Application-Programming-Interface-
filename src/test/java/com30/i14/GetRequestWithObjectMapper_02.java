package com30.i14;

import com30.baseUrls.RestfulBookerHerokuappBaseUrl;
import com30.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithObjectMapper_02 extends RestfulBookerHerokuappBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking/2  url'ine get request gonderildiginde
    donen response'un
    status code = 200
    {
    "firstname": "Mark",
    "lastname": "Wilson",
    "totalprice": 845,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2020-12-03",
        "checkout": "2022-06-21"
    },
    "additionalneeds": "Breakfast"
    }
    oldugunu Object Mapper kullanarak test ediniz.
     */
    @Test
    public void test() {
        specRestful.pathParams("first", "booking",
                "second", 2);

        String jsonData = "" +
                "{\n" +
                "    \"firstname\": \"Mark\",\n" +
                "    \"lastname\": \"Wilson\",\n" +
                "    \"totalprice\": 845,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": " +
                "    {\n" +
                "        \"checkin\": \"2020-12-03\",\n" +
                "        \"checkout\": \"2022-06-21\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specRestful)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        //       "firstname": "Jim"
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        //       "lastname": "Ericsson"
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        //       "totalprice": 301,
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        //       "depositpaid": true,
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        //       "checkin": "2018-05-14",
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"),
                ((Map) actualData.get("bookingdates")).get("checkin"));
        //       "checkout": "2019-02-23"
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"),
                ((Map) actualData.get("bookingdates")).get("checkout"));
        //       "additionalneeds": "Breakfast"
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

    }
}
