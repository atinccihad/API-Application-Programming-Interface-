package com.automationexercise.tests;

import com.automationexercise.baseUrl.AutomationexercisebaseUrl;
import com.automationexercise.testData.AutomationexerciseTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class i01_GetAllProductsList extends AutomationexercisebaseUrl {

    @Test
    public void test01() {
        specAutomation.pathParam("first","productsList");
        Response response = given()
                .accept(ContentType.JSON)
                .spec(specAutomation)
                .when()
                .get("/{first}");
        response.prettyPrint();

        AutomationexerciseTestData expectedDataObje = new AutomationexerciseTestData();
        HashMap<String, Object> expectedDataMap = expectedDataObje.setupTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

    }
}
