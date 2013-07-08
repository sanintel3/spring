package com.cts.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cts.hibernate.entity.UserEntity;

@Repository("userDAO")
public class UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public UserEntity getUserByEmployeeId(String employeeId) {
		
		List<UserEntity> userEntities = findByCriteria(
				Restrictions.eq("employeeId", employeeId));
		
		if (userEntities.size() > 0)
			return userEntities.get(0);

		return new UserEntity();
		
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<UserEntity> getAllUsers() {
	
		return findByCriteria(
				Restrictions.ne("id", 0));
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUser(UserEntity userEntity){
		sessionFactory.getCurrentSession().save(userEntity);		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUser(UserEntity userEntity){
		sessionFactory.getCurrentSession().update(userEntity);		
	}
	
	protected List<UserEntity> findByCriteria(Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(UserEntity.class);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (crit.list() == null)
			return new ArrayList<UserEntity>();

		return crit.list();
	}
}
