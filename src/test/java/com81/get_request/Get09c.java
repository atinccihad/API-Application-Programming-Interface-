package com81.get_request;

import com81.base_urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09c extends DummyRestapiBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/employees  url'ine bir istek gonderildiginde
    status kodunun 200,
    gelen body de
    calisanin isminin "Airi Satou" oldugunu,
    calisanin maasinin "320800" oldugunu
    toplam 24 calisan oldugunu
    "Rhona Davidson" un employee lerden biri oldugunu
    "21","23","61" yaslarinda employeeler oldugunu test edin.
     */

    @Test
    public void test09c() {
        spec.pathParam("parametre1", "employees");
        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}");
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        // status kodunun 200
        assertEquals("response.statusCode 200 degil!", 200, response.statusCode());

        // calisanin isminin "Airi Satou" oldugunu
        assertEquals("calisan ismi 'Airi Satou' den farkli!", "Airi Satou", jsonPath.getString("data[4].employee_name"));

        // toplam 24 calisan oldugunu
        System.out.println("Calisan sayisi: "+jsonPath.getList("data.id").size());
        assertEquals("calisanSayisi 24 den farkli!", 24, jsonPath.getList("data.id").size());

        // "Rhona Davidson" un employee lerden biri oldugunu
        assertTrue("'Rhona Davidson' un employee lerden biri degil!", jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

        // "21","23","61" yaslarinda employeeler oldugunu
        List<Integer> arananYaslar = Arrays.asList(21, 23, 61);
        /*
        List<Integer>arananYaslar = new ArrayList<>();
        arananYaslar.add(21);
        arananYaslar.add(23);
        arananYaslar.add(61);
        */
        assertTrue("Verilen yaslar 21,23,61 icermiyor!", jsonPath.getList("data.employee_age").containsAll(arananYaslar));
    }
}
