package com30.i12;

import com30.baseUrls.JsonPlaceHolderBaseUrl;
import com30.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PatchRequest_01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL'ine asagidaki body patch request olarak gonderildiginde,
    {

    "title":"API working.."

    }
    Donen response'un Status kodunun 200 ve response body'nin asagidaki gibi oldugunu test edin,
    {
    "userId":21,
    "title":"API working..",
    "completed":false,
    "id": 198
    }
     */
    @Test
    public void test() {
        // url
        specJson.pathParams("first", "todos", "second", 198);
        // expected ve request data olustur
        JsonPlaceHolderTestData testData = new JsonPlaceHolderTestData();
        JSONObject requestData = testData.setupPatchRequestData();
        System.out.println("requestData = " + requestData);
        JSONObject expectedData = testData.setupPatchExpectedData();
        System.out.println("expectedData = " + expectedData);

        // send the request
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specJson)
                .auth().basic("admin", "password123")
                .body(requestData.toString())
                .when()
                .patch("/{first}/{second}");
        response.prettyPrint();

        // do assertion

        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        assertEquals(expectedData.getInt("userId"),
                jsonPath.getInt("userId"));
        assertEquals(expectedData.getString("title"),
                jsonPath.getString("title"));
        assertEquals(expectedData.getBoolean("completed"),
                jsonPath.getBoolean("completed"));

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getInt("userId"), (actualDataMap.get("userId")));
        assertEquals(expectedData.getString("title"), (actualDataMap.get("title")));
        assertEquals(expectedData.getInt("id"), (actualDataMap.get("id")));
        assertEquals(expectedData.getBoolean("completed"), (actualDataMap.get("completed")));

        // Matchers class
        response.then().assertThat().statusCode(200).body(
                                "userId", equalTo(10),
                "id", equalTo(198),
                                     "title", equalTo("API working.."),
                                     "completed", equalTo(true)
        );

    }
}
