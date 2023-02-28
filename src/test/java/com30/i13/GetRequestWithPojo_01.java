package com30.i13;

import com30.baseUrls.DummyResttapiexampleBaseUrl;
import com30.pojos.Data;
import com30.pojos.DummyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestWithPojo_01 extends DummyResttapiexampleBaseUrl {
    /*
    GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
    Status code is 200,
    {
    "status":"success",
    "data":{
      "id":1,
      "employee_name":"Tiger Nixon",
      "employee_salary":320200,
      "employee_age":61,
      "profile_image":""
            },
       "message":"Successfully' Record has been fetched."
      }
      verify to data..
     */

    @Test
    public void test() {

        specDummy.pathParams("first", "employee",
                "second", 1);

        Data data = new Data(1, "Tiger Nixon", 320800, 61, "");

        DummyPojo expectedData =
                new DummyPojo("success", data, "Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specDummy)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        DummyPojo actualData = response.as(DummyPojo.class);
        System.out.println("actualData = " + actualData);
        // Status code is 200
        assertEquals(200, response.getStatusCode());
        // "status":"success"
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        // "employee_name":"Tiger Nixon"
        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        // "id":1
        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        // "employee_age":61
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        // "employee_salary":320200
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        // "profile_image":""
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
        // "message":"Successfully' Record has been fetched."
        assertEquals(expectedData.getMessage(), actualData.getMessage());
    }
}
