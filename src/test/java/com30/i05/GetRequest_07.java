package com30.i05;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest_07 {
    /*
    https://dummy.restapiexample.com/api/v1/employee/719 url'ine,
    accept type'i "application-json" olan get request'i yolladigimda gelen response'un
    status code : 200
    content-type : "application/json"
    ve response body'nin
    {
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
            },
    "message": "Successfully! Record has been fetched."
    }
     seklinde oldugunu test edin.
    */
    @Test
    public void test() {
        String url = "https://dummy.restapiexample.com/api/v1/employee/1";

        Response response = given()
                .accept("application/json")
                .when()
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(                  "status", equalTo("success"),
                        "data.id", equalTo(1),
                                             "data.employee_name", equalTo("Tiger Nixon"),
                                             "data.employee_salary", equalTo(320800),
                                             "data.employee_age", equalTo(61),
                                             "data.profile_image", equalTo(""),
                                             "message", equalTo("Successfully! Record has been fetched."));
    }
}
