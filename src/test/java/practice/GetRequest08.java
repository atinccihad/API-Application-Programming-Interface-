package practice;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import utilities.GMIBankBaseUrl;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest08 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers/43703
          “firstName”: “Alda”,
          “lastName”: “Monahan”,
          “middleInitial”: “Nichelle Hermann Kohler”,
          “email”: “com.github.javafaker.Name@7c011174@gmail.com”,
          “mobilePhoneNumber”: “909-162-8114”,
          “city”: “St Louis”,
          “ssn”: “108-53-6655"
          1) MATCHERS CLASS
          2) JSON PATH
          3) De-Serialization
     */

    @Test
    public void test08() {
        // Set the Url
        spec01.pathParams("first", "tp-customers", "second", 43703);

        // Send the Request Get the Response
        Response response = given().spec(spec01).headers("Authorization", "Bearer " + generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion to MATCHERS CLASS
        response.then().assertThat()
                .body("firstName", equalTo("Alda")
                        , "lastName", equalTo("Monahan")
                        , "middleInitial", equalTo("Nichelle Hermann Kohler")
                        , "email", equalTo("com.github.javafaker.Name@7c011174@gmail.com")
                        , "mobilePhoneNumber", equalTo("909-162-8114")
                        , "city", equalTo("St Louis")
                        , "ssn", equalTo("108-53-6655"));

        // Do softAssertion to JSON PATH
        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("firstName"), "Alda");
        softAssert.assertEquals(json.getString("lastName"), "Monahan");
        softAssert.assertEquals(json.getString("middleInitial"), "Nichelle Hermann Kohler");
        softAssert.assertEquals(json.getString("email"), "com.github.javafaker.Name@7c011174@gmail.com");
        softAssert.assertEquals(json.getString("mobilePhoneNumber"), "909-162-8114");
        softAssert.assertEquals(json.getString("city"), "St Louis");
        softAssert.assertEquals(json.getString("ssn"), "108-53-6655");
        softAssert.assertAll();

        // De-Serialization
        Map<String, Object> actualData = response.as(HashMap.class);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstName", "Alda");
        expectedData.put("lastName", "Monahan");
        expectedData.put("middleInitial", "Nichelle Hermann Kohler");
        expectedData.put("email", "com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber", "909-162-8114");
        expectedData.put("city", "St Louis");
        expectedData.put("ssn", "108-53-6655");

        assertEquals("firstName fail!", expectedData.get("firstName"), actualData.get("firstName"));
        assertEquals("lastName fail!", expectedData.get("lastName"), actualData.get("lastName"));
        assertEquals("middleInitial fail!", expectedData.get("middleInitial"), actualData.get("middleInitial"));
        assertEquals("mobilePhoneNumber fail!", expectedData.get("mobilePhoneNumber"), actualData.get("mobilePhoneNumber"));
        assertEquals("city fail!", expectedData.get("city"), actualData.get("city"));
        assertEquals("ssn fail!", expectedData.get("ssn"), actualData.get("ssn"));
    }
}
