package get_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get06c extends JsonplaceholderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/todos/123  url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen responce'un
    Status code : 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare
    ve response body'deki "userId" nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin.
     */

    @Test
    public void test06c() {
        //String url = "https://jsonplaceholder.typicode.com/todos/123";
        spec.pathParams("parametre1", "todos", "parametre2", 123);
        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}/{parametre2}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .header("Server", equalTo("cloudflare"))
                .body("userId", equalTo(7)
                        , "title", equalTo("esse et quis iste est earum aut impedit")
                        , "completed", equalTo(false));
    }
}
