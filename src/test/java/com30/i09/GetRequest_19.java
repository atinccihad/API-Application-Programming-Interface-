package com30.i09;

import com30.testBase.DummyResttapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class GetRequest_19 extends DummyResttapiexampleTestBase {
       /*
       https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
       Status kodun 200 oldugunu,
       En yuksek maasin 725000 oldugunu,
       En kucuk yasin 19 oldugunu,
       Ikinci en yuksek maasin 675000
       oldugunu test edin
       */
    @Test
    public void test() {
        specDummy.pathParam("first", "employees");

        DummyRestApiTestData expectedObje = new DummyRestApiTestData();
        HashMap<String, Integer> expectedDataMap = expectedObje.setUpTestData02();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given()
                .accept("application/json")
                .spec(specDummy)
                .when()
                .get("/{first}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        // Status kodun 200 oldugunu
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        // En yuksek maasin 725000 oldugunu
        assertEquals(expectedDataMap.get("enYuksekMaas"),);
        assertTrue(jsonPath.getList("data.employee_age").containsAll((List) expectedDataMap.get("arananYaslar")));



    }
}
