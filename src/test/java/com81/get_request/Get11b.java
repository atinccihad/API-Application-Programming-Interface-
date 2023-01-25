package com81.get_request;

import com81.base_urls.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Get11b extends JsonplaceholderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/2  url'ine istek gonderildiginde,
    Donen response'un
    Status kodunun 200,
    Donen body'de  "completed" degerinin false,
    "title" degerinin "quis ut nam facilis et offica qui"
    "userId" sinin 1 ve header degerlerinden "Via" degerinin "1.1 vegur" ve
    "Server" degerinin "cloudflare" oldugunu test edin.
     */
    /*
    url olustur
    -- expected data
    request gonder
    -- actual data
    assertion
     */

    @Test
    public void get11b() {
        // Set the expected data
        spec.pathParams("parametre1", "todos", "parametre2", 2);
        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}/{parametre2}");
        response.prettyPrint();
        System.out.println("=====================================================================================================================================");

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);

        // 1. yontem Matchers class ile assertion islemi
        response.then().assertThat().statusCode((int)expectedData.get("statusCode"))
                .headers("Via", equalTo(expectedData.get("Via")),
                        "Server",equalTo(expectedData.get("Server")))
                .body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));


        // 2. yontem JsonPath
        JsonPath jsonPath = response.jsonPath();
        assertEquals("statusCode !!",expectedData.get("statusCode"),response.getStatusCode());
        assertEquals("Via !!",expectedData.get("Via"),response.getHeader("Via"));
        assertEquals("Server !!",expectedData.get("Server"),response.getHeader("Server"));

        assertEquals("userId !!",expectedData.get("userId"),jsonPath.getInt("userId"));
        assertEquals("title !!",expectedData.get("title"),jsonPath.getString("title"));
        assertEquals("completed !!",expectedData.get("completed"),jsonPath.getBoolean("completed"));

        // 3. yontem deSerialization
        // object mapper
        // pojo class ile birlikte map




    }
}
