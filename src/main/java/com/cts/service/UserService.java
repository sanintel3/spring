package com.cts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hibernate.dao.UserDAO;
import com.cts.hibernate.entity.UserEntity;
import com.cts.model.User;

@Service("userService")
public class UserService {

	@Autowired
	UserDAO userDAO;
	

	public User getUserByEmployeeId(String employeeId) throws Exception {

		User user = new User();
		UserEntity userEntity = userDAO.getUserByEmployeeId(employeeId);
		BeanUtils.copyProperties(userEntity,user);
		return user;
	}

	public List<User> getAllUsers() throws Exception {

		List<UserEntity> userEntities = userDAO.getAllUsers();
		System.out.println(userEntities.size());
		List<User> users = new ArrayList<User>(userEntities.size());
		for (UserEntity userEntity : userEntities) {
			User user = new User();
			BeanUtils.copyProperties(userEntity,user);
			users.add(user);
		}
		
		return users;
	}

	public void saveUser(User user) throws Exception {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userDAO.saveUser(userEntity);
	}

	public void updateUser(User user) throws Exception {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userDAO.updateUser(userEntity);
	}
}
