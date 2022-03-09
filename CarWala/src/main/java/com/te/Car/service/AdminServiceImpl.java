package com.te.Car.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.Car.bean.Admin;
import com.te.Car.dao.AdminDao;
import com.te.Car.dao.CarDetailsDao;
import com.te.Car.util.JwtUtil;
@Service
public class AdminServiceImpl implements UserDetailsService,AdminService{
	
	@Autowired
	private AdminDao adminDao;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin=adminDao.findByAdminName(username);
		if(admin!=null) {
			return new User(admin.getAdminName(), admin.getAdminPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Admin Not Found");
	}


	@Override
	public Admin signUp(Admin admin) {
		return adminDao.save(admin);
	}
	
}
