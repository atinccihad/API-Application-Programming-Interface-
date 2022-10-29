package get_request;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get02b extends ReqresBaseUrl {
    /*
     Given
         https://reqres.in/api/users/23
     When
         User send a GET Request to the url
     Then
         HTTP Status code should be 404
     And
         Status Line should be HTTP/1.1 404 Not Found
     And
         Server is "cloudflare"
     And
         Response body should be empty
  */
    @Test
    public void get02() {
        //Set the Url
        spec.pathParams("first", "api", "second", "users", "third", 23);

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //Do Assertion
    }

}
