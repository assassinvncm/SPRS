//package com.api.services.impl;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//
//import com.api.model.Group;
//import com.api.model.User;
//import com.api.services.GroupServices;
//import com.api.services.UserServices;
//import com.api.services.custom.UserServiceCustom;
//
//public class UserServiceCustom_Impl implements UserServiceCustom{
//	
//	@Autowired
//    @Lazy
//    UserServices userServ;
//	
//	@Autowired 
//	@Lazy
//	GroupServices groupServ;
//	
//	@Override
//	public User saveUser(User u) {
//		User newUser = new User();
//        newUser.setUsername(u.getUsername());
//        newUser.setPhone(u.getPhone());
//        newUser.setPassword(u.getPassword());
//        newUser.setAddress(u.getAddress());
//        newUser.setDob(u.getDob());
//        newUser.setCreate_time(u.getCreate_time());
//        newUser.setIsActive(u.getIsActive());
//        newUser.setFull_name(u.getFull_name());
////        newUser.getGroups_user().addAll(u
////                .getGroups_user()
////                .stream()
////                .map(v -> {
////                    Optional<Group> vv = groupServ.findById(v.getId());
////                    vv.get().getUsers().add(newUser);
////                    return vv;
////                }).collect(Collectors.toList()))
////                .addAll();
////        return userServ.save(newUser);
//        return newUser;
//	}
//
//}
