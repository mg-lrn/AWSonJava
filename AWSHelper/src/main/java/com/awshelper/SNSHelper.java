package com.awshelper;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SNSHelper {

	AmazonSNS snsClient;
	public SNSHelper() {
		// TODO Auto-generated constructor stub
		//create a new SNS client and set endpoint
		AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withRegion("us-west-2") // The first region to try your request against
               // .withForceGlobalBucketAccess(true) // If a bucket is in a different region, try again in the correct region
                .build();
		
	}

	public boolean publish(String arn, String msg, String subject) {
		  try {
		    snsClient.publish(arn, msg, subject);
		  } catch (RuntimeException e) {
		   // logger.error("error publishing", e);
		    return false;
		  }

		  return true;
		}
}
