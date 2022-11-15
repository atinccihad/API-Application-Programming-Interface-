package get_request;


import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12b extends HerokuAppBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking/23  url'ine istek gonderildiginde,
    Donen response'un

    {
    "firstname": "D1AC2",
    "lastname": "211DA",
    "totalprice": 12,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-11-15",
        "checkout": "2022-11-22"
    },
    "additionalneeds": "D2B3D"
}
actualDataMap = {firstname=D1AC2, additionalneeds=D2B3D, bookingdates={checkin=2022-11-15, checkout=2022-11-22}, totalprice=12, depositpaid=true, lastname=211DA}
    gibi oldugunu test edin.
     */

    @Test
    public void test12b() {
        // Set the Url
        spec.pathParams("parametre1", "booking", "parametre2", 23);

        HerokuappTestData expectedObje = new HerokuappTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setupTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // Send the request get the response
        // *** 1. yol de serialization ***
        Response response = given().accept("application/json").spec(spec).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        // Do assertion
        assertEquals("statusCode !", 200, response.getStatusCode());

        assertEquals("firstname'ler farkli!", expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals("lastname'ler farkli!", expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals("totalprice'ler farkli!", expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals("additionalneeds'ler farkli!", expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));

        assertEquals("bookingdates bolumundeki checkin degeri farkli!", ((Map) expectedDataMap.get("bookingdates")).get("checkin"), ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals("bookingdates bolumundeki checkout degeri farkli!", ((Map) expectedDataMap.get("bookingdates")).get("checkout"), ((Map) actualDataMap.get("bookingdates")).get("checkout"));

        // *** 2. yol JsonPath ***
        JsonPath jsonPath = response.jsonPath();
        assertEquals("firstname'ler farkli!", expectedDataMap.get("firstname"), jsonPath.getString("firstname"));
        assertEquals("lastname'ler farkli!", expectedDataMap.get("lastname"), jsonPath.getString("lastname"));
        assertEquals("totalprice'ler farkli!", expectedDataMap.get("totalprice"), jsonPath.getInt("totalprice"));
        assertEquals("depositpaid'ler farkli!", expectedDataMap.get("depositpaid"), jsonPath.getBoolean("depositpaid"));
        assertEquals("bookingdates bolumundeki checkin degeri farkli!", ((Map) expectedDataMap.get("bookingdates")).get("checkin"), (jsonPath.getString("bookingdates.checkin")));
        assertEquals("bookingdates bolumundeki checkout degeri farkli!", ((Map) expectedDataMap.get("bookingdates")).get("checkout"), (jsonPath.getString("bookingdates.checkout")));

    }
}
