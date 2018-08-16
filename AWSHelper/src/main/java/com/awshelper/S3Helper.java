package com.awshelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import java.io.File;



public class S3Helper {

	
	 AmazonS3 s3;
	public S3Helper() {
		// TODO Auto-generated constructor stub
		s3 = 
		        AmazonS3ClientBuilder.standard()
                .withRegion("us-west-2") // The first region to try your request against
               // .withForceGlobalBucketAccess(true) // If a bucket is in a different region, try again in the correct region
                .build();
	}

	
	
	
	public String GetS3ObjectAsString(String bucket,String key,int compressed) throws IOException
	{
		
		
		return readFromS3(bucket,key,compressed);
	}
	
	
	public String readFromS3(String bucketName, String key,int compressed) throws IOException {
		
		
		    S3Object o = s3.getObject(bucketName, key);
		    S3ObjectInputStream s3is = o.getObjectContent();
		 
		    InputStream inputStream;
		    switch(compressed)
		    {
		    case 1:
		    	inputStream = new GZIPInputStream(s3is);
		    	break;
		    	default:
		    		inputStream = s3is;
		    
		    }
		    
		    StringWriter writer = new StringWriter();
		    IOUtils.copy(inputStream, writer, "UTF-8");
		    String documentJSON = writer.toString();
		    
		    s3is.close();
		    
		    
		    return documentJSON;
		   
	}
	
	
	
	
	
	
}
