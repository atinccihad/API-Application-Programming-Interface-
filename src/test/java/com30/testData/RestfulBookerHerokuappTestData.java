package com30.testData;

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
}
