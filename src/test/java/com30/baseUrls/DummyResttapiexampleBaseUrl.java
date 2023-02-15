package com30.baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyResttapiexampleBaseUrl {

    protected RequestSpecification specDummy;

    @Before
    public void setUp() {
        specDummy = new RequestSpecBuilder().
                setBaseUri("https://dummy.restapiexample.com/api/v1").
                build();
    }
}
