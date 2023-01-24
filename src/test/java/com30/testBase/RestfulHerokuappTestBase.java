package com30.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulHerokuappTestBase {

    protected RequestSpecification specRestful;

    @Before
    public void setUp(){
        specRestful = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }

}
