package com30.i09;

import com30.testBase.DummyResttapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest_17 extends DummyResttapiexampleTestBase {
    /*
       https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
       2. calisan isminin "Garrett Winters" oldugunu,
       ikinci calisanin maasinin 170750 oldugunu,
       2. calisan bilgilerinin,
       {
            "id": 2,
            "employee_name": "Garrett Winters",
            "employee_salary": 170750,
            "employee_age": 63,
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

        // 2. calisan isminin "Garrett Winters" oldugunu
        assertEquals(expectedDataMap.get("ikinciCalisan"),
                ((Map) ((List) actualDataMap.get("data")).get(1)).get("employee_name"));

        // ikinci calisanin maasinin 170750 oldugunu
        assertEquals(expectedDataMap.get("ikinciCalisanMaasi"),
                ((Map) ((List) actualDataMap.get("data")).get(1)).get("employee_salary"));

        // 2. calisan bilgilerinin .....
        assertEquals(expectedDataMap.get("ikinciCalisanBilgileri"),
                (((List) actualDataMap.get("data")).get(1)).toString());
    }
}
