package com30.testData;

import java.util.HashMap;

public class RestfulBookerHerokuappTestData {

    public HashMap<String, Object> setUpTestData() {

        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2021-10-10");
        bookingdates.put("checkout", "2022-08-02");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Eric");
        expectedData.put("lastname", "Wilson");
        expectedData.put("totalprice", 728);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;
    }
}
