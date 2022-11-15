package get_request;

import base_urls.DummyRestapiBaseUrl;
import org.junit.Test;

public class Get13b extends DummyRestapiBaseUrl {
/*
    https://dummy.restapiexample.com/api/v1/employees  url'ine istek gonderildiginde,
    Status kodun 200 oldugunu
    5. calisan isminin "Airi Satou"  oldugunu,
    calisan sayisinin 24 oldugunu,
    sondan 2. calisanin maasinin 106450 oldugunu,
    40, 21, 19 yaslarinda calisanlar olup olmadigini,
    11. calisan bilgilerinin

    {
            "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
        }
        seklinde oldugunu test edin.
 */

    @Test
    public void test13b() {
        // Set the Url
        spec.pathParam("parametre1","employees");





    }
}
