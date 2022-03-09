package com.te.Car.service;

import java.util.List;

import com.te.Car.bean.Admin;
import com.te.Car.bean.CarDetails;

public interface CarService {
	
	public List<CarDetails> getAllCar();
	public boolean addCar(CarDetails carDetails);
	public boolean deleteCar(int carId);
	public boolean updateCar(CarDetails carDetails);
	public CarDetails getCarById(int id);

}
