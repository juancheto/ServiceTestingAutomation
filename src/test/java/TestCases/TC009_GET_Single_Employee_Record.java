package TestCases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC009_GET_Single_Employee_Record extends TestBase {

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("*****Started TC009_GET_Single_Employee_Record*****");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employee/" + empID);
        Thread.sleep(10000);
    }

    @Test
    void checkResponseBody() {
        logger.info("*****Checking Response Body*****");

        String responseBody = response.getBody().asString();
        logger.info("Response Body==> " + responseBody);
        Assert.assertEquals(responseBody.contains(empID), true);
    }

    @Test
    void checkStatusCode() {
        logger.info("*****Checking Status Code*****");

        int statusCode = response.getStatusCode();
        logger.info("Response Code==> " + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkResponseTime() {
        logger.info("*****Checking Response Time*****");

        long responseTime = response.getTime();
        logger.info("Response Time==> " + responseTime);

        if (responseTime>20000)
            logger.warn("Response Time is greater than 2000");
        Assert.assertTrue(responseTime<20000);
    }

    @Test
    void checkStatusLine() {
        logger.info("*****Checking Status Line*****");

        String statusLine = response.getStatusLine();
        logger.info("Status Line: " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @AfterClass
    void tearDown() {
        logger.info("*****Finished TC009_GET_Single_Employee_Record*****");
    }
}
