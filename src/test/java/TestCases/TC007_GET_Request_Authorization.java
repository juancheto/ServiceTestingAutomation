package TestCases;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC007_GET_Request_Authorization {

    @Test
    public void authorization() {

        //Specify bae URI
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

        //Basic Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");

        RestAssured.authentication = authScheme;

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET, "/");

        //Print response in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        //Status code validaion
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
