package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get02c {
    /*
    https://restful-booker.herokuapp.com/booking/ url'ine
    accept type'i "application/json" olan GET request gonderildiginde
    donecek cevap response icin

    HTTP status kodunun 200
    Content Type'in "application/json"
    Oldugunu test edin
     */

    @Test
    public void test02c() {
        // Get the Url
        String url = "https://restful-booker.herokuapp.com/booking/";

        // 2- Beklenen sonuc(expected result) olusturulur
        // 3- request gonder
        Response response = given().accept("application/json").when().get(url);
        response.prettyPrint();

        // 4- actual result olustur
        // 5- Dogrulama yap(Do Assertion)
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");
    }
}
