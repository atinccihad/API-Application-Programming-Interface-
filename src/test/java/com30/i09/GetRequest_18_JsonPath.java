package com30.i09;

import com30.baseUrls.DummyResttapiexampleBaseUrl;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest_18_JsonPath extends DummyResttapiexampleBaseUrl {
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

        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        // status code : 200,
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        //       5. calisan isminin "Airi Satou" oldugunu,
        assertEquals(expectedDataMap.get("besinciCalisan"), jsonPath.getString("data[4].employee_name"));
        //       calisan sayisinin 24 oldugunu,
        assertEquals(expectedDataMap.get("calisanSayisi"), jsonPath.getList("data.id").size());
        //       sondan ikinci calisanin maasinin 106450 oldugunu,
        assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"), jsonPath.getInt("data[-2].employee_salary"));
        //       40,21 ve 19 yaslarinda calisanlar olup olmadigini,
        assertTrue(jsonPath.getList("data.employee_age").containsAll((List) expectedDataMap.get("arananYaslar")));
        //       11. calisan bilgilerinin .... oldugunu dogrulayin
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("id"),
                jsonPath.getInt("data[10].id"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("profile_image"),
                jsonPath.getString("data[10].profile_image"));
    }
}
