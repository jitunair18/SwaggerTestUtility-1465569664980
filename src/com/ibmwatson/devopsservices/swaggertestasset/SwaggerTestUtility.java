package com.ibmwatson.devopsservices.swaggertestasset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.models.Swagger;
import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Scheme;
import io.swagger.parser.SwaggerParser;
import io.swagger.util.Json;
import io.swagger.models.Operation;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;
import io.swagger.inflector.utils.ReflectionUtils;
import io.swagger.inflector.utils.ResolverUtil;

public class SwaggerTestUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// read a swagger description from the petstore as a sample
		// This will be some endpoint url or a path
		Swagger swagger = new SwaggerParser().read("http://petstore.swagger.io/v2/swagger.json");
		new ResolverUtil().resolveFully(swagger);
		String spath = swagger.getBasePath();
		//System.out.println(spath);
		Map<String, Path> checkmap = new HashMap<String, Path>();
		checkmap = swagger.getPaths();
		// Key for checkmap will give all the required endpoints for the service
		//System.out.println("testing");
		Map<String, Model> modelMap = new HashMap<String, Model>();
		modelMap = swagger.getDefinitions();
		final Path checkPath = swagger.getPath("/pet");
		
		
		//******************************************************************************************************
		//ResolverUtil resolve = new ResolverUtil();
		//Iterate through the definition Map first
		for (Map.Entry<String, Model> entry : modelMap.entrySet()) {
			System.out.println(entry.getKey());
			Model outputModel = entry.getValue();
			String jsonSchema = Json.pretty(outputModel);
		    System.out.println(jsonSchema);
		    System.out.println("***********************************");
			//mapProperty = outputModel.getProperties();
			//Object output = outputModel.getExample();
			//System.out.println(output.toString());
			//outputModel.
		    outputModel.getDescription();
		    //System.out.prin
		}
		System.exit(0);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//****************************************************************************************************
		
		
		System.out.println("*********** Find my status starts here **********");
	    Parameter paramstatus = swagger.getPaths().get("/pet/findByStatus").getGet().getParameters().get(0);
	    
	    BodyParameter bp = null;
	    
	    
	    
	    if(paramstatus instanceof BodyParameter){
	    	bp = (BodyParameter) paramstatus;
	    }
	    Model schema = bp.getSchema();
	    String jsonSchema = Json.pretty(schema);
	    System.out.println(jsonSchema);
	    System.out.println("*********** end find my status here **********");
		
		
		
		

		// List all operations associated with the path
		Operation operationValidator = new Operation();
		operationValidator = checkPath.getGet();
		if (operationValidator == null) {
			System.out.println("get was null");
		} else {
			System.out.println("include a .get operation");
		}
		operationValidator = checkPath.getDelete();
		if (operationValidator == null) {
			System.out.println("delete was null");
		} else {
			System.out.println("include a .delete operation");
		}
		operationValidator = checkPath.getPost();
		if (operationValidator == null) {
			System.out.println("post was null");
		} else {
			System.out.println("include a .post operation");
		}
		operationValidator = checkPath.getPut();
		if (operationValidator == null) {
			System.out.println("put was null");
		} else {
			System.out.println("include a .put operation");
		}
		Map<String,Model> mapDefinitions = new HashMap<String,Model>();
		Map<String,Property> mapProperty = new HashMap<String,Property>();
		mapDefinitions = swagger.getDefinitions();
		for (Map.Entry<String, Model> entry : mapDefinitions.entrySet()) {
			System.out.println(entry.getKey());
			Model outputModel = entry.getValue();
			mapProperty = outputModel.getProperties();
			//Object output = outputModel.getExample();
			//System.out.println(output.toString());
			//outputModel.
		}
		
		
		for (Map.Entry<String, Property> entry : mapProperty.entrySet()) {
			System.out.println(entry.getKey());
			Property propValue = entry.getValue();
			//propValue.get
			//propValue.getName()
			System.out.println(propValue.getName());
			
		}
		
		
		//Crap completed
		System.out.println("Crap completed");
		

		// ********** operation association ends here
		List<Operation> listOperations = new ArrayList<Operation>();
		List<Parameter> parameterList = new ArrayList<Parameter>();
		List<Scheme> schemeList = new ArrayList<Scheme>();
		List<String> consumptionList = new ArrayList<String>();
		Map<String, Response> responseMap = new HashMap<String, Response>();
		listOperations = checkPath.getOperations();
		for (Operation op : listOperations) {
			responseMap = op.getResponses();
			parameterList = op.getParameters();
			consumptionList = op.getConsumes();
			schemeList = op.getSchemes();
		}

		System.out.println("Start iterating through responses of the endpoint operation");
		// Enumerate items of a hashmap
		for (Map.Entry<String, Response> entry : responseMap.entrySet()) {
			System.out.println(entry.getKey());
			Response rep = entry.getValue();
			
			System.out.println(rep.getDescription());
		}

		// Extract all parameters for the end point
		System.out.println("********** Now lets see the parameters for the end point");
		for (Parameter param : parameterList) {
			System.out.println(param.getDescription());
			System.out.println(param.getPattern());
			System.out.println(param.getIn());
			

		}

		// print out scheme and consumption list
		System.out.println("***** Scheme list displayed below");
		//for (Scheme s : schemeList) {
			// s.toString();
			// s.toValue();
			//System.out.println(s.toString());
			//System.out.println(s.toValue());
		//}
		System.out.println("consumption list is displayed below");
		for(String consume: consumptionList){
			System.out.println(consume);
		}

	}

}
