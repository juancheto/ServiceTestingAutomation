package TestCases;

import Utilities.RestUtils;
import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC012_DELETE_Employee_Record extends TestBase {

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("*****Started TC012_DELETE_Employee_Record*****");

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest=RestAssured.given();

        response = httpRequest.request(Method.GET, "/employees");
        Thread.sleep(3000);

        //First get jsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        //Capture id
        String empID = jsonPathEvaluator.get("[3].id");
        response = httpRequest.request(Method.DELETE, "/delete/"+empID); //Pass ID to delete record
        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody() {
        logger.info("*****Checking Response Body*****");

        String responseBody = response.getBody().asString();
        logger.info("Response Body==> " + responseBody);
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
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

    @Test
    void checkContentType() {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json;charset=utf-8");
    }

    @Test
    void checkServerType() {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    void checkContentEncoding() {
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, null);
    }

    @AfterClass
    void tearDown() {
        logger.info("*****Finished TC012_DELETE_Employee_Record*****");
    }


}
