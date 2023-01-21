package com81.get_request;

import com81.base_urls.AutoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08b extends AutoBaseUrl {
    /*
    Given
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
    public void get01() {
        spec.pathParam("first","productsList");
        Response response = given().spec(spec).when().get("/{first}");
        response.then().statusCode(200).contentType("text/html; charset=utf-8").statusLine("HTTP/1.1 200 OK");
        JsonPath json = response.jsonPath();

        List<String>women = json.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        List<String> men = json.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> kids = json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");

        assertEquals(12,women.size());
        assertEquals(9,men.size());
        assertEquals(13,kids.size());
    }
}