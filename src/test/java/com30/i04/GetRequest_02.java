package com30.i04;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest_02 {

    @Test
    public void test01() {
        /*
    https://restful-booker.herokuapp.com/booking url'ine,
    accept type'i "application/json; charset=utf-8" olan GET request yolladigimda,
    gelen response'un,
    status codu'nun 200,
    Content Type'in application/json; charset=utf-8,
    oldugunu test edin.
    */
        // url olustur
        String url = "https://restful-booker.herokuapp.com/booking";

        // expected datayi olustur(body gerekmedigi icin olusturmuyoruz)

        // request gonder
        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test
    public void test02() {
              /*
    https://restful-booker.herokuapp.com/booking/10011 url'ine,
    accept type'i "application/json; charset=utf-8" olan GET request yolladigimda,
    gelen response'un,
    status codu'nun 404,
    Response body'sinin "Not Found" icerdigini
    Response body'sinin "API" icermedigini test edin.
    */
        // url olustur
        String url = "https://restful-booker.herokuapp.com/booking/10011";

        // expected datayi olustur(body gerekmedigi icin olusturmuyoruz)

        // request gonder
        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(404);
        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("API"));
        // asString methodu ile json formatinda gelen response'u Stringe cevirdik
    }
}
