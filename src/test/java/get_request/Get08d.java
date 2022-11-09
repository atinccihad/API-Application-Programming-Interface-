package get_request;

import base_urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08d extends DummyRestapiBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/employees  url'inde bulunan

    1) Butun calisanlarin isimlerini konsola yazdiralim
    2) 3. calisan kisinin ismini konsola yazdiralim
    3) Ilk 5 calisanin isimlerini konsola yazdiralim
    4) Son 5 calisanin isimlerini konsola yazdiralim
     */

    @Test
    public void test08d() {
        spec.pathParam("parametre1", "employees");
        Response response = given()
                .accept("application/json")
                .spec(spec)
                .when()
                .get("/{parametre1}");
        // response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        // 1) Butun calisanlarin isimlerini konsola yazdiralim
        System.out.println(jsonPath.getList("data.employee_name"));
        System.out.println(jsonPath.getString("data.employee_name"));

        // 2) 3. calisan kisinin ismini konsola yazdiralim
        System.out.println(jsonPath.getString("data[2].employee_name"));

        // 3) Ilk 5 calisanin isimlerini konsola yazdiralim
        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));

        // 4) Son 5 calisanin isimlerini konsola yazdiralim
        System.out.println(jsonPath.getString("data.employee_name[-1,-2,-3,-4,-5]"));

        assertEquals(200, response.statusCode());
        assertEquals("Tiger Nixon", jsonPath.getString("data[0].employee_name"));
    }
}
