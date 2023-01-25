package com81.practice;

import com81.base_urls.AutomationBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class API2 extends AutomationBaseUrl {
    /* 
    Given
    https://automationexercise.com/api/productsList
    When
    User sends a POST Request to the url
    Then
    HTTP Response Code should be 405
    And
    Response Message: This request method is not supported.
   */
    @Test
    public void PostToAllProductsList() {
        String message = "This request method is not supported.";
        SoftAssert softAssert = new SoftAssert();
        //Given https://automationexercise.com/api/productsList
        spec.pathParams("first", "api", "second", "productsList");
        // When User sends a POST Request to the url
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // Then HTTP Response Code should be 405
        softAssert.assertEquals(response.statusCode(), 405,"doesn't match!");
        // And Response Message: This request method is not supported.
        softAssert.assertEquals(message,response.asPrettyString(),"doesn't match!");
        softAssert.assertAll();
    }
}
