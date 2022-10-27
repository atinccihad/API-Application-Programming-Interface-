package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get06 extends RestfulBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get01() {

        // 1. Set The URL ( URL Olustur)

        spec.pathParams("first", "booking", "second", 2325);

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response ( Talep gonder ve cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion ( Dogrulama Yap)

        /*
         Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": { // outer json
        "checkin": "2022-10-27", // inner json
        "checkout": "2022-11-07" // inner json
    },
    "additionalneeds": "None"
}
         */
        // 1. Yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Bradley"),
                        "lastname", equalTo("Pearson"),
                        "totalprice", equalTo(132),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2022-10-27"),
                        "bookingdates.checkout", equalTo("2022-11-07"),
                        "additionalneeds", equalTo("None"));

        // 2. Yol : Jsonpath class'nın kullanimi
        JsonPath json = response.jsonPath();


    }


}
