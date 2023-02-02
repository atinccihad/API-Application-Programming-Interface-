package com30.i10;

import com30.testBase.DummyResttapiexampleTestBase;
import com30.testData.DummyRestApiTestData;
import org.junit.Test;

import java.util.HashMap;

public class PostRequest_01 extends DummyResttapiexampleTestBase {
    /*
       https://dummy.restapiexample.com/api/v1/employees url'ine post request olarak,
       {
      "name":"atinc",
      "salary":"55",
      "age":"35",
      "profile_image":""
      }


       seklinde oldugunu test edin.
        */

    @Test
    public void test() {
        specDummy.pathParam("first", "create");

        DummyRestApiTestData obje = new DummyRestApiTestData();

        // POST request yaparken body gondermek zorundayiz
        // Testdata class'inda olusturdugumuz request
        HashMap<String, String> requestBodyMap = obje.setUpRequestBody();

    }
}
