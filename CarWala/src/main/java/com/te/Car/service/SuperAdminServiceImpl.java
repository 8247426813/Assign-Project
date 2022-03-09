package com.te.Car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.Car.bean.CarDetails;
import com.te.Car.dao.CarDetailsDao;
@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	
	@Autowired
	private CarDetailsDao dao;


//	@Override
	public List<CarDetails> getAll() {
		return (List<CarDetails>) dao.findAll();
	
	}
}
