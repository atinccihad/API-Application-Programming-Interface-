package com30.i05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest_03 {
    /*
     https://restful-booker.herokuapp.com/booking/7 url'ine,
     accept type'i "application-json" olan get request'i yolladigimda gelen response'un
     status code : 200
     content-type : "application/json"

     ve firstname'in "Jim"
     ve lastname'in "Jackson"
     ve checkin date'in "2017-07-30"
     ve checkout date'in "2022-05-17"
     ve additionalneeds'in "Breakfast"
     oldugunu test edin.
     */

    @Test
    public void test() {

        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response = given()
                .accept("application/json")
                .when()
                .get(url);
        response.prettyPrint();
        // ****** level 1 ******
        /*
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("Mary"))
                .body("lastname", equalTo("Ericsson"))
                .body("totalprice", equalTo(277))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2016-08-05"))
                .body("bookingdates.checkout", equalTo("2017-03-28"));
         */

        // ****** level 2 ******
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Jim"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", equalTo(575),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2017-07-30"),
                        "bookingdates.checkout", equalTo("2022-05-17"),
                        "additionalneeds",equalTo("Breakfast"));
    }
}
