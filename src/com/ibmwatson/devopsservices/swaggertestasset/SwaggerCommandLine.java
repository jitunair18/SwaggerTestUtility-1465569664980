package com.ibmwatson.devopsservices.swaggertestasset;

import java.io.File;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;



public class SwaggerCommandLine {
	
	public static String testngxml = "testng.xml";

	public static void main(String[] args) {
		
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		
		List<String> suites = Lists.newArrayList();
		File filedir = new File(System.getProperty("user.dir"));
		File filetestngxml = new File(filedir, testngxml);
		suites.add(filetestngxml.getPath());//path to xml..
		testng.setTestSuites(suites);
		testng.run();
		testng.getStatus();
		System.out.println(testng.getStatus());

	}

}
