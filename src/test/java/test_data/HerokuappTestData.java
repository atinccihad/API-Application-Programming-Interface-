package test_data;

import java.util.HashMap;

public class HerokuappTestData {
    /*
    {
    "firstname": "Edgar",
    "lastname": "Liu",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

    public  HashMap<String, Object> setupTestData() {
        HashMap<String, Object> bookingdates = new HashMap<String, Object>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        HashMap<String, Object> expectedData = new HashMap<String, Object>();

        expectedData.put("firstname", "Alex");
        expectedData.put("lastname", "Sevilla");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", "Breakfast");

        return expectedData;
    }
}
