package com.compute.controller;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.compute.CryptoServiceApplication;
import com.compute.service.ComputeService;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;

 
@Path("/crypto")
@Produces(MediaType.APPLICATION_JSON)
public class ComputeServiceController {
	
	@Inject
	private ComputeService computeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoServiceApplication.class);
 
    @GET
    @Path("/pushAndRecalculate/{id}")
    public Response getAverageAndStandardDeviation(@PathParam("id") Integer id) {
    	LOGGER.info("Requested getAverageAndStandardDeviation with id={}",id);
    	if(id == null){
    		return Response.noContent().build();
    	}
    	
    	LOGGER.info("Hash pf the object = {}",computeService);
    	double []response = computeService.getAverageAndStandardDeviation(id);
        return Response.ok(response).build();
    }
    
    @GET
    @Path("/pushRecalculateAndEncrypt/{id}")
    public Response getAverageAndStandardDeviationEncrypted(@PathParam("id") Integer id) {
    	if(id == null){
    		return Response.noContent().build();
    	}
    	String []response = computeService.getEncryptedAverageAndStandardDeviation(id);
        return Response.ok(response).build();
    }
    
    @GET
    @Path("/decrypt/{encryptedText}")
    public Response decrypt(@PathParam("encryptedText") String encryptedText) {
    	Preconditions.checkNotNull(encryptedText);
    	double plainText = computeService.decrypt(encryptedText);
        return Response.ok(plainText).build();
    }
 
   
}
