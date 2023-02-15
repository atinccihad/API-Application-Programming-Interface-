package com30.i07;

import com30.baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class GetRequest_12 extends JsonPlaceHolderBaseUrl {
/*
    https://jsonplaceholder.typicode.com/todos/2 url'ine bir istek gonderildiginde,
    status code : 200,
    gelen body'de,
    "completed": degerinin false,
    "title": degerinin "quis ut nam facilis et officia qui",
    "userId" sinin 1
    ve header degerlerinden "Via" degerinin "1.1 vegur"
    ve "Server" degerinin "cloudflare" oldugunu test edin.
     */
    /*
    url olustur
    expected data
    request gonder
    actual data
    assertion
    */

    @Test
    public void test() {
        specJson.pathParams("first",
                "todos", "second", 2);

        HashMap<String, Object> expectedData = new HashMap<String, Object>();

        expectedData.put("statusCode", 200);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println("expectedData = " + expectedData);
        System.out.println("-------------------------------------------");

        Response response = given()
                .accept(ContentType.JSON)
                .spec(specJson)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // JsonPath
        JsonPath jsonPath = response.jsonPath();

        // "completed": degerinin false
        boolean completed = jsonPath.getBoolean("userId");
        assertFalse(completed);

        // "title": degerinin "quis ut nam facilis et officia qui"
        String title = jsonPath.getString("title");
        String expectedTitle = "quis ut nam facilis et officia qui";
        assertEquals(expectedTitle, title);

        // "userId" sinin 1
        String userId = jsonPath.getString("userId");
        String expectedUserId = "1";
        assertEquals(expectedUserId, userId);

        // ve header degerlerinden "Via" degerinin "1.1 vegur" ve "Server" degerinin "cloudflare"

        // 1. yontem Matchers class ile assertion
        response.then()
                .assertThat()
                .statusCode((int) expectedData.get("statusCode"))
                .headers("via", equalTo(expectedData.get("via")),
                        "Server", equalTo(expectedData.get("Server")))
                .body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));
        // 2. yontem jsonPath
        assertEquals(expectedData.get("statusCode"), response.getStatusCode());
        assertEquals(expectedData.get("via"), response.getHeader("via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));
        assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));

        // 3. yontem deSerialization
        /*
            - object mapper
            - pojo class ile birlikte Map class'i kullanacagiz
         */
    }
}
