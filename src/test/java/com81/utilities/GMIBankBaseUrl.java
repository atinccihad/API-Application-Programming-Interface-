package com81.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GMIBankBaseUrl extends Authentication{

    protected RequestSpecification spec01;

    @Before
    public void setUp() {
        spec01 = new RequestSpecBuilder().setBaseUri("http://www.gmibank.com/api/").build();
    }
}
