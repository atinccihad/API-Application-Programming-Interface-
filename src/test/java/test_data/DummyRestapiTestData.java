package test_data;

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
    public HashMap<String,Object> setuptestData(){
        List<Integer>employeeAges = new ArrayList<>();
        employeeAges.add(40);
        employeeAges.add(21);
        employeeAges.add(19);

        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("id",11);
        expectedData.put("employee_name", "Jena Gaines");
        expectedData.put("employee_salary", 90560);
        expectedData.put("employee_age", 30);
        expectedData.put("profile_image", "");

        return expectedData;
    }
}
