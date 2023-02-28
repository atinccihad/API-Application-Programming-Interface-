package com30.i12;

import com30.baseUrls.RestfulBookerHerokuappBaseUrl;
import com30.pojos.JsonPlaceholder_BookingDatesPojo;
import com30.pojos.JsonPlaceholder_BookingPojo;
import com30.pojos.JsonPlaceholder_BookingResponsePojo;
import com30.testData.RestfulBookerHerokuappTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestWithPojo_02 extends RestfulBookerHerokuappBaseUrl {
    /*
     https://restful-booker.herokuapp.com/booking
     URL'ine  bir request olarak
      {
        "firstname": "cihad",
        "lastname": "atinc",
        "totalprice": 123,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2021-01-05",
            "checkout": "2021-01-10"
        }
     status code is 200,
     response body'nin
     {
    "bookingid": 4491,
    "booking": {
        "firstname": "cihad",
        "lastname": "atinc",
        "totalprice": 123,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2021-01-05",
            "checkout": "2021-01-10"
          }
       }
    }
     seklinde oldugunu test edin.
    */
    @Test
    public void test() {
        // url
        specRestful.pathParam("first", "booking");

        JsonPlaceholder_BookingDatesPojo jsonPlaceholderBookingDatesPojo = new JsonPlaceholder_BookingDatesPojo("2020-09-09", "2020-09-21");
        System.out.println("jsonPlaceholderBookingDatesPojo = " + jsonPlaceholderBookingDatesPojo);

        JsonPlaceholder_BookingPojo jsonPlaceholder_bookingPojo =
                new JsonPlaceholder_BookingPojo("Selim", "Ak", 15000, true, jsonPlaceholderBookingDatesPojo);
        System.out.println("jsonPlaceholder_bookingPojo = " + jsonPlaceholder_bookingPojo);

        // request gonder
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specRestful)
                .auth().basic("admin", "password123")
                .body(jsonPlaceholder_bookingPojo)
                .when()
                .post("/{first}");
        response.prettyPrint();

        JsonPlaceholder_BookingResponsePojo actualData = response.as(JsonPlaceholder_BookingResponsePojo.class);
        // status code is 200
        assertEquals(200, response.getStatusCode());
        // "firstname": "cihad"
        assertEquals(jsonPlaceholder_bookingPojo.getFirstname(), actualData.getBooking().getFirstname());
        // "lastname": "atinc"
        assertEquals(jsonPlaceholder_bookingPojo.getLastname(), actualData.getBooking().getLastname());
        // "totalprice": 123
        assertEquals(jsonPlaceholder_bookingPojo.getTotalprice(), actualData.getBooking().getTotalprice());
        // "depositpaid": false
        assertEquals(jsonPlaceholder_bookingPojo.isDepositpaid(), actualData.getBooking().isDepositpaid());
        // "checkin": "2021-01-05"
        assertEquals(jsonPlaceholder_bookingPojo.getBookingDatesPojo().getCheckin(), actualData.getBooking().getBookingDatesPojo().getCheckin());
        // "checkout": "2021-01-10"
        assertEquals(jsonPlaceholder_bookingPojo.getBookingDatesPojo().getCheckout(), actualData.getBooking().getBookingDatesPojo().getCheckout());
    }
}
