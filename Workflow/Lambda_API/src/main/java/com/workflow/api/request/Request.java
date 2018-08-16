package com.workflow.api.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.datamodel.dldocument.*;
import com.workflow.api.model.*;

public class Request {

	public String EventResource;
	public String EventhttpMethod;
	public String EventStage;
	public JSONObject event;
	public DLDocument document ;	
   
    
	
	public Request() {
		// TODO Auto-generated constructor stub
		
	}

	
public Resource parseRequest(InputStream inputStream) throws IOException
{
	
	 Resource  resource =null;
	// read inputstream
	JSONParser parser = new JSONParser();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    
 // parse reader to json object
    
   event = null;
	try {
		event = (JSONObject) parser.parse(reader);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// fill local variables
	EventResource = event.get("resource").toString();
	EventhttpMethod = ((JSONObject) event.get("requestContext")).get("httpMethod").toString();
	EventStage = ((JSONObject) event.get("requestContext")).get("stage").toString();
	
	document= new FeedsDocument();
	// int resource
	

    switch(EventResource)
    {
    case "/javatest/XSLT":
    	resource = new XSLT();
    	
    }
    
    
    return resource;
	
}

	
}
