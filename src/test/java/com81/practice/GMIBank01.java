package com81.practice;

import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import com81.pojos.Customer;
import com81.utilities.GMIBankBaseUrl;
import com81.utilities.ReadToText;
import com81.utilities.WriteToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank01 extends GMIBankBaseUrl {

     /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin
     1) Tüm Customer bilgilerini ekrana yazdırın.
     2) Tüm Customer SSN lerini ekrana yazdırın.
     3) Tüm Customer SSN lerini text dosyası olarak kaydedin
     4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
     */

    @Test
    public void test10() throws IOException {
        Customer[] customers;

        spec01.pathParam("first", "tp-customers");

        Response response = given().spec(spec01).headers("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{first}");
        //response.prettyPrint();

        ObjectMapper obj = new ObjectMapper();
        customers = obj.readValue(response.asString(), Customer[].class);

        // 1) Tüm Customer bilgilerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++)
            System.out.println(customers[i]);

        // 2) Tüm Customer SSN lerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++)
            System.out.println("SSN ler: " + customers[i].getSsn());

        // 3) Tüm Customer SSN lerini text dosyası olarak kaydedin
        String fileName = "src/test/java/practice/SSNNumbers.txt";
        WriteToText.saveSSNData(fileName, customers);

       // 4) Olusturduğunuz text dosyasından  SSNleri okuyarak "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
       List<String> expectedSSN = new ArrayList<>();
       expectedSSN.add("531-95-8437");
       expectedSSN.add("049-43-2360");
       expectedSSN.add("123-34-3434");

       List<String> actualSNN = ReadToText.readCustomerSSNList(fileName);

       Assert.assertTrue("SSNLER eşleşmedi", actualSNN.containsAll(expectedSSN));

       String fileName1 = "src/test/java/practice/AllData.txt";
       WriteToText.saveCustomersData(fileName1, customers);

    }
}