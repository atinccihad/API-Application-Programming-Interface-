package com81.post_request;

import com81.base_urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import com81.test_data.DummyRestapiTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01b extends DummyRestapiBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/create  url'ine, Request Body olarak
    {
    "name":"Cihad Atınç",
    "salary":"1000",
    "age":"34",
    "profile_image":"https://github.com/atinccihad"
    }
    gonderildiginde status code : 200 oldugunu
    ve donen response body'nin,
    {
    "status": "success",
    "data": {
        "name": "Cihad Atınç",
        "salary": "1000",
        "age": "34",
        "profile_image": "https://github.com/atinccihad",
        "id": 5202
    },
    "message": "Successfully! Record has been added."
   }
   oldugunu test edin.
     */

    @Test
    public void post01b() {
        // Set the Url
        spec.pathParam("parametre1", "create");

        DummyRestapiTestData obje = new DummyRestapiTestData();
        /*
         post request yaparken body gondermek zorundayiz,
         testdata classında olusturdugumuz request body'i burada cagiriyoruz.
        */
        HashMap<String, String> requestBodyMap = obje.setupRequestBody();
        HashMap<String, Object> expectedDataMap = obje.setupExpectedData();

        Response response = given()
                .accept("application/json")
                .spec(spec).auth().basic("admin", "password123")
                .body(requestBodyMap)
                .when()
                .post("/{parametre1}");

        response.prettyPrint();

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        // JsonPath
        JsonPath json = response.jsonPath();
        assertEquals(expectedDataMap.get("status"), json.getString("status"));
        assertEquals(expectedDataMap.get("message"), json.getString("message"));

    }
}
