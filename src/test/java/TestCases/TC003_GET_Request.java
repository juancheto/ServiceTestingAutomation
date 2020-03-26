package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_Request {

    @Test
    void googleMapTest() {

        //Specify base URI
        RestAssured.baseURI = "https://www.google.com";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/maps/search/Restaurants/@6.1973644,-75.5858772,15z/data=!3m1!4b1");

        //print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        //validating headers
        String contentType = response.header("Content-Type");// capture details of Content-Type header
        System.out.println("Content Type is:" + contentType);
        Assert.assertEquals(contentType, "text/html; charset=UTF-8");

        String contentEncoding = response.header("Content-Encoding");// capture details of Content-Encoding  header
        System.out.println("Content Encoding is:" + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }
}
