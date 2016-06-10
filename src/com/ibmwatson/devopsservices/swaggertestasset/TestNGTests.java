package com.ibmwatson.devopsservices.swaggertestasset;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ResponseSpecification;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class TestNGTests {

	public static String dataFile = "Data.properties";

	@DataProvider(name = "dbconfig")
	public Object[][] provideDbConfig() throws IOException {
		// Resolve data parameter for get operation
		Properties prop = new Properties();
		String filePath = System.getProperty("user.dir") + dataFile;
		File file1 = new File(System.getProperty("user.dir"));
		File file2 = new File(file1, dataFile);
		InputStream input;

		input = new FileInputStream(new File(file2.getPath()));

		System.out.println(new File(".").getAbsolutePath());
		prop.load(input);
		String swaggerurl = prop.getProperty("swaggerurl");
		Map<Object, Object> map = SwaggerUtility.getSwaggerData(swaggerurl);
		Object[][] arr = new Object[map.size()][2];
		Set entries = map.entrySet();
		Iterator entriesIterator = entries.iterator();

		int i = 0;
		while (entriesIterator.hasNext()) {

			Map.Entry mapping = (Map.Entry) entriesIterator.next();

			EndpointOperationType endpointtype = (EndpointOperationType) mapping.getKey();
			arr[i][0] = endpointtype.getEndpoint() + "," + endpointtype.getOperation();
			arr[i][1] = mapping.getValue();
			i++;
		}
		return arr;
	}

	// Run tests once for every endpoint and operation combination
	@Test(dataProvider = "dbconfig")
	public void executeRestfulTest(String endpoint, Object obj) throws IOException {

		String endpointOutput = endpoint.split(",")[0];
		String endpointOperationOutput = endpoint.split(",")[1];
		Map<Object, Object> dataMap = (Map) obj;
		dataMap.get("response");

		switch (endpointOperationOutput.toLowerCase()) {

		case "get":

			// Resolve data parameter for get operation
			Properties prop = new Properties();
			String filePath = System.getProperty("user.dir") + dataFile;
			File file1 = new File(System.getProperty("user.dir"));
			File file2 = new File(file1, dataFile);
			InputStream input = new FileInputStream(new File(file2.getPath()));
			System.out.println(new File(".").getAbsolutePath());
			prop.load(input);
			String output = prop.getProperty(endpointOutput);
			String data = null;
			if (output == null) {
				data = endpointOutput;
			} else {
				if (endpointOutput.contains("{")) {
					String answer = endpointOutput.substring(endpointOutput.indexOf("{") + 1,
							endpointOutput.indexOf("}"));
					String replace = "{" + answer + "}";
					data = endpointOutput.replace(replace, output);
				} else {
					data = endpointOutput + "?" + output;
				}

			}
			// Rest assured Given When Then BDD assertion syntax
			String geturl = dataMap.get("baseURI") + data.trim();
			RestAssured.given().contentType("application/json").when().get(geturl).then().statusCode(200);
			//response,dataMap.get(endpoint).get("responsebody)
			break;

		// Performance testing for the same endpoint and operation
		case "post":
			break;
		case "put":
			break;
		case "delete":
			break;

		default:
			System.out.println(endpointOutput + "+" + endpointOperationOutput);

		}

	}

}
