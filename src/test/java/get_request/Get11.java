package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void get11() {
        // 1. Set The URL
        spec.pathParam("first","users");

        // 2. Set The Expected Data ( put, post, patch)
        // 3. Send The Request And Get The Response
        Response response = given(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
    }
}

