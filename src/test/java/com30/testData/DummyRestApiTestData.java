package com30.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyRestApiTestData {

    public HashMap<String, Object> setUpTestData() {
        List<Integer> yaslar = new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String, Object> onbirinci = new HashMap<>();
        onbirinci.put("id", 11);
        onbirinci.put("employee_name", "Jena Gaines");
        onbirinci.put("employee_salary", 90560);
        onbirinci.put("employee_age", 30);
        onbirinci.put("profile_image", "");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("arananYaslar", yaslar);
        expectedData.put("onbirinciCalisan", onbirinci);

        return expectedData;
    }
}
