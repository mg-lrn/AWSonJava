package com.datamodel.document;


public interface DLDocument {

	StringBuilder outputXML= new StringBuilder();
	public  String getXMLFromDocument(String json,String RootElement);
	public String[] getDocumentS3Location(String DocumentKey);
}