package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_GET_Request_PrintAllHeaders {

    @Test
    void googleMapTestAllHeaders() {

        //Specify base URI
        RestAssured.baseURI = "https://www.google.com";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/maps/search/Restaurants/@6.1973644,-75.5858772,15z/data=!3m1!4b1");

        //print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        Headers allHeaders = response.headers(); //Capture all the headers from response

        for (Header header:allHeaders) {
            System.out.println(header.getName()+"     "+header.getValue());
        }
    }
}