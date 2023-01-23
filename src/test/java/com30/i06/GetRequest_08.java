package com30.i06;

import com30.testBase.RestfulHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest_08 extends RestfulHerokuapp {
    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine,
    content type'i "application/json" olan get request'i yolladigimda
    status code : 200
    content-type : "application/json"
    response body'sinin asagidaki gibi oldugunu test edin
    {

    }
    */

    @Test
    public void test() {
        specRestful.pathParams("first","booking",
                          "second",5);
        Response response = given()
                .accept("application/json")
                .spec(specRestful)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Sally",jsonPath.getString("firstname"));
        assertEquals("Smith",jsonPath.getString("lastname"));
        assertEquals(789,jsonPath.getInt("totalprice"));
        assertEquals(false,jsonPath.getBoolean("depositpaid"));
        assertEquals("2017-12-11",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2020-02-20",jsonPath.getString("bookingdates.checkout"));

    }
}
