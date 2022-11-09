package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get07c extends RestfulBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking/5  url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen responce'un
    Status code : 200
    ve content type'inin "application/json; charset=utf-8"
    ve Headers'daki "Server" in "Cowboy
    ve response body'sinin asagidaki gibi oldugunu test edin
    {
    "firstname": "James",
    "lastname": "Brown",
    "totalprice": 228,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-10-16",
        "checkout": "2022-07-08"
    }
}
     */

    @Test
    public void test07c() {
        spec.pathParams("parametre1", "booking", "parametre2", 5);
        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}/{parametre2}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("Cowboy", response.getHeader("Server"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals("James", jsonPath.getString("firstname"));
        assertEquals("Brown", jsonPath.getString("lastname"));
        assertEquals(228, jsonPath.getInt("totalprice"));
        assertEquals(false, jsonPath.getBoolean("depositpaid"));
        assertEquals("2021-10-16", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2022-07-08", jsonPath.getString("bookingdates.checkout"));
    }
}
