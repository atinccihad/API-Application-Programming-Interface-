package com30.i14;

import com30.baseUrls.JsonPlaceHolderBaseUrl;
import com30.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithObjectMapper_01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/198  url'ine get request gonderildiginde
    donen response'un
    status code = 200
    ve body kisminin,
    {
    "userId":10,
    "id":198,
    "title":"quis eius est sint explicabo",
    "completed":true
    }
    oldugunu Object Mapper kullanarak test ediniz.
     */
    @Test
    public void test() {
        specJson.pathParams("first", "todos",
                "second", 198);
        String jsonData = "{\n" +
                "    \"userId\":10,\n" +
                "    \"id\":198,\n" +
                "    \"title\":\"quis eius est sint explicabo\",\n" +
                "    \"completed\":true\n" +
                "    }";

        Map<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, Map.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specJson)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        //     status code = 200
        assertEquals(200, response.getStatusCode());
        //    "userId":10
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        //    "id":198
        assertEquals(expectedData.get("id"), actualData.get("id"));
        //    "title":"quis eius est sint explicabo"
        assertEquals(expectedData.get("title"), actualData.get("title"));
        //    "completed":true
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
