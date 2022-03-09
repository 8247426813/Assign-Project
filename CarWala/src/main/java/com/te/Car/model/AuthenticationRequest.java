package com.te.Car.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

	private String adminName;
	private String adminPassword;
	
}
