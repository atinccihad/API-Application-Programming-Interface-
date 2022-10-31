package get_request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertTrue;

public class RestAssuredTest {

    @Test
    public void singleUserBddTest() {
        when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .time(lessThan(10000L));
    }

    @Test
    public void singleUserTest() {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/api/users/2");
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        System.out.println(bodyAsString);
        assertTrue(bodyAsString.contains("janet.weaver@reqres.in"), "String not found");
    }

    @Test
    public void createUserTest() {
        String postData = "{\n" +
                "        \"name\": \"morpheus\",\n" +
                "            \"job\": \"leader\",\n" +
                "            \"id\": \"172\",\n" +
                "            \"createdAt\": \"2022-10-31T12:15:31.620Z\"\n" +
                "    }";
        given().
                contentType(ContentType.JSON).
                body(postData).
                when().
                post("https://reqres.in/api/users").
                then().statusCode(201).
                body("name", equalTo("morpheus"));
    }


}
