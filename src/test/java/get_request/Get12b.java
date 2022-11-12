package get_request;


import base_urls.HerokuAppBaseUrl;
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

    "firstname" : "Edgar",
    "lastname" : "Liu",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
    }
    gibi oldugunu test edin.
     */

    @Test
    public void test12b() {
        // Set the Url
        spec.pathParams("parametre1","booking","parametre2",23);

        // Get the ExpectedData
        HerokuappTestData expectedObje = new HerokuappTestData();
        HashMap<String,Object>expectedDataMap = expectedObje.setupTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // Send the request get the response
        Response response = given().accept("application/json").spec(spec).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        HashMap <String,Object>actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        // Do assertion
        assertEquals("firstname'ler farkli!",expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals("lastname'ler farkli!",expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals("totalprice'ler farkli!",expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals("additionalneeds'ler farkli!",expectedDataMap.get("additionalneeds"),actualDataMap.get("additionalneeds"));

        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }
}
