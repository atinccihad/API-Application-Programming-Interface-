package com30.i06;

import com30.baseUrls.DummyResttapiexampleBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GetRequest_11 extends DummyResttapiexampleBaseUrl {
     /*
    https://dummy.restapiexample.com/api/v1/employees url'ine bir istek gonderildiginde,
    status code : 200,
    gelen body'de,
    1) 10'dan buyuk tum id'leri ekrana yazdirin ve
      10'dan buyuk 14 id oldugunu,
    2) 30'dan kucuk tum yaslari ekrana yazdirin ve
      bu yaslarin icerisinde en buyuk yasin 23 oldugunu,
    3) Maasi 350000 den buyuk olan tum employee name'leri ekrana yazdirin ve bunlarin icerisinde "Charde Marshall" oldugunu test edin.
     */

    @Test
    public void test() {
        /*
        groovy language javanin alt dilidir.
        Biz bu dil yardimi ile loop kullanmadan gelen response'daki degerleri
        bir sarta bagli olarak listeye assign edebiliyoruz.
         */
        specDummy.pathParams("first", "employees");

        Response response = given()
                .accept("application/json")
                .spec(specDummy)
                .when()
                .get("/{first}");
        response.prettyPrint();
        assertEquals(200, response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();
        // 1) 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk 14 id oldugunu
        List<Integer> ids = jsonPath.getList("data.id");
        System.out.println("ids = " + ids);
        List<Integer> ids10danBuyuk = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) > 10) {
                ids10danBuyuk.add(ids.get(i));
            }
        }
        assertEquals(14, ids10danBuyuk.size());

        // *** groovy language ***
        List<Integer> ondanBuyukIds = jsonPath.getList("data.findAll{it.id>10}");
        System.out.println("ondanBuyukIds = " + ondanBuyukIds);
        assertEquals(14, ondanBuyukIds.size());

        // 2) 30'dan kucuk tum yaslari ekrana yazdirin ve bu yaslarin icerisinde en buyuk yasin 23 oldugunu
        List<Integer> ages = jsonPath.getList("data.employee_age");
        System.out.println("ages = " + ages);
        List<Integer> ages30danKucuk = new ArrayList<>();
        int expectedEnBuyukYas = 23;
        int actualEnBuyukYas = 0;
        for (int i = 0; i < ages.size(); i++) {
            if (ages.get(i) < 30) {
                ages30danKucuk.add(ages.get(i));
            }
        }
        System.out.println("ages30danKucuk = " + ages30danKucuk);
        for (int i = 0; i < ages30danKucuk.size(); i++) {
            if (ages30danKucuk.get(i) > actualEnBuyukYas) {
                actualEnBuyukYas = ages30danKucuk.get(i);
            }
        }
        System.out.println("actual30danKucukEnBuyukYas = " + actualEnBuyukYas);
        assertEquals(expectedEnBuyukYas, actualEnBuyukYas);

        // *** groovy language ***
        List<Integer> otuzdanKucukYaslar = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("otuzdanKucukYaslar = " + otuzdanKucukYaslar);

        Collections.sort(otuzdanKucukYaslar);
        assertEquals(expectedEnBuyukYas, (int) otuzdanKucukYaslar.get(otuzdanKucukYaslar.size() - 1));

        // 3) Maasi 350000 den buyuk olan tum employee name'leri ekrana yazdirin ve bunlarin icerisinde "Charde Marshall" oldugunu
        String expectedEmployeeName = "Charde Marshall";
        List<Integer> employeeSalary = jsonPath.getList("data.employee_salary");
        System.out.println("employeeSalary = " + employeeSalary);
        int enBuyukSalaryIndex = 0;
        int enBuyukSalary = 0;

        for (int i = 0; i < employeeSalary.size(); i++) {
            if (employeeSalary.get(i) > enBuyukSalary) {
                enBuyukSalary = employeeSalary.get(i);
                enBuyukSalaryIndex = i;
            }
        }
        System.out.println("enBuyukSalary = " + enBuyukSalary);
        System.out.println("enBuyukSalaryIndex = " + enBuyukSalaryIndex);

        List<String> employeeNames = jsonPath.getList("data.employee_name");
        System.out.println("En yuksek maasli: " + employeeNames.get(enBuyukSalaryIndex));
        System.out.println("employeeNames = " + employeeNames);
        for (int i = 0; i < employeeNames.size(); i++) {
            if (employeeNames.get(i).equals(expectedEmployeeName)) {
                System.out.println("Charde Marshall'in Maasi 350000 den fazla oldugu dogrulandi. \nCharde Marshall'in Maasi: " + employeeSalary.get(i));
            }
        }
        // *** groovy language ***
        List<String> maasi35000denbuyukEmployees = jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("maasi35000denbuyukEmployees = " + maasi35000denbuyukEmployees);

        assertTrue(maasi35000denbuyukEmployees.contains("Charde Marshall"));
    }
}
