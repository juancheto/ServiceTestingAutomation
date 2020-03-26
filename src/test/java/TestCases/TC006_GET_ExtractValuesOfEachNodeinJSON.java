package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006_GET_ExtractValuesOfEachNodeinJSON {

    @Test
    public void getWeatherDetails() {

        //Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/Envigado");

        JsonPath jsonPath = response.jsonPath();

        System.out.println((String) jsonPath.get("City"));
        System.out.println((String) jsonPath.get("Temperature"));
        System.out.println((String) jsonPath.get("Humidity"));
        System.out.println((String) jsonPath.get("WeatherDescription"));
        System.out.println((String) jsonPath.get("WindSpeed"));
        System.out.println((String) jsonPath.get("WindDirectionDegree"));
    }
}
