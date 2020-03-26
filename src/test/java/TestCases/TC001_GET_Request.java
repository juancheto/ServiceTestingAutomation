package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request {

    @Test
    public void getWeatherDetails() {

        //Specify bae URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET, "/Medellin");

        //Print response in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        //Status code validaion
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        //Status line verification
        String statusLine = response.getStatusLine();
        System.out.println("Status line: " + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
}
