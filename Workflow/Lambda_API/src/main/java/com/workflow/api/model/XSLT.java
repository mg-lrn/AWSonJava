package com.workflow.api.model;


import java.io.IOException;

import org.json.XML;
import org.json.simple.JSONObject;

import com.core.extractcore.*;
import com.workflow.api.request.*;
import com.workflow.api.response.*;


public class XSLT implements Resource{

	
	public XSLT() {
		// TODO Auto-generated constructor stub
	
	}

	
	public String processResourceRequest(Request request) throws IOException
	{
		
		Response response= new Response();
		String responseStr="";
		String responseReturnStr="";
		JSONObject jsonobj = (JSONObject)request.event.get("queryStringParameters");
		
		String action =jsonobj.get("action").toString();
		
		String DunsNumber =jsonobj.get("DunsNumber").toString();
		switch(action)
		{
			
			case "getXML":
				responseStr= getDunsXML(DunsNumber,request);
				responseReturnStr = response.getResponseStream(responseStr,0);
				break;
			case "ExtractAndPublish":
				break;
				
			case "getXSLToutput":
				responseStr= getXSLTOutput(DunsNumber,request);
				responseReturnStr = response.getResponseStream(responseStr,1);
				break;
			
		}
	
		
	
		return responseReturnStr;
	}
	
	
	private String getDunsXML(String DunsNumber,Request request) throws IOException
	{
		ExtractCore extractCore = new ExtractCore();
	
		// get location
			
		String[] location = request.document.getDocumentS3Location(DunsNumber);
		
		String bucket = "rdm-dev-stream-s3-dad-00"+location[0];
		String key = location[1]+"/"+DunsNumber+".json.gz";
	
		
		String xmldata = extractCore.getXMLFromDocument(bucket,key,1,request.document);
		
		return xmldata;
	}
	

	private String getXSLTOutput(String DunsNumber,Request request) throws IOException
	{
		ExtractCore extractCore = new ExtractCore();
		
		String xmldata = getDunsXML(DunsNumber,request);
		
		//extractCore.ProcessSubscription(xmldata);
		
		int PRETTY_PRINT_INDENT_FACTOR = 4;
		org.json.JSONObject xmlJSONObj = XML.toJSONObject(xmldata);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
		
		return jsonPrettyPrintString;
		
		
		
		
	}
	
}
