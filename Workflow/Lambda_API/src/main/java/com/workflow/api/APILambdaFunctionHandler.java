package com.workflow.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.workflow.api.model.*;
import com.workflow.api.request.*;


public class APILambdaFunctionHandler implements RequestStreamHandler {
	

    public APILambdaFunctionHandler() {}

    // Test purpose only.
   

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
    	
    	Request requestObj = new Request();
    	String response = "";
    	Resource resource= requestObj.parseRequest(inputStream);
    	
    	
    	response=resource.processResourceRequest(requestObj);
    	
    	context.getLogger().log("output length: " + response.length());
    	
    	OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(response);  
        writer.close();

    }
}