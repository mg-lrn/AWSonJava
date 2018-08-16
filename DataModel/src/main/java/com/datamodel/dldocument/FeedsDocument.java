package com.datamodel.dldocument;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class FeedsDocument implements DLDocument{

	public FeedsDocument() {
		// TODO Auto-generated constructor stub
	}
	
	
	public  String getXMLFromDocument(String json,String RootElement)
	{
		StringBuilder outputXML= new StringBuilder();
		outputXML.append("<"+RootElement+">");		
		org.json.JSONObject jsonFileObject = new org.json.JSONObject(json);
		setXMLForElement("feedUpdatesSnapshot",jsonFileObject,"FEEDUPDATESSNAPSHOT",outputXML);	
		setXMLforFeeds(jsonFileObject.getJSONObject("feedsData"),outputXML);		
		outputXML.append("</"+RootElement+">");	
	
		return outputXML.toString();
		
	}
	
	
	public String[] getDocumentS3Location(String DocumentKey)
	{
		
		String [] Location = new String[2];
		
		/*
		 
		0,1 =1
		2,3=2
		4,5=3
		6,7=4
		8,9=5
		
		*/
		
		/*
		 
		1,2 =1
		3,4=2
		5,6=3
		7,8=4
		9,0=5
		
		*/
		
		
		Location[0] = DocumentKey.substring(4, 5);
		Location[1] = DocumentKey.substring(5, 9);
		
		Integer temp = Integer.parseInt(Location[0])/2 + 1;
		
		Location[0] =  temp.toString();
		return Location;
		
		
	}
	
	
	
	
	
	private  void setXMLforFeeds(JSONObject jsonObject,StringBuilder outputXML)
	{
		
		 Iterator<?> keys = jsonObject.keys();
		 while(keys.hasNext() ) {
		     String key = (String)keys.next();
		     if ( jsonObject.get(key) instanceof JSONObject ) {		         
		    	 
		    	 JSONObject feed= (JSONObject) jsonObject.get(key);
		    	 
		    	 JSONArray feedDataContent= new JSONArray(feed.get("feedDataContent").toString().trim());
				 
		    	 outputXML.append("<"+key+">");
						for(int i = 0; i < feedDataContent.length(); i++)
						{
						      setXMLForElement("content",(JSONObject) feedDataContent.get(i),"ENTRY",outputXML);	
						      
						}
				outputXML.append("</"+key+">");
		        
		     }
		 }
		
		
	}
	
	private  void setXMLForElement(String JSONElement,JSONObject jsonObject,String XMLoutputNode,StringBuilder outputXML)
	{
		
		outputXML.append("<"+XMLoutputNode+">");
		 JSONObject elementJSON = new JSONObject( jsonObject.get(JSONElement).toString().trim());
		outputXML.append(JSONToXML(elementJSON));
		outputXML.append("</"+XMLoutputNode+">");		
		
	}
	
	private  String JSONToXML(JSONObject jsonObj)
	{
		String X = org.json.XML.toString(jsonObj) ;	
		return X;
	}

}
