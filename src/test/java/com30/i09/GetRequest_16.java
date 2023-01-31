package com30.i09;

import com30.testBase.DummyRestestapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest_16 extends DummyRestestapiexampleTestBase {
    /*
       https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
       status code : 200,
       1. calisan isminin "Tiger Nixon" oldugunu,
       employee_salary degerinin 320800 oldugunu,
       employee_age degerinin 61 oldugunu,
       1. calisan bilgilerinin
       {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
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

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        // status code : 200
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        // 1. calisan isminin "Tiger Nixon" oldugunu
        assertEquals(expectedDataMap.get("birinciCalisan"),
                ((Map) ((List) actualDataMap.get("data")).get(0)).get("employee_name"));

        // employee_salary degerinin 320800 oldugunu
        assertEquals(expectedDataMap.get("birinciCalisanMaasi"),
                ((Map) ((List) actualDataMap.get("data")).get(0)).get("employee_salary"));

        // employee_age degerinin 61 oldugunu
        assertEquals(expectedDataMap.get("birinciCalisanYasi"),
                ((Map) ((List) actualDataMap.get("data")).get(0)).get("employee_age"));

        // 1. calisan bilgilerinin ....
        assertEquals(expectedDataMap.get("birinciCalisanBilgileri"),
                (((List) actualDataMap.get("data")).get(0)).toString());
    }
}
