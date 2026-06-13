package apiTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected static final String usr = "user1";
    protected static final String pwd = "1234";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://selenoid.autotests.cloud";
        RestAssured.basePath = "/wd/hub";
    }
}
