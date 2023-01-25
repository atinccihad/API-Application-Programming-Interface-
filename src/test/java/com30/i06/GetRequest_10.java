package com30.i06;

import com30.testBase.DummyRestestapiexampleTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest_10 extends DummyRestestapiexampleTestBase {
    /*
    https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
    status code : 200,
    gelen body'de,
    5. calisanin isminin "Airi Satou" oldugunu,
    6. calisanin maasinin "372000" oldugunu,
    Toplam 24 calisan oldugunu,
    "Rhona Davidson" un employee'lerden biri oldugunu,
    "21", "23", "61" yaslarinda employee ler oldugunu test edin
    Ilk 4 isim bilgisini yazdiriniz.
     */
    @Test
    public void test() {
        specDummy.pathParam("first", "employees");

        Response response = given()
                .accept("application/json")
                .spec(specDummy)
                .when()
                .get("/{first}");
        response.prettyPrint();
        // status code : 200
        assertEquals(200, response.getStatusCode());

        // JsonPath
        JsonPath jsonPath = response.jsonPath();

        int perNum = jsonPath.getList("data.id").size();
        List<String> perNames = jsonPath.getList("data.employee_name");
        List<Integer> employeeAges = jsonPath.getList("data.employee_age");
        String wantedName = "Rhona Davidson";

        System.out.println(perNum);
        System.out.println(perNames);
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ".isim: " + perNames.get(i) + " ");
        }
        // 5. calisanin isminin "Airi Satou" oldugunu
        assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));

        // 6. calisanin maasinin "372000" oldugunu
        assertEquals(372000, jsonPath.getInt("data[5].employee_salary"));

        // Toplam 24 calisan oldugunu
        assertEquals(24, perNum);
        assertEquals(24, jsonPath.getList("data.id").size());

        // "Rhona Davidson" un employee'lerden biri oldugunu
        assertTrue(perNames.contains(wantedName));

        // "21", "23", "61" yaslarinda employee ler oldugunu
        List<Integer> soughtAge = new ArrayList<>();
        soughtAge.add(21);
        soughtAge.add(23);
        soughtAge.add(61);
        assertTrue(employeeAges.containsAll(soughtAge));
        assertTrue("soughtAge !!",employeeAges.containsAll(soughtAge));

        // Matchers
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(                  "data[4].employee_name", equalTo("Airi Satou"),
                        "data[5].employee_salary", equalTo(372000),
                                             "data.employee_name",hasItems(wantedName),
                                             "data.employee_age",equalTo(employeeAges));
    }
}
