package com.ibmwatson.devopsservices.swaggertestasset;

import java.util.List;

import org.testng.collections.Lists;

import org.testng.*;

public class TestExecutorService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add(".//TestNG.xml");
	    testng.setTestSuites(suites);
	    testng.run();

	}
	
	//Update a sample testng.xml based on input parameter
	

}
