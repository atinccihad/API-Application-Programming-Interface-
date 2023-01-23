package com30.i05;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest_05 {
     /*
    https://dummy.restapiexample.com/api/v1/employees url'ine,
    accept type'i "application-json" olan get request'i yolladigimda gelen response'un
    status code : 200
    content-type : "application/json"
    ve employees sayisinin 24
    ve employee'lerden birinin "Doris Wilder"
    ve gelen yaslar icinde 21, 61 ve 23 degerlerinin bulundugunu test edin.
    */

    @Test
    public void test() {
        String url = "https://dummy.restapiexample.com/api/v1/employees";

        Response response = given()
                .accept("application/json")
                .when()
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(                  "data.id", hasSize(24),
                        "data.employee_name", hasItem("Doris Wilder"),
                                             "data.employee_age", hasItems(35, 30, 40));
    }
}
