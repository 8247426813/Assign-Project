package com.te.Car.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class CarDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	private String carName;
	private String companyName;
	private String fuelType;
	private boolean powerSteering;
	private String breakSystem;
	private Double showroomPrice;
	private Double onroadPrice;
	private String imageUrl;
	private Double mileage;
	private int seatingCapacity;
	private int engineCapacity;
	private String gearType;
	
	@ManyToOne
	@JoinColumn(name = "admin_Id",referencedColumnName = "adminId")
	private Admin admin;


}
