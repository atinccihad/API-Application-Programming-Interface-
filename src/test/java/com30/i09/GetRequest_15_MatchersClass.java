package com30.i09;

import com30.testBase.DummyResttapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;


public class GetRequest_15_MatchersClass extends DummyResttapiexampleTestBase {
    /*
       https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
       status code : 200,
       5. calisan isminin "Airi Satou" oldugunu,
       calisan sayisinin 24 oldugunu,
       sondan ikinci calisanin maasinin 106450 oldugunu,
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

        response.then().assertThat().statusCode((Integer) expectedDataMap.get("statusCode"))
                .body("data[4].employee_name", equalTo(expectedDataMap.get("besinciCalisan")),
                        "data.id", hasSize((Integer) expectedDataMap.get("calisansayisi")),
                        "data[-2].employee_salary", equalTo("sondanIkinciCalisanMaasi"),
                        "data.employee_age", hasItems(((List) expectedDataMap.get("arananyaslar")).get(0)),
                        ((List<?>) expectedDataMap.get("arananYaslar")).get(1),
                        ((List<?>) expectedDataMap.get("arananYaslar")).get(2),
                        "data[10].employee_name", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_age")),
                        "data[10].profile_image", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("profile_image")));
    }
}
