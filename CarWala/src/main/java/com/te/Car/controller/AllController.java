package com.te.Car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.Car.bean.Admin;
import com.te.Car.bean.CarDetails;
import com.te.Car.model.AuthenticationRequest;
import com.te.Car.model.AuthenticationResponse;
import com.te.Car.model.Response;
import com.te.Car.model.SuperAdminResponse;
import com.te.Car.service.AdminService;
import com.te.Car.service.CarService;
import com.te.Car.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200/")
public class AllController {

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private CarService carService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AdminService adminService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getAdminName(), authenticationRequest.getAdminPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("INVALID ID & PASSWORD", e);
		}
		UserDetails userDetails = detailsService.loadUserByUsername(authenticationRequest.getAdminName());
		String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> signUpAuthentication(@RequestBody Admin admin) {
		Admin signUpData=adminService.signUp(admin);
		if (signUpData != null) {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signUpData.getAdminName(), signUpData.getAdminPassword()));
			UserDetails details1 = detailsService.loadUserByUsername(signUpData.getAdminName());
			String jwtToken = jwtUtil.generateToken(details1);
			
			return ResponseEntity.ok(new SuperAdminResponse("signUp success", false, null));

		} else {
			return ResponseEntity.ok(new SuperAdminResponse("username already exists", true, null));
		}

	}

	@GetMapping("/getAllCars")
	public Response getAllCars() {
		Response response = new Response();
		List<CarDetails> details = carService.getAllCar();
		if (details != null) {
			response.setStatusCode(300);
			response.setMsg("Success");
			response.setDiscription("Here all the details");
			response.setAllCars(details);
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDiscription("No car found");
		}
		return response;
	}

	@GetMapping("/getCarById")
	public Response getCarsBtId(int id) {
		Response response = new Response();
		CarDetails details = carService.getCarById(id);
		if (details != null) {
			response.setStatusCode(300);
			response.setMsg("Success");
			response.setDiscription("Here all the details");
			response.setCarDetails(details);
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDiscription("No car found");
		}
		return response;
	}

	@PostMapping("/addCar")
	public Response addCar(@RequestBody CarDetails carDetails) {
		Response response = new Response();
		if (carService.addCar(carDetails)) {
			response.setStatusCode(300);
			response.setMsg("Success");
			response.setDiscription("Data Added Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDiscription("Something Went wrong");
		}
		return response;
	}

	@DeleteMapping("/deleteCar/{carId}")
	public Response deleteCar(@PathVariable int carId) {
		Response response = new Response();
		if (carService.deleteCar(carId)) {
			response.setStatusCode(300);
			response.setMsg("Success");
			response.setDiscription("Data deleted Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDiscription("Something Went wrong");
		}
		return response;
	}

	@PostMapping("/updateCar/{carDetails}")
	public Response updateCar(@RequestBody CarDetails carDetails) {
		Response response = new Response();
		if (carService.updateCar(carDetails)) {
			response.setStatusCode(300);
			response.setMsg("Success");
			response.setDiscription("Data updated Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDiscription("Something Went wrong");
		}
		return response;
	}

}
