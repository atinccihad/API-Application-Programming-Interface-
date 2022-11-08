package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03c {
     /*
    https://restful-booker.herokuapp.com/booking/7/ url'ine
    accept type'i "application/json" olan GET request gonderildiginde
    donecek cevap response icin

    HTTP status kodunun 200
    ve content Type'in "application/json"

    ve firstname'in "Eric"
    ve lastname'in "Jones"
    ve checkin date'in "2016-07-24"
    ve checkout date'in "2022-10-21"
    Oldugunu test edin
     */

    @Test
    public void test03c() {
        /*
        {
           "firstname": "Eric",
           "lastname": "Jones",
           "totalprice": 565,
           "depositpaid": false,
           "bookingdates": {
               "checkin": "2016-07-24",
               "checkout": "2022-10-21"
           },
           "additionalneeds": "Breakfast"
           }
         */
        String url = "https://restful-booker.herokuapp.com/booking/7/";
        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();
        /*
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", Matchers.equalTo("Eric"))
                .body("lastname", Matchers.equalTo("Jones"))
                .body("totalprice", Matchers.equalTo(565))
                .body("depositpaid", Matchers.equalTo(false))
                .body("bookingdates.checkin", Matchers.equalTo("2016-07-24"))
                .body("bookingdates.checkout", Matchers.equalTo("2022-10-21"))
                .body("additionalneeds", Matchers.equalTo("Breakfast"));
                */
        response.then()
                .assertThat()
                .contentType("application/json")
                .body("firstname", equalTo("Eric"),
                        "lastname", equalTo("Jones"),
                        "totalprice", equalTo(565),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2016-07-24"),
                        "bookingdates.checkout", equalTo("2022-10-21"),
                        "additionalneeds", equalTo("Breakfast"));

    }
}
