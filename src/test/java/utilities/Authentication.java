package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {
/*
    public static void main(String[] args) {
        String guncelToken = generateToken();
        System.out.println(guncelToken);
    }
*/
    public static String generateToken() {
        String username = "Batch81";
        String password = "Batch81+";

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("rememberme", "true");
        // "password": "Batch81+",
        //    "rememberMe": true,
        //    "username": "Batch81"
        String endPoint = "https://www.medunna.com/api/authenticate";

        Response response1 = given().contentType(ContentType.JSON).body(map).when().post(endPoint);

        JsonPath token = response1.jsonPath();

        return token.getString("id_token");
    }
}
