package datadriventesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class DataDrivenTest_AddNewEmployee {

    @Test(dataProvider="EmpDataProvider")
    public void postNewEmployee(String ename, String eage, String esal) {

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest = RestAssured.given();

        //Here we created data which we can send along  with the post request
        JSONObject requestParam = new JSONObject();

        requestParam.put("name",ename);
        requestParam.put("salary",esal);
        requestParam.put("age",eage);

        //Add a header stating the Request body is a JSON
        httpRequest.header("Content-Type","application/json");

        //Add JSON to the body of the request
        httpRequest.body(requestParam.toJSONString());

        //Post Request
        Response response = httpRequest.request(Method.POST,"/create");

        //Capture response body to perform validations
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " + responseBody);

        Assert.assertEquals(responseBody.contains(ename), true);
        Assert.assertEquals(responseBody.contains(esal), true);
        Assert.assertEquals(responseBody.contains(eage), true);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @DataProvider (name="EmpDataProvider")
    Object[][] getEmpData() throws IOException {
        //Read data from Excel
        String path = System.getProperty("user.dir")+"/src/test/java/datadriventesting/empdata.xlsx";

        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path,"Sheet1",1);

        String empData[][] = new String[rownum][colcount];

        for (int i=1; i<=rownum; i++) {
            for (int j=0; j<colcount; j++) {
                empData[i-1][j] = XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        //String empData[][]={{"abc123","30000","40"},{"xyz123","40000","30"},{"pqr123","80000","50"}};
        return empData;
    }
}
