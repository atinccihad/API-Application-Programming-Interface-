package com30.testData;

import org.json.JSONObject;

import java.util.HashMap;

public class RestfulBookerHerokuappTestData {

    public HashMap<String, Object> setUpTestData() {

        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2019-06-21");
        bookingdates.put("checkout", "2022-10-29");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 865);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;
    }

    public JSONObject setUpTestAndRequestData() {
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-01-05");
        bookingdates.put("checkout", "2021-01-10");

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("firstname", "cihad");
        expectedRequest.put("lastname", "atinc");
        expectedRequest.put("totalprice", 123);
        expectedRequest.put("depositpaid", false);
        expectedRequest.put("bookingdates", bookingdates);

        return expectedRequest;
    }

}
