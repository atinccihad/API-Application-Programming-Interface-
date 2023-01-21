package com81.practice;

import com81.pojos.Customer;
import com81.utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Test;
import com81.pojos.Country;
import com81.pojos.User;
import com81.utilities.GMIBankBaseUrl;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends GMIBankBaseUrl {
        /*
        http://www.gmibank.com/api/tp-customers/110452
         */
    @Test
    public void test09() {
        spec01.pathParams("bir", "tp-customers", "iki", 110452);

        // EXPECTED DATA
        /*
            /*
        "user": {
        "id": 110016,
        "login": "leopoldo.reinger",
        "firstName": "Jasmine",
        "lastName": "Stehr",
        "email": "marni.zboncak@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    }
     */

        // Account [] accounts;
        User user = new User(110016, "leopoldo.reinger", "Jasmine", "Stehr",
                "marni.zboncak@yahoo.com", true, "en", null, null);

        Country country = new Country(3, "USA", null);

        Customer expectedData = new Customer(110452, "Jasmine", "Stehr", "V", "marni.zboncak@yahoo.com"
                , "463-609-2097", "1-112-497-0270", "16525", "14387 Al Ridge5343 Bert Burgs", "Waltermouth"
                , "761-59-2911", "2021-11-28T21:00:00Z", false, country, "California", user);

        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec01).headers("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{bir}/{iki}");

        //response.prettyPrint();
        Customer actualData = response.as(Customer.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getUser().getLogin(), actualData.getUser().getLogin());
        assertEquals(expectedData.getCountry().getName(), actualData.getCountry().getName());

        // Object Mapper
        Customer actualData2 = JsonUtil.convertJsonToJava(response.asString(), Customer.class);
        System.out.println("actualData2 = " + actualData2);

        assertEquals(expectedData.getId(),actualData2.getId());
        assertEquals(expectedData.getUser().getLogin(),actualData2.getUser().getLogin());
        assertEquals(expectedData.getCountry().getName(),actualData2.getCountry().getName());

    }
}