package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.Authentication;

public class MedunnaBaseUrl extends Authentication {
    protected RequestSpecification spec;
    @Before
    public void setup(){
        spec=new RequestSpecBuilder().setBaseUri("https://www.medunna.com/api/").build();
    }
}
