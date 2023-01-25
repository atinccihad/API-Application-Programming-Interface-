package com81.get_request;

import com81.base_urls.DummyRestapiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import com81.test_data.DummyRestapiTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get13b extends DummyRestapiBaseUrl {
/*
    https://dummy.restapiexample.com/api/v1/employees  url'ine istek gonderildiginde,
    Status kodun 200 oldugunu
    5. calisan isminin "Airi Satou"  oldugunu,
    calisan sayisinin 24 oldugunu,
    sondan 2. calisanin maasinin 106450 oldugunu,
    40, 21, 19 yaslarinda calisanlar olup olmadigini,
    11. calisan bilgilerinin
    {
            "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
        }
        seklinde oldugunu test edin.
 */
    @Test
    public void test13b() {
        // Set the Url
        spec.pathParam("parametre1", "employees");

        DummyRestapiTestData expectedObje = new DummyRestapiTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setuptestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // Send the request get the response
        Response response = given()
                .accept("application/jason")
                .spec(spec)
                .when()
                .get("/{parametre1}");
        // response.prettyPrint();

        // De Serialization islemi
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        // Status kodun 200 oldugunu
        assertEquals("Status kod 200'den farkli!", 200, response.getStatusCode());

        // 5. calisan isminin "Airi Satou"  oldugunu
        assertEquals("calisan isminin Airi Satou'dan farkli!", expectedDataMap.get("besincicalisan"), ((Map) ((List) actualDataMap.get("data")).get(4)).get("employee_name"));

        // calisan sayisinin 24 oldugunu
        assertEquals("calisan sayisinin 24'ten farkli!", expectedDataMap.get("calisansayisi"), ((List) actualDataMap.get("data")).size());

        //     sondan 2. calisanin maasinin 106450 oldugunu

        // once actual datadan bize donen List'in size'ini almaliyiz
        int datasize = ((List) actualDataMap.get("data")).size();
        assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"), ((Map) ((List) actualDataMap.get("data")).get(datasize - 2)).get("employee_salary"));

        // 40, 21, 19 yaslarinda calisanlar olup olmadigini
        List<Integer> actualAges = new ArrayList<>();

        for (int i = 0; i < datasize; i++) {
            actualAges.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        System.out.println("actualAges = " + actualAges);

        assertTrue("40, 21, 19 icermiyor!", actualAges.containsAll((List) expectedDataMap.get("arananyaslar")));

        // 11. calisan bilgileri assertion
        assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_name"), ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));
        assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_salary"), ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));
        assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_age"), ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));
        assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image"), ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));
    }
}
