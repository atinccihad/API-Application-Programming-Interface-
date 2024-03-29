package com81.get_request;


import com81.base_urls.RestfulBaseUrl;
import io.restassured.response.*;
import org.junit.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
     */

    @Test
    public void get01(){
        // https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz
        // 1. Set The URL
        spec.pathParam("first","booking").queryParams("firstname","Kimie","lastname","Jackie");

        // 2. Set The Expected Data

        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        assertEquals(200,response.getStatusCode());
        assertFalse(response.asString().contains("bookingid"));
    }

}