package com81.practice;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest01 {

    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().when().get(url);
        //                  given().when().get(url);  end pointe gondermek icin request olusturduk.
        // Response response-> api tarafindan donen cevap

        // Response response = given().auth().basic("user","password").when().get(url);
        // basic aut ile request gondermek icin

        //response.prettyPrint(); // print response's body
        //response.prettyPeek(); // print response's body+ head
        //response.peek();

        //response.print(); // return to String data

        System.out.println("statusCode: " + response.statusCode());
        System.out.println("statusLine: " + response.statusLine());
        System.out.println("contentType: " + response.contentType());

        // 1- JUnit assertleri ile API test yapabiliriz
        assertEquals("assertionError!", 200, response.getStatusCode());
        assertEquals("assertionError!", "HTTP/1.1 200 OK", response.statusLine());
        assertEquals("assertionError!", "application/json; charset=utf-8", response.contentType());

        // 2- assertThat ile
        response.then().assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json; charset=utf-8");

        /*
         response.then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json; charset=utf-8");

                .assertThat() olmadanda calisir.
         */

    }
}
