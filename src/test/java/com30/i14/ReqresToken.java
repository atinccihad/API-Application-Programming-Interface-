package com30.i14;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {
    /*
    {
    "email": "eve.holt@reqres.in",
    "password": "cityslicka"
    }
     */
    public String generateToken() {
        String url = "https://reqres.in/api/login";

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");
        //System.out.println("requestBody = " + requestBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .auth().basic("admin", "password")
                .when()
                .post(url);
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.getString("token");

        return token;
    }
}
