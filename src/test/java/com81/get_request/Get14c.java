package com81.get_request;

import com81.base_urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import com81.test_data.DummyRestapiTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14c extends DummyRestapiBaseUrl {
    /*
       https://dummy.restapiexample.com/api/v1/employees  url'ine istek gonderildiginde,
       Status kodun 200 oldugunu
       En yuksek maasin 725000 oldugunu,
       En kucuk yasin 19 oldugunu,
       Ikinci en yuksek maasin 675000
           seklinde oldugunu test edin.
    */
    @Test
    public void test14c() {
        // Set the Url
        spec.pathParam("parametre1", "employees");

        DummyRestapiTestData expectedObje = new DummyRestapiTestData();
        HashMap<String, Integer> expectedDataMap = expectedObje.setupTestData02();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}");
        // response.prettyPrint();

        //******* 1- De-Serialization yontemi ile ********
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        // Status kodun 200 oldugunu
        assertEquals("Status kod 200'den farkli!", expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        // En yuksek maasin 725000 oldugunu
        List<Integer> maasListesi = new ArrayList<>();
        int dataSize = ((List) actualDataMap.get("data")).size();

        for (int i = 0; i < dataSize; i++) {
            maasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(maasListesi);
        assertEquals("En yuksek maas 725000 degil!", expectedDataMap.get("enYuksekMaas"), maasListesi.get(dataSize - 1));

        // En kucuk yasin 19 oldugunu
        List<Integer> yasListesi = new ArrayList<>();

        for (int i = 0; i < dataSize; i++) {
            yasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(yasListesi);
        assertEquals("En kucuk yas 19 degil!", expectedDataMap.get("enKucukYas"), yasListesi.get(0));

        // Ikinci en yuksek maasin 675000 seklinde oldugunu test edin.
        assertEquals("Ikinci yuksek maas 675000 degil!", expectedDataMap.get("ikinciYuksekMaas"), maasListesi.get(dataSize - 2));

        //******** 2- JsonPath yontemi ile ********
        JsonPath json = response.jsonPath();

        // En yuksek maasin 725000 oldugunu
        List<Integer> maasListesiJson = json.getList("data.employee_salary");
        Collections.sort(maasListesiJson);
        assertEquals("En yuksek maas 725000 degil!", expectedDataMap.get("enYuksekMaas"), maasListesiJson.get(maasListesiJson.size() - 1));

        // En kucuk yasin 19 oldugunu
        List<Integer> yasListesiJson = json.getList("data.employee_age");
        Collections.sort(yasListesiJson);

        assertEquals("enKucukYas 19 degil!", expectedDataMap.get("enKucukYas"), yasListesiJson.get(0));

        // Ikinci en yuksek maasin 675000 seklinde oldugunu test edin.
        assertEquals("Ikinci en yuksek maas 675000 degil!", expectedDataMap.get("ikinciYuksekMaas"), maasListesiJson.get(maasListesiJson.size() - 2));

    }
}
