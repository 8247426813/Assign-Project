package com.te.Car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.Car.bean.Admin;
import com.te.Car.bean.CarDetails;
import com.te.Car.dao.CarDetailsDao;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDetailsDao detailsDao;

	@Override
	public List<CarDetails> getAllCar() {
		List<CarDetails> details = (List<CarDetails>) detailsDao.findAll();
		if (details.size() <= 0) {
			return null;
		} else {
			return details;
		}
	}

	@Override
	public boolean addCar(CarDetails carDetails) {
		boolean isAdded=false;
		CarDetails carDetails2=detailsDao.save(carDetails);
		if(carDetails2!=null) {
			isAdded=true;
		}
		return isAdded;
	}

	@Override
	public boolean deleteCar(int id) {
		boolean isDeleted=false;
		CarDetails details=detailsDao.findBycarId(id);
		detailsDao.delete(details);
		if(details!=null) {
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public boolean updateCar(CarDetails carDetails) {
		boolean isUpdated=false;
		CarDetails details = detailsDao.findBycarId(carDetails.getCarId());
		if(details!=null) {
			details.setCarId(carDetails.getCarId());
			details.setCarName(carDetails.getCarName());
			details.setCompanyName(carDetails.getCompanyName());
			details.setFuelType(carDetails.getFuelType());
			details.setPowerSteering(carDetails.isPowerSteering());
			details.setBreakSystem(carDetails.getBreakSystem());
			details.setShowroomPrice(carDetails.getShowroomPrice());
			details.setOnroadPrice(carDetails.getOnroadPrice());
			details.setImageUrl(carDetails.getImageUrl());
			details.setMileage(carDetails.getMileage());
			details.setSeatingCapacity(carDetails.getSeatingCapacity());
			details.setEngineCapacity(carDetails.getEngineCapacity());
			details.setGearType(carDetails.getGearType());
			
			isUpdated=true;
		}
		return isUpdated;
	}

	@Override
	public CarDetails getCarById(int id) {
		boolean isFind=false;
		CarDetails carDetails=detailsDao.findBycarId(id);
		if(carDetails!=null) {
			isFind=true;
		}
		return carDetails;
	}

	

	

}
