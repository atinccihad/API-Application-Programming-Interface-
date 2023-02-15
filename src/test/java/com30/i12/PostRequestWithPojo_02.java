package com30.i12;

import com30.baseUrls.RestfulBookerHerokuappBaseUrl;
import com30.testData.RestfulBookerHerokuappTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
        // requestBody ve expected Data ayni oldugu icin tek bir JSONObject kullanilmasi yeterlidir.
        RestfulBookerHerokuappTestData testData = new RestfulBookerHerokuappTestData();
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println("expectedRequestData = " + expectedRequestData);
        // request gonder
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specRestful)
                .auth().basic("admin", "password123")
                .body(expectedRequestData.toString())
                .when()
                .post("/{first}");
        response.prettyPrint();
    }
}
