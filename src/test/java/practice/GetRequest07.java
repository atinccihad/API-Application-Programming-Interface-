package practice;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import utilities.GMIBankBaseUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest07 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
   “firstName”: “Melva”,
   “lastName”: “Bernhard”,
   “email”: “chas.kuhlman@yahoo.com”
   “zipCode”: “40207"
   “country” “name”: “San Jose”
   “login”: “delilah.metz”
     */

    @Test
    public void gmiTest() {
        // Set the Url
        spec01.pathParams("first", "tp-customers", "second", 110472);

        // Send the Request Get the Response
        Response response = given().spec(spec01).headers("Authorization", "Bearer " + generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion to Matcher
        response.then().assertThat()
                .body("firstName", equalTo("Melva")
                        , "lastName", equalTo("Bernhard")
                        , "email", equalTo("chas.kuhlman@yahoo.com")
                        , "zipCode", equalTo("40207")
                        , "country.name", equalTo("San Jose")
                        , "user.login", equalTo("delilah.metz"));
        // Do assertion to Json Path
        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("firstName"), "Melva");
        softAssert.assertEquals(json.getString("lastName"), "Bernhard");
        softAssert.assertEquals(json.getString("email"), "chas.kuhlman@yahoo.com");
        softAssert.assertEquals(json.getString("zipCode"), "40207");
        softAssert.assertAll();

    }
}
