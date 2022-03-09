package com.te.Car.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.Car.bean.CarDetails;

public interface CarDetailsDao  extends CrudRepository<CarDetails, Integer>{
	
	public CarDetails findBycarId(int id);


}
