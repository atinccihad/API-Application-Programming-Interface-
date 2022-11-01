package base_urls;

import io.restassured.builder.*;
import io.restassured.specification.*;
import org.junit.*;

public class JsonplaceholderBaseUrl {
   protected RequestSpecification spec;

    @Before
    public void setup() {
        spec = new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
    }
}
