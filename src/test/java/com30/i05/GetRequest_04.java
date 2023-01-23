package com30.i05;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest_04 {
    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine,
    accept type'i "application-json" olan get request'i yolladigimda gelen response'un
    status code : 200
    content-type : "application/json"
    ve firstname'in "Jim"
    ve totalprice'in 162
    ve checkin date'in "2018-07-03"
    oldugunu test edin.
            */

    @Test
    public void test() {
        String url = "https://restful-booker.herokuapp.com/booking/5";

        Response response = given()
                .accept("application/json")
                .when()
                .get(url);
        response.prettyPrint();
        /*
        Matchers class ile dogrulama yapmak icin Response class'indan olusturdugumuz objeyi kullanarak
        response.then()
                .assertThat()..... seklinde baslayip body icinde Matchers class'in
                ilgili methodlarini kullanarak dogrulamalarimizi yapabiliyoruz.
         */
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(                  "firstname", equalTo("Jim"),
                        "totalprice", equalTo(162),
                                             "bookingdates.checkin", equalTo("2018-07-03"));
    }
}
