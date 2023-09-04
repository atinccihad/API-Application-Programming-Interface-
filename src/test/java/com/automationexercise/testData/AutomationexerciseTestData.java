package com.automationexercise.testData;

import java.util.HashMap;

public class AutomationexerciseTestData {

    public HashMap<String, Object> setupTestData() {
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);

        return expectedData;
    }
}
