package com.tomjay.SimpleRest;

import java.util.ArrayList;
import java.util.List;


// Our mini business delegate
public class BusinessDelegate {
	
	
	// Instance of ourself since we are a Singleton
	private static BusinessDelegate INSTANCE = new BusinessDelegate();
	
	
	// Our mini in-memory database
	private List<UserEntity> users = new ArrayList<UserEntity>();
	
	public static BusinessDelegate getInstance() {
		return INSTANCE;
	}
	
	
	// Private constructor since we are a singleton
	private BusinessDelegate() {
		
		// Create all our users
		UserEntity user = new UserEntity();
		user.setId("1234");
		user.setName("John Smith");
		user.setDescription("Programmer");
		
		// Save in our in-memory database - ala List
		users.add(user);
		
		user = new UserEntity();
		user.setId("4321");
		user.setName("Jane Smith");
		user.setDescription("Tester");

		users.add(user);
		
	}
	
	
	// If the userId is not null, check out mini database and return the user if found, otherwise return null
	public UserEntity findUserForId(String userId) {
		
		if (userId == null) {
			return null;
		}
		
		for (UserEntity user : users) {
			if (user.getId().equals(userId)) {
				return user;
			}
		}
		
		return null;
		
		
	}
	

}
