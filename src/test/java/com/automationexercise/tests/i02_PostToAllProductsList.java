package com.automationexercise.tests;

import com.automationexercise.baseUrl.AutomationexercisebaseUrl;
import com.automationexercise.testData.AutomationexerciseTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class i02_PostToAllProductsList extends AutomationexercisebaseUrl {

    @Test
    public void test02() {
        specAutomation.pathParam("first", "productsList");
        Response response = given()
                .accept(ContentType.JSON)
                .spec(specAutomation)
                .when()
                .post("/{first}");
        response.prettyPrint();

        assertTrue("response code test FAIL!", response.body().asString().contains("405"));
        assertTrue("response message test FAIL!", response.body().asString().contains("This request method is not supported."));
    }

}
