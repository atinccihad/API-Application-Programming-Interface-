package com81.practice;


import com81.base_urls.AutomationBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest03 extends AutomationBaseUrl {
       /*
           Matchers ile dataları doğrulayınız
             "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
     */


    /* Given
    https://automationexercise.com/api/productsList
    When
    User sends a GET Request to the url
    Then
    HTTP Status Code should be 200
    And
    Content Type should be "text/html; charset=utf-8"
    And
    Status Line should be HTTP/1.1 200 OK
            And
    There must be 12 Women, 9 Men, 13 Kids usertype in products
   */
    @Test
    public void test1() {

        SoftAssert softAssert = new SoftAssert();
        spec.pathParams("first", "api", "second", "productsList");
        Response response = given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath = response.jsonPath();
        //jsonPath.prettyPrint();

        softAssert.assertEquals(200, response.getStatusCode(), "Status Code Hatalı");
        softAssert.assertEquals("text/html; charset=utf-8", response.getContentType(), "Content Hatalı");
        softAssert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

        List<String> usertypeMenListi = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> usertypeWomenListi = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        List<String> usertypeKidsListi = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");

        softAssert.assertEquals(12, usertypeWomenListi.size(), "Bayan Sayısı 12 Değil ");
        softAssert.assertEquals(9, usertypeMenListi.size());
        softAssert.assertEquals(13, usertypeKidsListi.size());

        softAssert.assertAll(); // perform gibi
    }
}
