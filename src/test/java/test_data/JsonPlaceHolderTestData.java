package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public Map<String, Object> expectedDataMethod(Integer userId, String title, Boolean completed) {


        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("userId", 1);
        expectedDataMap.put("title", "quis ut nam facilis et officia qui");
        expectedDataMap.put("completed", false);

        return expectedDataMap;
    }


}
