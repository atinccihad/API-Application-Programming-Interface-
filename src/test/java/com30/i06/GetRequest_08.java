package com30.i06;

import com30.testBase.RestfulHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class GetRequest_08 extends RestfulHerokuapp {
    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine,
    content type'i "application/json" olan get request'i yolladigimda
    status code : 200
    content-type : "application/json"
    response body'sinin asagidaki gibi oldugunu test edin
    {
    "firstname": "Susan",
    "lastname": "Jackson",
    "totalprice": 851,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-02-20",
        "checkout": "2022-12-30"
     },
    "additionalneeds": "Breakfast"
    }
    */

    @Test
    public void test() {
        specRestful.pathParams("first", "booking",
                          "second", 3);
        Response response = given()
                .accept("application/json")
                .spec(specRestful)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Mary", jsonPath.getString("firstname"));
        assertEquals("Ericsson", jsonPath.getString("lastname"));
        assertEquals(695, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2019-02-28", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2022-09-24", jsonPath.getString("bookingdates.checkout"));

        // Matchers
        response.then().assertThat().statusCode(200).contentType("application/json")
                .body("firstname",equalTo("Mary"),
                        "lastname",equalTo("Ericsson"),
                                             "totalprice",equalTo(695),
                                             "depositpaid",equalTo(true),
                                             "bookingdates.checkin",equalTo("2019-02-28"),
                                             "bookingdates.checkout",equalTo("2022-09-24"));
    }
}
