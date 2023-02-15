package com30.baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulBookerHerokuappBaseUrl {

    protected RequestSpecification specRestful;

    @Before
    public void setUp(){
        specRestful = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }

}
