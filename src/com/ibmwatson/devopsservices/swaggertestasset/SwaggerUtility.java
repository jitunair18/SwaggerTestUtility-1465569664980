package com.ibmwatson.devopsservices.swaggertestasset;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.inflector.utils.ResolverUtil;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;
import io.swagger.models.RefModel;
import io.swagger.parser.SwaggerParser;
import io.swagger.util.Json;
import io.swagger.models.parameters.BodyParameter;

public class SwaggerUtility {

	// get Map with swagger specification parsed and return in memory data
	// structure with all required information
	public static Map<Object, Object> getSwaggerData(String url) {

		// Variable declaration section
		Map<Object, Object> swaggerMap = new HashMap<Object,Object>();
		Map<Object, Object> dataMap = null;
		Map<String, Model> definitionMap;
		List<EndpointOperationType> endpointOperationList = new ArrayList<EndpointOperationType>();
		EndpointOperationType endpointoperationInstance;
		Map<String, Response> responseMap = null;
		List<Parameter> parameterList = null;
		String baseURI = null;
		String basePath;

		// Extract path of swagger specification
		Swagger swagger = new SwaggerParser().read(url);
		new ResolverUtil().resolveFully(swagger);
		System.out.println("THE HOST AND SWAGGER ARE GIVEN BY .......");
		
	
		
		//Swagger specification
		System.out.println(swagger.getHost());
		System.out.println(swagger.getSwagger());
		//swagger.get
		
		//Extract basepath url for the endpoint
		File file1 = new File(url);
		//file1.getPath();
		//baseURI = file1.getParent();
	    File file2 = new File(swagger.getBasePath());
	    basePath = file2.getPath();
	    System.out.println(file2.getPath());
	    
	    try {
			URL urlobject = new URL(url);
			File file = new File( urlobject.getPath() );
			String parentPath = file.getParent( );
			System.out.println(parentPath);
			URL parentUrl = new URL( urlobject.getProtocol( ), urlobject.getHost( ), urlobject.getPort( ), parentPath );
			baseURI = parentUrl.toString();
			System.out.println( "Parent: " + baseURI );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get all definitions from the Swagger specification
		definitionMap = new HashMap<String, Model>();
		definitionMap = swagger.getDefinitions();

		definitionMap = swagger.getDefinitions();

		// Print out all the definition Map entries
		for (Map.Entry<String, Model> defentry : definitionMap.entrySet()) {
			System.out.println(defentry.getKey());

			Model modeloutput = defentry.getValue();

			modeloutput.getReference();
			modeloutput.getTitle();
			modeloutput.getProperties();
			System.out.println(modeloutput.getDescription());
			System.out.println(modeloutput.getTitle());
			System.out.println(modeloutput.getReference());
			System.out.println(modeloutput.getProperties());
			Map<String, Property> mapprop = modeloutput.getProperties();
			for (Map.Entry<String, Property> entryProp : mapprop.entrySet()) {
				System.out.println(entryProp.getKey());
			}
			System.out.println("***************************************");
		}
		// Check if this definition map is going to work or not
		// System.exit(0);

		// Path map of all end points and all operations
		Map<String, Path> pathMap = new HashMap<String, Path>();
		pathMap = swagger.getPaths();
		for (Map.Entry<String, Path> entry : pathMap.entrySet()) {

			String someString = entry.getKey();
			System.out.println(someString);

			Path checkPath = entry.getValue();

			// List all operations associated with the path
			Operation operationValidator = new Operation();
			operationValidator = checkPath.getGet();
			if (operationValidator == null) {
				// System.out.println("get was null");
			} else {

			
				

				// Get all responses associated with the operation
				responseMap = new HashMap<String, Response>();
				responseMap = operationValidator.getResponses();

				// Iterate through all the responses in the map
				for (Map.Entry<String, Response> entryResponse : responseMap.entrySet()) {
					System.out.println(entryResponse.getKey());
					Response rep = entryResponse.getValue();
					System.out.println(rep.getDescription());
				}

				// Get all Parameters associated with the operation
				parameterList = new ArrayList<Parameter>();
				parameterList = operationValidator.getParameters();

				for (Parameter param : parameterList) {
					System.out.println("FIND ALL FOR A GET OPERATION");
					System.out.println("What the class is this parameter");
					System.out.println(param.getClass());
					System.out.println(param.getDescription());
					System.out.println(param.getIn());
					System.out.println(param.getName());
					System.out.println(param.getPattern());
					param.getIn();
					param.getName();
					param.getPattern();
					if (param instanceof BodyParameter) {
						BodyParameter bp = (BodyParameter) param;
						// bp.getSchema().get$ref();

						System.out.println("&&&&&&&&&&&&&&");
						// System.out.println(bp.get$ref());
					}
				}

				
				
				
				//Feed values into swagger map
				endpointoperationInstance = new EndpointOperationType(entry.getKey(), "get");
				dataMap = new HashMap<Object,Object>();
				dataMap.put("definition", definitionMap);
				dataMap.put("response", responseMap);
				dataMap.put("baseURI", baseURI);
				swaggerMap.put(endpointoperationInstance, dataMap);
			}
			
			
			
			
			
			
			operationValidator = checkPath.getDelete();
			if (operationValidator == null) {
				// System.out.println("delete was null");
			} else {
				

				// Get all responses associated with the operation
				responseMap = new HashMap<String, Response>();
				responseMap = operationValidator.getResponses();

				// Iterate through all the responses in the map
				for (Map.Entry<String, Response> entryResponse : responseMap.entrySet()) {
					System.out.println(entryResponse.getKey());
					Response rep = entryResponse.getValue();
					System.out.println(rep.getDescription());
				}

				// Get all Parameters associated with the operation
				parameterList = new ArrayList<Parameter>();
				parameterList = operationValidator.getParameters();

				for (Parameter param : parameterList) {
					System.out.println(param.getClass());
					if (param instanceof BodyParameter) {
						BodyParameter bp = (BodyParameter) param;
						// bp.getSchema().get$ref();

						System.out.println("&&&&&&&&&&&&&&");
						// System.out.println(bp.get$ref());
					}
				}
				
				
				//Feed values into swagger map
				endpointoperationInstance = new EndpointOperationType(entry.getKey(), "delete");
				dataMap = new HashMap<Object,Object>();
				dataMap.put("definition", definitionMap);
				dataMap.put("response", responseMap);
				dataMap.put("baseURI", baseURI);
				swaggerMap.put(endpointoperationInstance, dataMap);
				
				

			}
			
			
			
			
			
			operationValidator = checkPath.getPost();
			if (operationValidator == null) {
				// System.out.println("post was null");
			} else {
				

				// Get all responses associated with the operation
				responseMap = new HashMap<String, Response>();
				responseMap = operationValidator.getResponses();

				// Iterate through all the responses in the map
				for (Map.Entry<String, Response> entryResponse : responseMap.entrySet()) {
					System.out.println(entryResponse.getKey());
					Response rep = entryResponse.getValue();
					System.out.println(rep.getDescription());
				}

				// Get all Parameters associated with the operation
				parameterList = new ArrayList<Parameter>();
				parameterList = operationValidator.getParameters();

				for (Parameter param : parameterList) {
					System.out.println(param.getClass());
					System.out.println("Is code even going here");
					if (param instanceof BodyParameter) {
						BodyParameter bp = (BodyParameter) param;
						// bp.getSchema().get$ref();

						System.out.println("&&&&&&&&&&&&&&");
						// System.out.println(bp.get$ref());
					} else {
						System.out.println("This was not found to be a Ref parameter");
					}
				}
				
				//Feed values into swagger map
				endpointoperationInstance = new EndpointOperationType(entry.getKey(), "post");
				dataMap = new HashMap<Object,Object>();
				dataMap.put("definition", definitionMap);
				dataMap.put("response", responseMap);
				dataMap.put("baseURI", baseURI);
				swaggerMap.put(endpointoperationInstance, dataMap);
				
				
			}
			
			
			
			
			
			operationValidator = checkPath.getPut();
			if (operationValidator == null) {
				// System.out.println("put was null");
			} else {
				

				// Get all responses associated with the operation
				responseMap = new HashMap<String, Response>();
				responseMap = operationValidator.getResponses();

				// Iterate through all the responses in the map
				for (Map.Entry<String, Response> entryResponse : responseMap.entrySet()) {
					System.out.println(entryResponse.getKey());
					Response rep = entryResponse.getValue();
					System.out.println(rep.getDescription());
				}

				// Get all Parameters associated with the operation
				parameterList = new ArrayList<Parameter>();
				parameterList = operationValidator.getParameters();

				for (Parameter param : parameterList) {
					System.out.println(param.getClass());
					if (param instanceof BodyParameter) {
						System.out.println("BODY PARAMETER WAS FOUND for a put operation");
						BodyParameter bp = (BodyParameter) param;

						// bp.getSchema().get$ref();

						System.out.println("&&&&&&&&&&&&&&");
						System.out.println(bp.getSchema().getClass());
						// bp.getSchema(
						// try{
						//System.out.println(((RefModel) bp.getSchema()).get$ref());
						// }catch(Exception e){
						// System.out.println("Got stuck");
						// }
					}
				}
				
				
				
				//Feed values into swagger map
				endpointoperationInstance = new EndpointOperationType(entry.getKey(), "put");
				dataMap = new HashMap<Object,Object>();
				dataMap.put("definition", definitionMap);
				dataMap.put("response", responseMap);
				dataMap.put("baseURI", baseURI);
				swaggerMap.put(endpointoperationInstance, dataMap);
				
			}

		}

		return swaggerMap;
	}

	public static void main(String[] args) {

		Map<Object,Object> finalMap = getSwaggerData("http://petstore.swagger.io/v2/swagger.json");
		System.out.println("final map is generated debug and check once");
		
		//Iterate through Final map and see output
		for(Map.Entry<Object, Object> output: finalMap.entrySet()){
			
			EndpointOperationType endpointtype	= (EndpointOperationType)output.getKey();
			//endpointtype.getEndpoint() + endpointtype.getOperation();
			System.out.println(endpointtype.getEndpoint() + "+" + endpointtype.getOperation());
		}

	}

}
