package com81.test_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyRestapiTestData {
    /*
    {
              "id": 11,
              "employee_name": "Jena Gaines",
              "employee_salary": 90560,
              "employee_age": 30,
              "profile_image": ""
          }
     */
    public HashMap<String, Object> setuptestData() {
        List<Integer> yaslar = new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String, Object> onbirinci = new HashMap<String, Object>();
        onbirinci.put("id", 11);
        onbirinci.put("employee_name", "Jena Gaines");
        onbirinci.put("employee_salary", 90560);
        onbirinci.put("employee_age", 30);
        onbirinci.put("profile_image", "");

        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("besincicalisan", "Airi Satou");
        expectedData.put("calisansayisi", 24);
        expectedData.put("sondanikincicalisanmaasi", 106450);
        expectedData.put("arananyaslar", yaslar);
        expectedData.put("onbirincicalisan", onbirinci);

        return expectedData;
    }

    public HashMap<String, Integer> setupTestData02() {
        HashMap<String, Integer> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("enYuksekMaas", 725000);
        expectedData.put("enKucukYas", 19);
        expectedData.put("ikinciYuksekMaas", 675000);

        return expectedData;
    }

    public HashMap<String, String> setupRequestBody() {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Cihad Atınç");
        requestBody.put("salary", "1000");
        requestBody.put("age", "34");
        requestBody.put("profile_image", "https://github.com/atinccihad");

        return requestBody;
    }

    public HashMap<String, Object> setupExpectedData() {

        //   HashMap<String, Object> data = new HashMap<>();
        //   data.put("name", "Cihad Atınç");
        //   data.put("salary", "1000");
        //   data.put("age", "34");
        //   data.put("profile_image", "https://github.com/atinccihad");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("status", "success");
        //  expectedData.put("data", data);
        expectedData.put("message", "Successfully! Record has been added.");

        return expectedData;
    }
}
