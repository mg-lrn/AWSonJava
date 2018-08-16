package com.workflow.api.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;

public class Response {

	public OutputStream outputStream;
	public Response() {
		// TODO Auto-generated constructor stub
		
	}

	public void setResponseStream(String responseStr) throws IOException
	{
		
        JSONObject responseJson = new JSONObject();
        
        JSONObject responseBody = new JSONObject();
        responseBody.put("response", responseStr);
        responseBody.put("message", "Success");

        JSONObject headerJson = new JSONObject();
        headerJson.put("x-custom-header", "my custom header value");

        responseJson.put("isBase64Encoded", false);
        responseJson.put("statusCode", "200");
        responseJson.put("headers", headerJson);
        responseJson.put("body", responseBody.toString());  

        
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toJSONString());  
        writer.close();
        
	}
}
