package get_request;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_GetRequest {
    /*
    https://jsonplaceholder.typicode.com/posts/44  url'ine bir GET request yolladigimizda donen Response'in
    Status code'unun 200,
    ve content type'inin JSON,
    ve response body'sinde bulunan userId'nin 5,
    ve response body'sinde bulunan title'in "optio dolor molestias sit",
    oldugunu test edin.
     */

    @Test
    public void get09() {
        // 1- Request URL ve Body olustur
        String url = "https://jsonplaceholder.typicode.com/posts/44";

        // 2- Expected Data olustur
        JSONObject expBody = new JSONObject();
        expBody.put("userId", 5);
        expBody.put("title", "optio dolor molestias sit");

        // 3- save to Respons
        Response response = given().when().get(url);
        //response.prettyPrint();

        // 4- Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonPath actualBody = response.jsonPath();

        assertEquals(expBody.get("userId"), actualBody.get("userId"));
        assertEquals(expBody.get("title"), actualBody.get("title"));

    }
}
