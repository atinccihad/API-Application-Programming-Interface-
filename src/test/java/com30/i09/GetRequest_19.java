package com30.i09;

import com30.baseUrls.DummyResttapiexampleBaseUrl;
import com30.testData.DummyRestApiTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest_19 extends DummyResttapiexampleBaseUrl {
    /*
       https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
       status code : 200,
       En yuksek maasin 725000 oldugunu,
       Ikinci en yuksek maasin 325000 oldugunu,
       En kucuk yasin 19 oldugunu,
       seklinde oldugunu test edin.
        */

    @Test
    public void test() {
        specDummy.pathParam("first", "employees");

        DummyRestApiTestData expectedObje = new DummyRestApiTestData();
        HashMap<String, Integer> expectedDataMap = expectedObje.setUpTestData02();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given()
                .accept("application/json")
                .spec(specDummy)
                .when()
                .get("/{first}");
        response.prettyPrint();
        // *** De-Serialization ***
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        //       status code : 200,
        assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        //       En yuksek maasin 725000 oldugunu,
        List<Integer> maasListesi = new ArrayList<>();
        int datasize = ((List<?>) actualDataMap.get("data")).size();
        for (int i = 0; i < datasize; i++) {
            maasListesi.add((Integer) ((Map<?, ?>) ((List<?>) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(maasListesi);
        assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesi.get(datasize - 1));

        //       Ikinci en yuksek maasin 325000 oldugunu,
        assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasListesi.get(maasListesi.size() - 2));

        //       En kucuk yasin 19 oldugunu,
        List<Integer> yasListesi = new ArrayList<>();
        for (int i = 0; i < datasize; i++) {
            yasListesi.add((Integer) ((Map) ((List<?>) actualDataMap.get("data")).get(0)).get("employee_age"));
        }
        Collections.sort(yasListesi);
        assertEquals(expectedDataMap.get("enKucukYas"), yasListesi.get(0));

        // *** JsonPath ***
        JsonPath json = response.jsonPath();

        //       status code : 200,
        assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        //       En yuksek maasin 725000 oldug
        List<Integer> maasListesiJson = json.getList("data.employee_salary");
        Collections.sort(maasListesiJson);
        assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesiJson.get(maasListesiJson.size() - 1));

        //       Ikinci en yuksek maasin 325000 oldugunu,
        assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasListesiJson.get(maasListesiJson.size() - 2));

        //       En kucuk yasin 19 oldugunu,
        List<Integer> yasListesiJson = json.getList("data.employee_age");
        Collections.sort(yasListesi);
        assertEquals(expectedDataMap.get("enKucukYas"), yasListesi.get(0));
    }
}
