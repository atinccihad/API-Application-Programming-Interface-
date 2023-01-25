package com81.get_request;

import io.restassured.response.*;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04c {

    /*
    https://restful-booker.herokuapp.com/booking/5/
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen responce'un
    Status code : 200
    ve content type'inin "application/json"
    ve firstname'in "Jim"
    ve totalprice'in 600
    ve checkin date'in 2018-01-01 oldugunu test edin.
     */

    @Test
    public void test04c() {
        String url = "https://restful-booker.herokuapp.com/booking/5/";
        Response response = given()
                .accept("application/json")
                .when()
                .get(url);
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("James"))
                .body("totalprice", equalTo(111))
                .body("bookingdates.checkin", equalTo("2018-01-01"));
    }
}
