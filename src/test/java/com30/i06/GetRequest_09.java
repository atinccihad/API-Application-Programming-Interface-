package com30.i06;

import com30.testBase.DummyResttapiexampleTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest_09 extends DummyResttapiexampleTestBase {
    /*
    https://dummy.restapiexample.com/api/v1/employees url'ine request gonderip,
    1) Butun calisanlarin isimlerini consola yazdiralim
    2) 3. calisan kisinin ismini consola yazdiralim
    3) Ilk 5 calisanin isimlerini consola yazdiralim
    4) En son calisanin ismini consola yazdiralim
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

        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));
        System.out.println("3. calisan kisinin ismi: " + jsonPath.getString("data[2].employee_name"));
        // System.out.println("3. calisan kisinin ismi: "+jsonPath.getString("data.employee_name[2]")); bu satirda calisir fakat ust satir syntax daha uygun
        System.out.println("Ilk 5 calisanin isimleri: " + jsonPath.getString("data.employee_name[0,1,2,3,4]"));
        System.out.println("En son calisanin ismi: " + jsonPath.getString("data.employee_name[-1]"));

        assertEquals(200,response.getStatusCode());
        assertEquals("Ashton Cox",jsonPath.getString("data[2].employee_name"));
        assertEquals("Doris Wilder",jsonPath.getString("data.employee_name[-1]"));

        // Matchers
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(                  "data[2].employee_name",equalTo("Ashton Cox"),
                        "data.employee_name[-1]",equalTo("Doris Wilder"));
    }
}
