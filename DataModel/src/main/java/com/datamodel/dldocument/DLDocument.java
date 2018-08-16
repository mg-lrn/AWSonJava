package com.datamodel.dldocument;


public interface DLDocument {

	
	public  String getXMLFromDocument(String json,String RootElement);
	public String[] getDocumentS3Location(String DocumentKey);
}