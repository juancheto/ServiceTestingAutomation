package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {

    @Test
    public void createCustomer() {

        //Specify base URI
        RestAssured.baseURI="http://restapi.demoqa.com/customer";

        //Request object
        RequestSpecification httpRequest=RestAssured.given();

        //Request paylaod sending along with post request
        JSONObject requestParams=new JSONObject();

        requestParams.put("FirstName","a3JohnXYZ1");
        requestParams.put("LastName","a3XYZJohn1");
        requestParams.put("UserName","a3JohnXYZ1");
        requestParams.put("Password","a3JohnXYZxyx1");
        requestParams.put("Email","a3JohnXYZ1@gmail.com");

        httpRequest.header("Content-Type","application/json");

        httpRequest.body(requestParams.toJSONString()); // attach above data to the request

        //Response object
        Response response=httpRequest.request(Method.POST,"/register");

        //print response in console window

        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:" +responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode, 201);

        //success code validation
        String successCode=response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");

    }
}