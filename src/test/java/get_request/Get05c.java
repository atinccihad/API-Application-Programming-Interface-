package get_request;

import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get05c {
    /*
    https://dummy.restapiexample.com/api/v1/employees
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen responce'un
    Status code : 200
    ve content type'inin "application/json"
    ve employees sayisinin 24
    ve employee'lerden birinin "Ashtan Cox"
    ve gelen yaslar icinde 21, 61 ve 23 degerlerinden birinin oldugunu test edin.
     */

    @Test
    public void test05c() {
        // Get the Url
        String url = "https://dummy.restapiexample.com/api/v1/employees";
        Response response = given()
                .accept("application/json")
                .when()
                .get(url);
        response.prettyPrint();

        // Response status code 200?
        response.then().assertThat().statusCode(200)

                // Response content type "application/json"?
                .contentType("application/json")

                // employes size 24?
                // employes contains  "Ashtan Cox"?
                // Response contains 21, 61 and 23 assertThat
                .body("data.profile_image", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age", hasItems(21, 61, 23));
    }
}
