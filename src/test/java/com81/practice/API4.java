package com81.practice;

import com81.base_urls.AutomationBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class API4 extends AutomationBaseUrl {
    /*
    API URL: https://automationexercise.com/api/brandsList
    Request Method: PUT
    Response Code: 405
    Response Message: This request method is not supported.
     */
    @Test
    public void putToAllBrandsList() {
        SoftAssert softAssert = new SoftAssert();
        String message = "This request method is not supported.";
        //    Given https://automationexercise.com/api/brandsList
        spec.pathParams("first", "api", "last", "brandsList");
        //    When User sends a GET Request to the url
        Response response = given().spec(spec).when().put("/{first}/{last}");
        JsonPath jsonPath = response.jsonPath();
        //    Then HTTP Response Code should be 405
        softAssert.assertEquals(405, response.getStatusCode(), "Response Code not match 405");
        //    And Response Message: This request method is not supported.
        softAssert.assertEquals(message, response.getStatusLine(), "This request method is not supported.");

        softAssert.assertAll();
    }
}
