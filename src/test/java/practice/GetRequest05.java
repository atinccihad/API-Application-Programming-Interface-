package practice;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;

public class GetRequest05 {

    @Test
    public void tets06() {

        String url ="https://www.gmibank.com/api/tp-customers/114351";

        Response response = given().headers("Authorization","Bearer "+ generateToken()).when().get(url);
        response.prettyPrint();
        //Matcher class ile musteri bilgilerini dogrulayin
        response.then().assertThat().body("firstName",equalTo("Della"),
                "lastName",equalTo("Heaney"),
                "email",equalTo("ricardo.larkin@yahoo.com"),
                "mobilePhoneNumber",equalTo("123-456-7893"),"accounts[0].balance",equalTo(11190),
                "accounts[0].accountType",equalTo("CHECKING"),
                "accounts[1].balance",equalTo(69700),
                "accounts[1].accountType",equalTo("CREDIT_CARD"));




        //JsonPatl ile musteri bilgilerini dogrulayin
    }
}
