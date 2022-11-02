package get_request;

import java.util.*;

import base_urls.*;
import io.restassured.response.*;
import org.junit.*;
import test_data.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get10 extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
            {
        "meta": null,
        "data": {
           "meta": null,
     "data": {
     "id": 2986,
     "name": "Kanaka Jain",
     "email": "kanaka_jain@stark.net",
     "gender": "male",
     "status": "active"

              }
         }
     */

    @Test
    public void get10() {
        spec.pathParams("first", "users", "second", 2986);

        GoRestTestData obj = new GoRestTestData();
        Map<String, String> dataKeyMap = obj.dataKeyMap("Kanaka Jain", "kanaka_jain@stark.net", "male", "active");
        Map<String, Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map) actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) actualData.get("data")).get("status"));
        assertEquals(200, response.statusCode());
    }
}