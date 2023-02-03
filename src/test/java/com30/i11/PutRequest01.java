package com30.i11;

import com30.testBase.JsonPlaceHolderTestBase;
import com30.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutRequest01 extends JsonPlaceHolderTestBase {
    /*
  https://jsonplaceholder.typicode.com/todos/198 URL'ine asgidaki body gonderildiginde,
  {
  "userId":21,
  "title":"Wash the dishes",
  "completed":false
  }
  Donen response'un Status kodunun 201 ve response body'nin asagidaki gibi oldugunu test edin,
  {
  "userId":21,
  "title":"Wash the dishes",
  "completed":false,
  "id": 198
  }
   */
    @Test
    public void test() {
        specJson.pathParams("first", "todos", "second", 198);

        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPutData();
        System.out.println("expectedRequest = " + expectedRequest);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specJson)
                .auth().basic("admin", "password123")
                .body(expectedRequest.toString())
                .when()
                .put("/{first}/{second}");
        response.prettyPrint();

        // JsonPath
        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedRequest.getInt("userId"), jsonPath.getInt("userId"));
        assertEquals(expectedRequest.getString("title"), jsonPath.getString("title"));
        assertEquals(expectedRequest.getBoolean("completed"), jsonPath.getBoolean("completed"));


    }
}
