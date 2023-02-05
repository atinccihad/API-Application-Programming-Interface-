package com30.i12;

import com30.pojos.Jsonplaceholder_TodosPojo;
import com30.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestWithPojo_01 extends JsonPlaceHolderTestBase {
    /*
     https://jsonplaceholder.typicode.com/todos URL'ine  bir request olarak gonderildiginde,
     request body,
     {
     "userId":21,
     "title":"Tidy your room",
     "completed":false,
     "id": 201
     }
     Donen response'un Status kodunun 201
     ve response body'nin asagidaki gibi oldugunu test edin,
     {
     "userId":21,
     "id": 201,
     "title":"Tidy your room",
     "completed":false
     }
      */
    @Test
    public void test() {
        specJson.pathParam("first", "todos");

        Jsonplaceholder_TodosPojo requestExpected = new Jsonplaceholder_TodosPojo(21, 201, "Tidy your room", false);
        System.out.println("requestExpected = " + requestExpected);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specJson)
                .auth().basic("admin", "password123")
                .body(requestExpected)
                .when()
                .post("/{first}");
        response.prettyPrint();

        // De Serialization
        Jsonplaceholder_TodosPojo actualData = response.as(Jsonplaceholder_TodosPojo.class);
        System.out.println("actualData = " + actualData);

        // do assertion
        assertEquals(201, response.getStatusCode());
        assertEquals(requestExpected.getUserId(), actualData.getUserId());
        assertEquals(requestExpected.getId(), actualData.getId());
        assertEquals(requestExpected.getTitle(), actualData.getTitle());
        assertEquals(requestExpected.isCompleted(), actualData.isCompleted());
    }
}
