package get_request;

import base_urls.DummyRestapiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyRestapiTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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

    }
}
