package com81.get_request;


import com81.base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get03 extends JsonplaceholderBaseUrl {
    /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
   */

    @Test
    public void get03() {
        // set the URL
        spec.pathParams("first", "todos", "second", 23);

        // Set The Expected Data(Put, Patch, Post)

        // Send the request and Get Responce
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // response.prettyPrint();

        // Do Assertion
        // 1.yol(Hard Assert)
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));

        // 2.yol(Soft Assert) => sadece body icerisinde gecerlidir
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false), "userId", equalTo(2));

    }
}
