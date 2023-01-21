package com30.i04;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest_01 {
    /*
    https://restful-booker.herokuapp.com/booking/3 adresine request gonderildiginde donecek response'un,
    HTTP status codu'nun 200,
    Content Type'in application/json; charset=utf-8,
    Ve Status Line'in HTTP/1.1 200 OK,
    oldugunu test edin.
    */

    @Test
    public void test() {
        // 1- API test yaparken ilk olarak url(end point) belirlenmeli)
        String url = "https://restful-booker.herokuapp.com/booking/3";

        // 2- Expected Result olusturulur(Bu case'de body dogrulamasi istenmedigi icin simdilik beklenen sonuc olusturmuyoruz)

        // 3- Request gonder
        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        // 4- Actual Result olusturulur(response body ile islem yapilmicagi icin simdi olusturmuyoruz)

        // 5- Assertion
        System.out.println("status code: " + response.getStatusCode());
        System.out.println("content type: " + response.getContentType());
        System.out.println("status line: " + response.getStatusLine());
        System.out.println("headers: " + response.getHeaders());
        /*
        assertEquals("StatusCode beklenenden farkli!", 200, response.getStatusCode());
        assertEquals("contentType beklenenden farkli!", "application/json; charset=utf-8", response.getContentType());
        assertEquals("statusLine beklenenden farkli!", "HTTP/1.1 200 OK", response.getStatusLine());
         */
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");
    }

}
