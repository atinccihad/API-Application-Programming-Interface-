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

public class GetRequest_15_MatchersClass extends DummyRestestapiexampleTestBase {
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

        // De Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        // status code : 200
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        // 5. calisan isminin "Airi Satou" oldugunu
        assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(4)).get("employee_name"));

        // calisan sayisinin 24 oldugunu
        assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List<?>) actualDataMap.get("data")).size());

        // Sondan ikinci calisanin maasinin 106450 oldugunu

        // once actualData'dan bize donen list'in size'ini almaliyiz
        int dataSize = ((List<?>) actualDataMap.get("data")).size();

        assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"),
                ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(dataSize - 2)).get("employee_salary"));

        // 40,21 ve 19 yaslarinda calisanlar olup olmadigini
        List<Integer> actualYasListesi = new ArrayList<>();

        for (int i = 0; i < dataSize; i++) {
            actualYasListesi.add((Integer) ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(i)).get("employee_age"));
        }

        assertTrue(actualYasListesi.containsAll((List<?>) expectedDataMap.get("arananYaslar")));

        // 11. calisan bilgilerini dogrulayin
        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_name"),
                ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),
                ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_age"),
                ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("profile_image"),
                ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));
    }
}
