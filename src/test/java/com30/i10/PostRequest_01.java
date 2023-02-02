package com30.i10;

import com30.testBase.DummyResttapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest_01 extends DummyResttapiexampleTestBase {
      /*
       https://dummy.restapiexample.com/api/v1/employees url'ine post request olarak,
       {
      "name":"atinc",
      "salary":"55",
      "age":"35",
      "profile_image":""
      }
       seklinde oldugunu test edin.
        */
    @Test
    public void test() {
        specDummy.pathParam("first", "create");

        DummyRestApiTestData obje = new DummyRestApiTestData();
        /*
         POST request yaparken body gondermek zorundayiz
         Testdata class'inda olusturdugumuz request body'i burada cagiriyoruz
       */
        HashMap<String, String> requestBodyMap = obje.setUpRequestBody();
        HashMap<String, Object> expectedDataMap = obje.setUpExpectedData();

        Response response = given()
                .accept(ContentType.JSON)
                .spec(specDummy)
                .auth()
                .basic("admin", "password123")
                .body(requestBodyMap)
                .when()
                .post("/{first}");
        response.prettyPrint();

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        // JsonPath
        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedDataMap.get("status"), jsonPath.getString("status"));
        assertEquals(expectedDataMap.get("message"), jsonPath.getString("message"));

    }
}
