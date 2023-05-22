package com30.i10;

import com30.baseUrls.RestfulBookerHerokuappBaseUrl;
import com30.testData.RestfulBookerHerokuappTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest_02 extends RestfulBookerHerokuappBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking adresine request
    gonderildiginde, status kodun 200 oldugunu ve donen response body'nin,
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

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedRequestData.getString("firstname")
                , ((Map) actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedRequestData.getString("lastname")
                , ((Map) actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedRequestData.getInt("totalprice")
                , ((Map) actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedRequestData.getBoolean("depositpaid")
                , ((Map) actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        System.out.println("----------------------- JsonPath object ----------------------------");
        jsonPath.prettyPrint();

        assertEquals(expectedRequestData.getString("lastname")
                , jsonPath.getString("booking.lastname"));
        assertEquals(expectedRequestData.getString("firstname")
                , jsonPath.getString("booking.firstname"));
        assertEquals(expectedRequestData.getInt("totalprice")
                , jsonPath.getInt("booking.totalprice"));
        assertEquals(expectedRequestData.getBoolean("depositpaid")
                , jsonPath.getBoolean("booking.depositpaid"));

        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin")
                , jsonPath.getString("booking.bookingdates.checkin"));
        assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout")
                , jsonPath.getString("booking.bookingdates.checkout"));
    }
}
