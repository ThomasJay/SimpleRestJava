package com.tomjay.simplerestservice.services.v1;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.tomjay.SimpleRest.BusinessDelegate;
import com.tomjay.SimpleRest.UserEntity;


@Path("v1/users")

public class UsersResource {

	private static final Logger log = Logger.getLogger(UsersResource.class.getName());

	@javax.ws.rs.core.Context
	ServletContext context;

	
	@GET
	@Path("/{userId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findUserById(@Context HttpServletRequest requestContext,
			@PathParam("userId") String userId,
			   @HeaderParam("X-AUTH-TOKEN") String authToken) {

		
		log.debug("userId=" + userId);
		if (!"SECRET".equals(authToken)) {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"-1:Not Authorized\", \"status\":\"FAIL\"}")
					.build();
			
		}


		UserEntity user = BusinessDelegate.getInstance().findUserForId(userId);
		
		if (user != null) {
			return Response.status(Response.Status.OK).entity(user).build();			
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"100:User Not Found\", \"status\":\"user not found\"}").build();			
		}
		

	}

}
