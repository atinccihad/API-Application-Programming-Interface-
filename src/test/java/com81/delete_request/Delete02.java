package com81.delete_request;

import com81.base_urls.DummyRestapiBaseUrl;
import com81.pojos.DummyRestApiDeletePojo;
import com81.utils.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestapiBaseUrl {
       /*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"

       */

    /*
    Given
    URL: https://dummy.restapiexample.com/api/v1/delete/2
    When
    User sends Delete Request and gets response
    Then
    i) Status code is 200
    And
    ii) "status" is "success"
    And
    iii) "data" is "2"
    And
    iv) "message" is "Successfully! Record has been deleted"

     */
    @Test
    public void delete02() {
        spec.pathParams("first", "delete", "second", 2);
        DummyRestApiDeletePojo expectedData = new DummyRestApiDeletePojo("success", "2", "Successfully! Record has been deleted");

        System.out.println("expectedData = " + expectedData);
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();
        DummyRestApiDeletePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiDeletePojo.class);

        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getData(), actualData.getData());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
    }

}