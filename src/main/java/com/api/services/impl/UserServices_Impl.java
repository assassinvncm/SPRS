package com.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.api.model.User;
import com.api.services.UserServices;
import com.api.services.custom.UserServicesCustom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserServices_Impl implements UserServicesCustom{
	@Autowired
    @Lazy
    UserServices usersrv;

	@Autowired
    EntityManager entityManager;
	
//	@Override
//	public User getByUsername(String username) {
//		 try {
//	            String sql = "SELECT * from [user] where username = ''";
//	            Query query = entityManager.createQuery(sql);
//	            return (User) query.getSingleResult();
//	        } catch (NoResultException e) {
//	            return null;
//	        }
//	}
}
