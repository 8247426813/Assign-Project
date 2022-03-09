package com.te.Car.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.te.Car.bean.CarDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
//@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperAdminResponse {
	public SuperAdminResponse(String message2, boolean b, List<CarDetails> list) {
	}
	
	private String message;
	private String error;
	private List<CarDetails> carDetails;
//	private String role;

	

}
