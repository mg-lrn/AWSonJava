package com.workflow.api.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Response {

	
	public Response() {
		// TODO Auto-generated constructor stub
		
	}

	public String getResponseStream(String responseStr,int useAsJsonObj) throws IOException
	{
		 JSONObject responseJson= null;
		 
		 responseJson=new JSONObject(); 
        
		 JSONObject responseBody = new JSONObject();
		 if(useAsJsonObj==0)
		 {
        
	        responseBody.put("response", responseStr);
	        responseBody.put("message", "Success");
		 }
		 else
		 {
			 
			 
			 JSONParser parser = new JSONParser(); 
			 try {
				responseBody = (JSONObject) parser.parse(responseStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 
		 }

        JSONObject headerJson = new JSONObject();
        headerJson.put("x-custom-header", "my custom header value");

        responseJson.put("isBase64Encoded", false);
        responseJson.put("statusCode", "200");
        responseJson.put("headers", headerJson);
        responseJson.put("body", responseBody.toString()); 
        
        return responseJson.toJSONString();

        
	}
}
