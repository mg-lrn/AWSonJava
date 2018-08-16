package com.workflow.api.model;


import java.io.IOException;

import org.json.simple.JSONObject;

import com.core.extractcore.*;
import com.workflow.api.request.*;
import com.workflow.api.response.*;


public class XSLT implements Resource{

	
	public XSLT() {
		// TODO Auto-generated constructor stub
	
	}

	
	public Response processResourceRequest(Request request) throws IOException
	{
		
		Response response= new Response();
		String responseStr="";
		JSONObject jsonobj = (JSONObject)request.event.get("queryStringParameters");
		
		String action =request.event.get("action").toString();
		
		String DunsNumber =request.event.get("DunsNumber").toString();
		switch(action)
		{
			
			case "getXML":
				responseStr= getDunsXML(DunsNumber,request);
				break;
			case "ExtractAndPublish":
				break;
				
			case "getXSLToutput":
				break;
			
		}
	
		response.setResponseStream(responseStr);
	
		return response;
	}
	
	
	private String getDunsXML(String DunsNumber,Request request) throws IOException
	{
		ExtractCore extractCore = new ExtractCore(request.document);
	
		// get location
			
		String[] location = request.document.getDocumentS3Location(DunsNumber);
		
		String bucket = "rdm-dev-stream-s3-dad-00"+location[0];
		String key = location[1]+"/"+DunsNumber+".json.gz";
	
		
		String s = extractCore.getXMLFromDocument(bucket,key,1);
	
		String xmldata = request.document.getXMLFromDocument(s,"ROOT");
	
		return xmldata;
	}
	
}
