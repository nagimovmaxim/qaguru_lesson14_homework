package apiTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public static String usr;
    public static String pwd;
    public static String wdHubStatusApiUri;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://selenoid.autotests.cloud";
        usr = "user1";
        pwd = "1234";
        wdHubStatusApiUri = "/wd/hub/status";
    }
}
