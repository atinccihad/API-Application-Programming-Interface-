package com30.i05;

import com30.testBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest_06 extends JsonPlaceHolderTestBase {
    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine,
    accept type'i "application-json" olan get request'i yolladigimda gelen response'un
    status code : 200
    content-type : "application/json"
    Headers'daki "Server" in "cloudflare"
    response body'deki "userId" nin 7
    "title" in "esse et quis iste est earum aut impedit"
    "completed" bolumunun false
    oldugunu test edin.
    */

    @Test
    public void test() {
        // String url = "https://jsonplaceholder.typicode.com/todos/123";
        specJson.pathParams("first", "todos",
                "second", 123);

        Response response = given()
                .accept("application/json")
                .spec(specJson)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // Matchers class
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .header(           "Server",    equalTo("cloudflare"))
                .body(                  "userId",    equalTo(7),
                        "title",     equalTo("esse et quis iste est earum aut impedit"),
                                             "completed", equalTo(false));
    }
}
