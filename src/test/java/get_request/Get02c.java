package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get02c {

    @Test
    public void test02c() {
        /*
    https://restful-booker.herokuapp.com/booking/ url'ine
    accept type'i "application/json" olan GET request gonderildiginde
    donecek cevap response icin

    HTTP status kodunun 200
    Content Type'in "application/json"
    Oldugunu test edin
     */
        // 1- Get the Url
        String url = "https://restful-booker.herokuapp.com/booking/";

        // 2- Beklenen sonuc(expected result) olusturulur
        // 3- request gonder
        Response response = given().accept("application/json").when().get(url);
        // response.prettyPrint();

        // 4- actual result olustur
        // 5- Dogrulama yap(Do Assertion)
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    public void test02d() {
        /*
    https://restful-booker.herokuapp.com/booking/10011 url'ine
    accept type'i "application/json" olan GET request gonderildiginde
    gelen response'un

    HTTP status kodunun 404
    ve Response body'sinin "Not Found" icerdigini
    ve Response body'sinin "API" icermedigini
    test edin
     */
        // 1- Get the Url
        String url = "https://restful-booker.herokuapp.com/booking/10011";

        // 2- Beklenen sonuc(expected result) olusturulur
        // 3- request gonder
        Response response = given().accept("application/json").when().get(url);
        // response.prettyPrint();

        // 4- actual result olustur
        // 5- Dogrulama yap(Do Assertion)
        String body = response.asString();
        response.then()
                .assertThat()
                .statusCode(404);
        assertTrue("Response body'si 'Not Found' icermiyor!", body.contains("Not Found"));
        assertFalse("Response body'si 'API' iceriyor!", body.contains("API"));

        // asString(); ile json formatinda gelen response'u Stringe cevirdik
    }

}
