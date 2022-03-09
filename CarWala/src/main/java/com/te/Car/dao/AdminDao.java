package com.te.Car.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.Car.bean.Admin;

public interface AdminDao extends CrudRepository<Admin, Integer>{

	public Admin findByAdminName(String adminName);
}
