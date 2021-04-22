
package com.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.camel.Header; 

public class UserService {

	private static Map<Integer, User> userDB;

	private static AtomicInteger idCounter;

	static {
		System.out.println("The initilaization for userDB");
		// customerDB = new ConcurrentHashMap<Customer_ID, Customer>();
		userDB = new ConcurrentHashMap<Integer, User>();
		idCounter = new AtomicInteger();
		User user1 = new User();

		user1.setIncome(7865.45);
		user1.setName("Ashok Gupta");
		int idCount = idCounter.incrementAndGet();
		System.out.println("The user1 id created is "+idCount);
		
		user1.setId(idCount);

		// add user object to map;
		userDB.put(idCount, user1);

		User user2 = new User();

		user2.setIncome(5134.90);
		user2.setName("Baba Kadam");
		idCount = idCounter.incrementAndGet();
		System.out.println("The user2 id created is "+idCount);
		user2.setId(idCount);
		// add user object to map;
		userDB.put(idCount, user2);

	}

	public List getAllUsers() {
		System.out.println("getAllUsers from user service");
		int size = userDB.size();
		System.out.println("The number of initial user entries are " + size);

		List<User> userList = new ArrayList<User>(userDB.values());
		System.out.println("The users list is  " + userList);

		return userList;
	}

	
	public User addUser(User user)
	{
		System.out.println("userService.addUser() post from json "+user);
	      
		  int idCount = idCounter.incrementAndGet();		  
		  
		  user.setId(idCount);		  
	      userDB.put(idCount, user);	      
	      User custObj = userDB.get(idCount);
	      System.out.println("The user in map is  "+custObj);
	      
	      String result = "Added customer with id  " + idCount;
	      int size = userDB.size();
		  System.out.println("The usre entries in post are "+size);		   
	       
		  return user;
	}
	
	public User findUser(@Header("id") String userid) {
		System.out.println("The findUser for userService " + userid);
        int id = Integer.parseInt(userid);
		User user = userDB.get(id);
		if (user == null) {
			// throw new WebApplicationException(Response.Status.NOT_FOUND);
			System.out.println("The User NOT found as  " + id);
			return null;
		}

		System.out.println("The User identified as  " + user.getName());

		return user;
	}

	public User updateUser(@Header("id") int id, User user) {
		System.out.println("UserService.updateUser()   " + user);

		int userid = user.getId();
		System.out.println("Updating the user with id = " + userid);
		User custObj = userDB.get(id);
		System.out.println("The User in map is  " + custObj);
		if (custObj == null)
			return null;

		userDB.put(id, user);

		System.out.println("Updated user " + id);

		return user;

	}

}
