package com30.i12;

import com30.testBase.DummyResttapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteRequest_01 extends DummyResttapiexampleTestBase {
    /*
    https://dummy.restapiexample.com/v1/delete/2 URL'ine bir DELETE request gonderildiginde,
    Donen response'un Status kodunun 200 ve response body'nin asagidaki gibi oldugunu test edin,
    {
    "status":"success",
    "data": "2",
    "message":"Successfully! Record has been deleted"
    }
     */

    @Test
    public void test() {
        specDummy.pathParams("first", "delete", "second", 2);

        DummyRestApiTestData testData = new DummyRestApiTestData();
        JSONObject expectedData = testData.setupDeleteExpectedData();

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specDummy)
                .auth().basic("admin", "password123")
                .when()
                .delete("/{first}/{second}");
        response.prettyPrint();

        // Matchers assertion
        response
                .then()
                .assertThat()
                .statusCode(expectedData.getInt("statusCode"))
                .body("status", equalTo(expectedData.getString("status")),
                        "data", equalTo(expectedData.getString("data")),
                        "message", equalTo(expectedData.getString("message")));
    }
}
