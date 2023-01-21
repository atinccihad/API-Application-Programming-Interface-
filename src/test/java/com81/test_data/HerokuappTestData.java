package com81.test_data;

import org.json.JSONObject;

import java.util.HashMap;

public class HerokuappTestData {
    /*
    {
    "firstname": "D1AC2",
    "lastname": "211DA",
    "totalprice": 12,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-11-15",
        "checkout": "2022-11-22"
    },
    "additionalneeds": "D2B3D"
}
     */

    public HashMap<String, Object> setupTestData() {
        HashMap<String, Object> bookingdates = new HashMap<String, Object>();
        bookingdates.put("checkin", "2022-11-15");
        bookingdates.put("checkout", "2022-11-22");

        HashMap<String, Object> expectedData = new HashMap<String, Object>();

        expectedData.put("firstname", "D1AC2");
        expectedData.put("lastname", "211DA");
        expectedData.put("totalprice", 12);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", "D2B3D");

        return expectedData;
    }
    public JSONObject setupTestAndRequestData() {

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2020-09-09");
        bookingdates.put("checkout", "2020-09-21");

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("firstname", "Cihad");
        expectedRequest.put("lastname", "Atınç");
        expectedRequest.put("totalprice", 11111);
        expectedRequest.put("depositpaid", true);
        expectedRequest.put("bookingdates", bookingdates);

        return expectedRequest;
    }
}
