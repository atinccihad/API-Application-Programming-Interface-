package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get06 extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get01() {

        // 1. Set The URL ( URL Olustur)
        spec.pathParams("first", "booking", "second", 2325);

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response ( Talep gonder ve cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4. Do Assertion ( Dogrulama Yap)
        /*
         Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": { // outer json
        "checkin": "2022-10-27", // inner json
        "checkout": "2022-11-07" // inner json
    },
    "additionalneeds": "None"
}
         */
        // 1. Yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("John"),
                        "lastname", equalTo("Kowalsky"),
                        "totalprice", equalTo(10150),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2023-01-01"),
                        "bookingdates.checkout", equalTo("2023-02-01"),
                        "additionalneeds", equalTo("Lunch"));

        // 2. Yol : Jsonpath class'nın kullanimi
        JsonPath json = response.jsonPath();

        assertEquals("John", json.getString("firstname"));
        assertEquals("Kowalsky", json.getString("lastname"));
        assertEquals(10150, json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2023-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2023-02-01", json.getString("bookingdates.checkout"));
        assertEquals("Lunch", json.getString("additionalneeds"));

        // 3. Yol : Soft Assertion
        // softAssert class 3 adimda kullanilir

        // i) Obje olusturma
        SoftAssert softAssert = new SoftAssert();

        // ii) Do Assertion ( dogrulama Yapma)
        softAssert.assertEquals(json.getString("firstname"), "John", "First Name Hatali");
        softAssert.assertEquals(json.getString("lastname"), "Kowalsky", "Last Name Hatali");
        softAssert.assertEquals(json.getInt("totalprice"), 10150, "Total Price Hatali");
        softAssert.assertEquals(json.getBoolean("depositpaid"), true, "Depositpaid Hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2023-01-01", "Check In Tarihi Hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2023-02-01", "Check out Tarihi Hatali");
        softAssert.assertEquals(json.getString("additionalneeds"), "Lunch", "Additionalneeds Hatali");

        softAssert.assertAll();

        /*
          iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
         sagliyoruz.
         Eger islemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz hatalı bile olsa testimiz pass olacaktir.
         */

    }

}