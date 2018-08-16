package com.core.extractcore;

import java.io.IOException;
import com.awshelper.S3Helper;
import com.datamodel.document.*;

public class ExtractCore {


    private S3Helper s3ClientSource ;
    private S3Helper s3ClientSubscription ;
    private DLDocument document;
	public ExtractCore() {
		// TODO Auto-generated constructor stub
		s3ClientSource=new S3Helper();
		s3ClientSubscription = new S3Helper();
	}
	public ExtractCore(DLDocument document) {
		// TODO Auto-generated constructor stub
		this.document = document;
	}
	

	public String processExtractFromS3(String bucket,String key,Integer Compressed) throws IOException
	{
	    String S="";
	    String SubscriptionJSON="";
	    
	    // get object as string from S3
	    
		//S = s3ClientSource.GetS3ObjectAsString(bucket,key,Compressed);
		
		S = getXMLFromDocument(bucket,key,Compressed);
		
		
		// get subscription
		/*
		try {
			SubscriptionJSON = s3ClientSource.GetS3ObjectAsString(bucket,key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// apply XSLTs
		
		return S;
	}
	
	public String getXMLFromDocument(String bucket,String key,Integer Compressed) throws IOException
	{

	    // get object as string from S3
	    String S="";
		S = s3ClientSource.GetS3ObjectAsString(bucket,key,Compressed);
		return document.getXMLFromDocument(S, "ROOT");
		
	}
	
	
    
}
