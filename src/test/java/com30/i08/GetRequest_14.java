package com30.i08;

import com30.testBase.DummyRestestapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest_14 extends DummyRestestapiexampleTestBase {
    /*
    https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
    status code : 200,
    5. calisan isminin "Airi Satou" oldugunu,
    calisan sayisinin 24 oldugunu,
    Sondan ikinci calisanin maasinin 106450 oldugunu,
    40,21 ve 19 yaslarinda calisanlar olup olmadigini,
    11. calisan bilgilerinin
    {
            "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
        },
     seklinde oldugunu test edin.
     */

    @Test
    public void test() {
        specDummy.pathParam("first", "employees");

        DummyRestApiTestData expectedDataObje = new DummyRestApiTestData();
        HashMap<String, Object> expectedDataMap = expectedDataObje.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);
        Response response = given()
                .accept(ContentType.JSON)
                .spec(specDummy)
                .when()
                .get("/{first}");
        response.prettyPrint();
    }
}
