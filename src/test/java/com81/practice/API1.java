package com81.practice;

import com81.base_urls.AutomationBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class API1 extends AutomationBaseUrl {

    @Test
    public void GetAllProductsList() {

        SoftAssert softAssert = new SoftAssert();
        spec.pathParams("first", "api", "second", "productsList");
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //JsonPath jsonPath = response.jsonPath();
        //jsonPath.prettyPrint();
        softAssert.assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        softAssert.assertAll();
    }
}
