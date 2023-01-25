package com81.practice;

import com81.base_urls.AutomationBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class API3 extends AutomationBaseUrl {
     /*
    Given
    https://automationexercise.com/api/brandsList
    When
    User sends a GET Request to the url
    Then
    HTTP Response Code should be 200
    And
    Response JSON: All brands list
   */
    @Test
    public void GetAllBrandsList() {
        SoftAssert softAssert = new SoftAssert();
        //    Given https://automationexercise.com/api/brandsList
        spec.pathParams("first", "api", "second", "brandsList");
        //    When User sends a GET Request to the url
        Response response = given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath = response.jsonPath();
        //    Then HTTP Response Code should be 200
        softAssert.assertEquals(response.getStatusCode(), 200, "status code doesn't match 200!");
        //    And Response JSON: All brands list
        Map<String, String> allBrandsList = response.jsonPath().get();
        jsonPath.prettyPrint();
        softAssert.assertAll();
    }
}
