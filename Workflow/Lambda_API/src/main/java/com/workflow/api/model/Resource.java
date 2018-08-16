package com.workflow.api.model;

import java.io.IOException;

import com.workflow.api.request.*;
import com.workflow.api.response.*;
public interface Resource {
	
	
	public Response processResourceRequest(Request request) throws IOException;

}
