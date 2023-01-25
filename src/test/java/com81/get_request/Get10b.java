package com81.get_request;

import com81.base_urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10b extends DummyRestapiBaseUrl {
     /*
    https://dummy.restapiexample.com/api/v1/employees
    url'ine bir istek gonderildiginde
    status kodunun 200,
    gelen body de
    1) 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk 14 id oldugunu,
    2) 30'dan kucuk tum yaslari ekrana yazdirin ve bu yaslarin icerisinde en buyuk yasin 23 oldugunu
    3) Maasi 350000 den buyuk olan tum employe name'leri ekrana yazdirin ve bunlarin icerisinde "Charde Marshall" oldugunu test edin
     */

    @Test
    public void test10b() {
        spec.pathParam("parametre1", "employees");
        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}");

        JsonPath jsonPath = response.jsonPath();
        /*
        Groovy dili javanın alt dilidir.
        Biz bu dil yardimi ile loop kullanmadan gelen response'daki degerleri
        bir sarta bagli olarak listeye yazdirabiliyoruz.

        jsonPath.getList("data.findAll{it.id>10}.id");
        Ust satirdaki 'it' java'da kullandigimiz this. ile benzer ozellikte calisir
         */
        // status kodunun 200
        assertEquals("response.statusCode 200 degil!", 200, response.getStatusCode());

        // 1) 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk 14 id oldugunu,
        int count = 0;
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println("10'dan buyuk id'ler: " + ids);
        assertEquals("10'dan buyuk 14 id bulunmadi!", 14, ids.size());

        // 2) 30'dan kucuk tum yaslari ekrana yazdirin ve bu yaslarin icerisinde en buyuk yasin 23 oldugunu
        List<Integer> yaslar = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("30'dan kucuk yaslar = " + yaslar);
        Collections.sort(yaslar);
        System.out.println("30'dan kucuk en buyuk yas: " + yaslar.get(yaslar.size() - 1));
        assertEquals("30'dan kucuk en buyuk yas: 23 degil!", (Integer) 23, yaslar.get(yaslar.size() - 1));

        // 3) Maasi 350000 den buyuk olan tum employe name'leri ekrana yazdirin ve bunlarin icerisinde "Brielle Williamson" oldugunu test edin
        int beklenenMaas = 350000;
        List<String> maasaGoreIsımListesi = jsonPath.getList("data.findAll{it.employee_salary>"+beklenenMaas+"}.employee_name");
        System.out.println("Maasi " + beklenenMaas + "'den buyuk olan isimListesi = " + maasaGoreIsımListesi);
        assertTrue("employe name'ler icerisinde 'Brielle Williamson' bulunmuyor!", maasaGoreIsımListesi.contains("Brielle Williamson"));
    }
}
