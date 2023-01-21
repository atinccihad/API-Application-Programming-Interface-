package com81.get_request;

import com81.base_urls.DummyRestapiBaseUrl;
import com81.pojos.DummyRestApiDataPojo;
import com81.pojos.DummyRestApiResponseBodyPojo;
import com81.utils.ObjectMapperUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestapiBaseUrl {
    /*
     Given
         URL: https://dummy.restapiexample.com/api/v1/employee/1
     When
         User sends GET Request
     Then
         Status code is 200
     And
         "employee_name" is "Tiger Nixon"
     And
         "employee_salary" is 320800
     And
         "employee_age" is 61
     And
         "status" is "success"
     And
         "message" is "Successfully! Record has been fetched."

      */
    @Test
    public void get17() {
        spec.pathParams("first", "employee", "second", 1);

        DummyRestApiDataPojo dummyRestApiDataPojo = new DummyRestApiDataPojo("Tiger Nixon", 320800, 61, "");
        DummyRestApiResponseBodyPojo expectedData = new DummyRestApiResponseBodyPojo("success", dummyRestApiDataPojo, "Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponseBodyPojo.class);
        System.out.println(actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());

        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
    }

}