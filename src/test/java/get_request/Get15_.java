package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15_ extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
               "firstname": "Guoqiang",
               "lastname": "Morante Briones",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2018-01-01",
                   "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

    @Test
    public void get15() {
        // Set the Url
        spec.pathParams("first", "booking", "second", 22);

        // Set the expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Guoqiang", "Morante Briones", 111, true, bookingDatesPojo, "Breakfast");

        // Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);

        // Soft Assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(200, response.statusCode());
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname(), "firstname fail!");
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname(), "lastname fail!");
        softAssert.assertEquals(actualData.getTotalprice(), expectedData.getTotalprice(), "totalPrice fail!");
        softAssert.assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid(), "depositPaid fail!");
        softAssert.assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds(), "additionalNeeds fail!");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), bookingDatesPojo.getCheckin(), "checkin fail!");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), bookingDatesPojo.getCheckout(), "checkout fail!");

        softAssert.assertAll();

        // Hard Assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());

    }
}
