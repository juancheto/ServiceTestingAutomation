package TestCases;

import Utilities.RestUtils;
import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC011_PUT_Employee_Record extends TestBase {

    String empName = RestUtils.empName();
    String empSalary = RestUtils.empSal();
    String empAge = RestUtils.empAge();

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("*****Started TC011_PUT_Employee_Record*****");

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest=RestAssured.given();

        JSONObject requestParams=new JSONObject();

        requestParams.put("name",empName);
        requestParams.put("salary",empSalary);
        requestParams.put("age",empAge);

        httpRequest.header("Content-Type","application/json");

        httpRequest.body(requestParams.toJSONString()); // attach above data to the request

        response=httpRequest.request(Method.PUT,"/update/"+empID);
        Thread.sleep(5000);
    }

    @Test
    void checkResponseBody() {
        logger.info("*****Checking Response Body*****");

        String responseBody = response.getBody().asString();
        logger.info("Response Body==> " + responseBody);
        Assert.assertEquals(responseBody.contains(empName), true);
        Assert.assertEquals(responseBody.contains(empSalary), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
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
        logger.info("*****Finished TC011_PUT_Employee_Record*****");
    }


}
