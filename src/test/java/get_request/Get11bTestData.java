package get_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData2;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get11bTestData extends JsonplaceholderBaseUrl {

    @Test
    public void get11b() {
        // Set the expected data
        spec.pathParams("parametre1", "todos", "parametre2", 2);
        JsonPlaceHolderTestData2 expectedObje = new JsonPlaceHolderTestData2();
        HashMap<String, Object> expectedData = (HashMap<String, Object>) expectedObje.setupTestData();
        System.out.println("expectedData = " + expectedData);

        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}/{parametre2}");
        response.prettyPrint();
        System.out.println("=====================================================================================================================================");

        // 1. yontem Matchers class ile assertion islemi
        response.then().assertThat().statusCode((int) expectedData.get("statusCode"))
                .headers("Via", equalTo(expectedData.get("Via")),
                        "Server", equalTo(expectedData.get("Server")))
                .body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));


        // 2. yontem JsonPath
        JsonPath jsonPath = response.jsonPath();

        assertEquals("statusCode !!", expectedData.get("statusCode"), response.getStatusCode());
        assertEquals("Via !!", expectedData.get("Via"), response.getHeader("Via"));
        assertEquals("Server !!", expectedData.get("Server"), response.getHeader("Server"));

        assertEquals("userId !!", expectedData.get("userId"), jsonPath.getInt("userId"));
        assertEquals("title !!", expectedData.get("title"), jsonPath.getString("title"));
        assertEquals("completed !!", expectedData.get("completed"), jsonPath.getBoolean("completed"));

        // 3. yontem deSerialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);
        assertEquals("userId !!", expectedData.get("userId"), actualData.get("userId"));
        assertEquals("title !!", expectedData.get("title"), actualData.get("title"));
        assertEquals("completed !!", expectedData.get("completed"), actualData.get("completed"));

    }

}
