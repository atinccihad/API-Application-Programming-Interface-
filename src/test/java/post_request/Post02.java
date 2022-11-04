package post_request;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfullTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
    /*
     Given
         1) https://restful-booker.herokuapp.com/booking
         2) {
              "firstname": "John",
              "lastname": "Doe",
              "totalprice": 11111,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2021-09-09",
                  "checkout": "2021-09-21"
               }
            }
     When
         I send POST Request to the Url
     Then
         Status code is 200
         And response body should be like {
                                             "bookingid": 5315,
                                             "booking": {
                                                 "firstname": "John",
                                                 "lastname": "Doe",
                                                 "totalprice": 11111,
                                                 "depositpaid": true,
                                                 "bookingdates": {
                                                     "checkin": "2021-09-09",
                                                     "checkout": "2021-09-21"
                                                 }
                                             }
                                          }
*/

    @Test
    public void post02() {
        // Set the Url
        spec.pathParams("first", "booking");

        // Set the expected Data
        RestfullTestData obj = new RestfullTestData();
        Map<String, String> bookingdatesMap = obj.bookingdatesMethod("2021-09-09", "2021-09-21");
        Map<String, Object> expectedData = obj.expectedDataMethod("John", "Doe", 11111, true, bookingdatesMap);

        // Send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); // De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals("firstname beklenenden farkli!", expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals("lastname beklenenden farkli!", expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals("totalprice beklenenden farkli!", expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals("depositpaid beklenenden farkli!", expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));

        assertEquals("checkin beklenenden farkli!", bookingdatesMap.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals("checkout beklenenden farkli!", bookingdatesMap.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
    }
}
