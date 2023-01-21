package com81.get_request;

import com81.base_urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import com81.test_data.DummyRestapiTestData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get13d_JsonPath extends DummyRestapiBaseUrl {
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
        response.prettyPrint();

        // JsonPath yontemi
        JsonPath json = response.jsonPath();

        // Do assertion
        assertEquals("StatusCode not equal 200!", expectedDataMap.get("statusCode"), response.getStatusCode());
        assertEquals("besincicalisan not equal Airi Satou!", expectedDataMap.get("besincicalisan"), json.getString("data[4].employee_name"));
        assertEquals("calisan sayisinin 24'den farkli!", expectedDataMap.get("calisansayisi"), json.getList("data.id").size());
        assertEquals("sondan 2. calisanin maasi 106450 degil!", expectedDataMap.get("sondanikincicalisanmaasi"), json.getInt("data[-2].employee_salary"));
        assertTrue("40, 21, 19 yaslarinda calisanlar testi fail!", json.getList("data.employee_age").containsAll((Collection<?>) expectedDataMap.get("arananyaslar")));
        assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("id"),
                json.getInt("data[10].id"));
        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                json.getString("data[10].employee_name"));
        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                json.getInt("data[10].employee_salary"));
        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                json.getInt("data[10].employee_age"));
        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                json.getString("data[10].profile_image"));

    }
}
