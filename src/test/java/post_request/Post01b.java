package post_request;

import base_urls.DummyRestapiBaseUrl;
import org.junit.Test;
import test_data.DummyRestapiTestData;

import java.util.HashMap;

public class Post01b extends DummyRestapiBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/create  url'ine, Request Body olarak
    {
    "name":"Cihad Atınç",
    "salary":"1000",
    "age":"34",
    "profile_image":"https://github.com/atinccihad"
    }
    gonderildiginde status code : 200 oldugunu
    ve donen response body'nin,
    {
    "status": "success",
    "data": {
        "name": "Cihad Atınç",
        "salary": "1000",
        "age": "34",
        "profile_image": "https://github.com/atinccihad",
        "id": 5202
    },
    "message": "Successfully! Record has been added."
   }
   oldugunu test edin.
     */

    @Test
    public void post01b() {
        // Set the Url
        spec.pathParam("parametre1", "create");

        DummyRestapiTestData obje = new DummyRestapiTestData();
        /*
         post request yaparken body gondermek zorundayiz,
         testdata classında olusturdugumuz request body'i burada cagiriyoruz.
        */
        HashMap<String, String> requestBodyMap = obje.setupRequestBody();



    }
}
