package com.workflow.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.workflow.api.model.*;
import com.workflow.api.request.*;
import com.workflow.api.response.*;


public class APILambdaFunctionHandler implements RequestStreamHandler {
	

    public APILambdaFunctionHandler() {}

    // Test purpose only.
   

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
    	
    	Request requestObj = new Request();
    	Response response = new Response();
    	Resource resource= requestObj.parseRequest(inputStream);
    	response=resource.processResourceRequest(requestObj);
    	outputStream = response.outputStream;

    }
}