package get_request;

import base_urls.MedunnaBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.Authentication.generateToken;

public class GetMedunna extends MedunnaBaseUrl {
    // https://www.medunna.com/api/physicians/147333
    /*
    {
        "id":147333,
        "firstname":"Doktor",
        "lastname":"Yusuf",
        "phone":"555-555-5555",
        "gender":"MALE",
        "user":{
            "login":"doktoryusuf",
        "email":"doktoryusuf@gmail.com",
        "ssn":"751-01-0103"
    },
        "speciality":"NUCLEAR_MEDICINE"
    }
     */

    @Test
    public void test01() {
        spec.pathParams("first", "physicians", "second", 147333);

        Response response = given()
                .spec(spec)
                .header("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // MATCHERS Class ile
        response.then().assertThat()
                .body("id", equalTo(147333)
                        , "firstName", equalTo("Doktor")
                        , "lastName", equalTo("Yusuf")
                        , "phone", equalTo("555-555-5555")
                        , "gender", equalTo("MALE")
                        , "user.login", equalTo("doktoryusuf")
                        , "user.email", equalTo("doktoryusuf@gmail.com")
                        , "user.ssn", equalTo("751-01-0103")
                        , "speciality", equalTo("NUCLEAR_MEDICINE"));

        // JSON PATH ile
        JsonPath json = response.jsonPath();
        assertEquals(147333, json.getInt("id"));
        assertEquals("Doktor", json.getString("firstName"));
        assertEquals("Yusuf", json.getString("lastName"));
        assertEquals("555-555-5555", json.getString("phone"));
        assertEquals("MALE", json.getString("gender"));
        assertEquals("doktoryusuf", json.getString("user.login"));
        assertEquals("doktoryusuf@gmail.com", json.getString("user.email"));
        assertTrue(json.getBoolean("user.activated"));
        assertEquals("751-01-0103", json.getString("user.ssn"));
        assertEquals("NUCLEAR_MEDICINE", json.getString("speciality"));
    }
}
