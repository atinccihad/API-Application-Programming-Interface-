package get_request;

import base_urls.RestfulBaseUrl;
import org.junit.Test;

public class Get12Pojo extends RestfulBaseUrl {
        /*
         Given
             https://restful-booker.herokuapp.com/booking/18
         When
        I send GET Request to the URL
       Then
        Status code is 200
    And
        Response body is like:
                            {
                  "firstname": "Dane",
                  "lastname": "Combs",
                  "totalprice": 111,
                  "depositpaid": true,
                  "bookingdates": {
                      "checkin": "2018-01-01",
                      "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
      */

    @Test
    public void get12Pojo() {
        // Set the Url
        spec.pathParams("first","booking","second",18);

        // Set the Expected Data

    }
}
