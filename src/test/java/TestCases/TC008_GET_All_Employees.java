package TestCases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC008_GET_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees() {
        logger.info("*****Started TC008_GET_All_Employees*****");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");
    }

    @Test
    void checkResponseBody() {
        logger.info("*****Checking Response Body*****");

        String responseBody = response.getBody().asString();
        logger.info("Response Body==> " + responseBody);
        Assert.assertTrue(responseBody!=null);
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

        if (responseTime>2000)
            logger.warn("Response Time is greater than 2000");
        Assert.assertTrue(responseTime<2000);
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
        logger.info("*****Finished TC008_GET_All_Employees*****");
    }
}
