package get_request;

import base_urls.DummyRestapiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyRestapiTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get13c_matchersClass extends DummyRestapiBaseUrl {
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

        response.then().assertThat().statusCode((Integer) expectedDataMap.get("statusCode"))
                .body("data[4].employee_name", equalTo(expectedDataMap.get("besincicalisan")),
                        "data.id", hasSize((Integer) expectedDataMap.get("calisansayisi")),
                        "data[-2].employee_salary", equalTo(expectedDataMap.get("sondanikincicalisanmaasi")),
                        "data.employee_age", hasItems(((List) expectedDataMap.get("arananyaslar")).get(0),
                                ((List) expectedDataMap.get("arananyaslar")).get(1),
                                ((List) expectedDataMap.get("arananyaslar")).get(2)),
                        "data[10].employee_name", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_age")),
                        "data[10].profile_image", equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image")));

    }
}