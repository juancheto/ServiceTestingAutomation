package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_GET_ValidatingJSONResponse {

    @Test
    public void getWeatherDetails() {

        //Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/Envigado");

        //print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        Assert.assertTrue(responseBody.contains("Envigado"));
    }
}
