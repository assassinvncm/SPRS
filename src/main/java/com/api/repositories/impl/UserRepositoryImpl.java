package com.api.repositories.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.model.Group;
import com.api.model.User;
import com.api.repositories.GroupRepository;
import com.api.repositories.UserRepository;

@Component
public class UserRepositoryImpl {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private GroupRepository groupRepo;
	 
//	@SuppressWarnings("unused")
//	public User saveUser(User user) {
//		User newCustomer = new Customer();
//        newCustomer.setCustomerName(user.getUsername());
//        newCustomer.getGroups_user()
//                .addAll(user
//                        .getGroups_user()
//                        .stream()
//                        .map(v -> {
//                            Optional<Group> vv = groupRepo.findById(v.getId());
//                            vv.get().getUsers().add(newCustomer);
//                            return vv;
//                        }).collect(Collectors.toList()));
//        return customerRepository.save(newCustomer);
//	};
}
