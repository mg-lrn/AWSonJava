package com.workflow.api.model;

import java.io.IOException;

import com.workflow.api.request.*;
public interface Resource {
	
	
	public String processResourceRequest(Request request) throws IOException;

}
