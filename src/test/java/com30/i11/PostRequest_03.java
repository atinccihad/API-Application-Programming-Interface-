package com30.i11;

import com30.testBase.JsonPlaceHolderTestBase;
import com30.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;

public class PostRequest_03 extends JsonPlaceHolderTestBase {
    /*
    https://jsonplaceholder.typicode.com/todos URL'ine asgidaki body gonderildiginde,
    statusCode : 201
    {
    "userId":55,
    "title":"Tidy your room",
    "completed":false
    }
    Donen response'un Status kodunun 201 ve response body'nin asagidaki gibi oldugunu test edin,
    {
    "userId":55,
    "title":"Tidy your room",
    "completed":false,
    "id":...
    }
     */
    @Test
    public void test() {
        specJson.pathParam("first", "todos");

        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setupTestPostData();
        System.out.println("expectedRequest = " + expectedRequest);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specJson)
                .auth().basic("admin", "password123")
                .body(expectedRequest.toString())
                .when()
                .post("/{first}");
        response.prettyPrint();

        // Matchers class
        response
                .then()
                .assertThat()
                .statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                        "userId", equalTo(expectedRequest.getInt("userId")),
                        "title", equalTo(expectedRequest.getString("title")));
        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        assertEquals(expectedRequest.getInt("statusCode"),
                response.getStatusCode());
        assertEquals(expectedRequest.getInt("userId"),
                jsonPath.getInt("userId"));
        assertEquals(expectedRequest.getString("title"),
                jsonPath.getString("title"));
        assertEquals(expectedRequest.getBoolean("completed"),
                jsonPath.getBoolean("completed"));

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedRequest.getInt("statusCode")
                , (actualDataMap.get("statusCode")));
        assertEquals(expectedRequest.getInt("userId")
                , (actualDataMap.get("userId")));
        assertEquals(expectedRequest.getString("title")
                , (actualDataMap.get("title")));
        assertEquals(expectedRequest.getBoolean("completed"),
                (actualDataMap.get("completed")));
    }
}
