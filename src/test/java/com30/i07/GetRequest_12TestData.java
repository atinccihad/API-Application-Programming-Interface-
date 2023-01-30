package com30.i07;

import com30.testBase.JsonPlaceHolderTestBase;
import com30.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetRequest_12TestData extends JsonPlaceHolderTestBase {

    @Test
    public void test() {
        specJson.pathParams("first",
                "todos", "second", 2);

        JsonPlaceHolderTestData expectedObje = new JsonPlaceHolderTestData();

        HashMap<String, Object> expectedData = (HashMap<String, Object>) expectedObje.setUpTestData();
        System.out.println("expectedData = " + expectedData);

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
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData Map = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
