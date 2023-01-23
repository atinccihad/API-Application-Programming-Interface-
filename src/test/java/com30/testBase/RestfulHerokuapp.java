package com30.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulHerokuapp {

    protected RequestSpecification specRestful;

    @Before
    public void setUp(){
        specRestful = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuaoo.com/booking/5")
                .build();
    }

}
