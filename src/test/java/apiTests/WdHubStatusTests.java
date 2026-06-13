package apiTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class WdHubStatusTests extends TestBase {
    @Test
    @Description("UC01 Positive test: check response status 200 on normal request")
    public void checkResponseStatusNormalReqTest() {
        given()
                .auth().basic(usr, pwd)
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .statusCode(200);
    }

    @Test
    @Description("UC02 Positive test: check json schema")
    public void checkJsonSchemaTest() {
        given()
                .auth().basic(usr, pwd)
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/wd_hub_status_schema.json"));
    }

    @Test
    @Description("UC03 Positive test: check selenoid version in message field")
    public void checkValueInMessageFieldTest() {
        given()
                .auth().basic(usr, pwd)
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .statusCode(200)
                .body("value.message", containsString("Selenoid 1.11.3 built at"));
    }

    @Test
    @Description("UC04 Positive test: check ready field on ready status")
    public void checkStatusInReadyFieldTest() {
        given()
                .auth().basic(usr, pwd)
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    @Description("UC05 Negative test: check response status 401 on unauthorized request")
    public void checkRespStatusUnauthorizedReqTest() {
        given()
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .statusCode(401);
    }

    @Test
    @Description("UC06 Negative test: check response status 404 on wrong url request")
    public void checkRespStatusWrongReqTest() {
        given()
                .auth().basic(usr, pwd)
                .when()
                .get("/wd/huhb/status")
                .then()
                .statusCode(404);
    }

    @Test
    @Description("UC07 Negative test: check response status 401 on wrong username request")
    public void checkRespStatusWrongUnameTest() {
        given()
                .auth().basic("admin", pwd)
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    @Description("UC08 Negative test: check response status 401 on wrong password request")
    public void checkRespStatusWrongPwdTest() {
        given()
                .auth().basic(usr, "pwd")
                .when()
                .get(wdHubStatusApiUri)
                .then()
                .log().all()
                .statusCode(401);
    }
}
