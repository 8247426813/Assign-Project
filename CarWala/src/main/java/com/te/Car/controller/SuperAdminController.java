package com.te.Car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.Car.bean.CarDetails;
import com.te.Car.model.SuperAdminResponse;
import com.te.Car.service.SuperAdminService;

@RestController
public class SuperAdminController {
	@Autowired
	private SuperAdminService service;
	
	
	@GetMapping("super/getall")
	public ResponseEntity<?> getAll() {
		try {
			List<CarDetails> list=service.getAll();
			return ResponseEntity.ok(new SuperAdminResponse("got all car list", false, list));
		} catch (Exception e) {
			return ResponseEntity.ok(new SuperAdminResponse("something went wrong!!!", true, null));
		}
		
		
	}

}
