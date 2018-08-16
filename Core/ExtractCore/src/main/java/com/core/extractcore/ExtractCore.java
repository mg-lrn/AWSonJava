package com.core.extractcore;

import java.io.IOException;
import com.awshelper.S3Helper;
import com.datamodel.dldocument.*;
import com.datamodel.subscription.*;


public class ExtractCore {


    private S3Helper s3ClientSource ;
    private S3Helper s3ClientSubscription;
    private SubscriptionConfig subscriptionConfig;
    private int _isSubscriptionConfig;
	public ExtractCore() {
		// TODO Auto-generated constructor stub
		s3ClientSource=new S3Helper();
		s3ClientSubscription = new S3Helper();
		subscriptionConfig = new SubscriptionConfig();
		_isSubscriptionConfig=0;
		loadSubscriptionConfig();
	}
	
	
	public void loadSubscriptionConfig()
	{
		System.out.println("_isSubscriptionConfig before:" + _isSubscriptionConfig);
		if(_isSubscriptionConfig==0)
		{
			subscriptionConfig.loaded =1;
			_isSubscriptionConfig=1;
		}
		
		
	}

	public String processExtractFromS3(String bucket,String key,Integer Compressed, DLDocument document) throws IOException
	{
	    String S="";
	    String SubscriptionJSON="";
	    
	    // get object as string from S3
	    
		//S = s3ClientSource.GetS3ObjectAsString(bucket,key,Compressed);
		
		S = getXMLFromDocument(bucket,key,Compressed,document);
		
		
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
	
	public String getXMLFromDocument(String bucket,String key,Integer Compressed,DLDocument document) throws IOException
	{

		System.out.println("_isSubscriptionConfig after: " + _isSubscriptionConfig);
		
		
	    // get object as string from S3
	    String S="";
	    
	    long startTime = System.nanoTime();	   
	    
		S = s3ClientSource.GetS3ObjectAsString(bucket,key,Compressed);		
		
		long endTime = System.nanoTime();

		long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
		    
		System.out.println("s3 get time: "+ duration);
		
		String XMLRtnStr = "";
		
		XMLRtnStr = document.getXMLFromDocument(S, "ROOT");
		
		return XMLRtnStr;
		
	}
	
	
    
}
