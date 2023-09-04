package com.automationexercise.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AutomationexercisebaseUrl {

    protected RequestSpecification specAutomation;

    @Before
    public void setUp(){
        specAutomation = new RequestSpecBuilder()
                .setBaseUri("https://automationexercise.com/api")
                .build();
    }
}
