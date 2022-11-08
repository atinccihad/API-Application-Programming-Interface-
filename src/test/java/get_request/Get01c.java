package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get01c {
    /*
    https://restful-booker.herokuapp.com/booking/2 adresine request gonderildiginde
    donecek cevap response icin

    HTTP status kodunun 418
    Content Type'in text/plain; charset=utf-8
    Ve Status Line'in HTTP/1.1 418 I'm a Teapot
    Oldugunu test edin
     */

    @Test
    public void test01c() {
        // 1- api testi yaparken ilk olarak url(endpoint) belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/2";

        // 2- Beklenen sonuc(expected result) olusturulur
        // 3- request gonder
        Response response = given().
                accept(ContentType.JSON) // accept("application/json") da kullanilabilir.
                .when()
                .get(url);
        // response.prettyPrint();

        // 4- actual result olustur
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        System.out.println("response.getHeaders() = " + response.getHeaders()); // => response'dan tum headers'i dondurur

        // 5- Dogrulama yap(Do Assertion)
        // expected bize task olarak verilen degerdir actual olan ise response'dan gelen degerdir
        /*
        assertEquals("response.statusCode() 418 den farkli!",418, response.statusCode());
        assertEquals("response.getContentType()  'text/plain; charset=utf-8' ile uyusmuyor!","text/plain; charset=utf-8", response.getContentType());
        assertEquals("response.getStatusLine() 'HTTP/1.1 418 I'm a Teapot' ile uyusmuyor!","HTTP/1.1 418 I'm a Teapot", response.statusLine());
        */
        response.then()
                .assertThat()
                .statusCode(418)
                .contentType("text/plain; charset=utf-8")
                .statusLine("HTTP/1.1 418 I'm a Teapot");
    }
}
