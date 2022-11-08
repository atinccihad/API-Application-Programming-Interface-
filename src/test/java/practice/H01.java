package practice;

import base_urls.AutomationBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class H01 extends AutomationBaseUrl {
    @Test
    public void test01() {
        // Given https://automationexercise.com/api/brandsList
        spec.pathParams("first","api","second","brandsList");
        // Set The Expected Data
        // Send The Request and Get The Response
        // When User sends a GET Request to the url
        String hm = "H&amp;M";
        String polo = "Polo";
        int hmNum=0;
        int poloNum=0;
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        // Then HTTP Status Code should be 200
        response.then().statusCode(200).contentType("text/html; charset=utf-8").statusLine("HTTP/1.1 200 OK");
        // And Content Type should be "text/html; charset=utf-8"
        // And Status Line should be HTTP/1.1 200 OK

        // And Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)

    }

}
