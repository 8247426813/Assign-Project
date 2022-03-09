package com.te.Car.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.Car.service.AdminService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AllControllerTest {
	
	@MockBean
	private AuthenticationManager authenticationManager;
	
	@MockBean
	private AdminService adminService;
	
	@MockBean
	private UserDetailsService detailsService;
	
	private  MockMvc mockmvc;
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@BeforeEach
	public void setUp() throws Exception {
		mockmvc=MockMvcBuilders.webAppContextSetup(applicationContext).build();
				
	}

	@Test
	void testSignUpAuthentication() {
//		fail("Not yet implemented");
	}

	@Test
	void testGetAllCars () {
//		fail("Not yet implemented");
		
	}

//	@Test
//	void testAddCar() {
////		fail("Not yet implemented");
//		
//
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		request.getHeader("Authorization");
//		CarDetails carDetails = new CarDetails();
//		carDetails.setCarId(102);
//		carDetails.setCompanyName("hundai");
//		carDetails.setCarName("elitei20");
//		carDetails.setEngineCapacity(220);
//		carDetails.setBreakSystem("ABS");
//
//		when(adminService.addCar(carDetails, request)).thenReturn(carDetails);
//
//		String contentAsString = mockMvc
//				.perform(post("/admin/addCar").contentType(MediaType.APPLICATION_JSON_VALUE)
//						.content(objectMapper.writeValueAsString(carDetails)).accept(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//		AuthenticationResponse readValue = objectMapper.readValue(contentAsString, CarResponse.class);
//
//		assertEquals("car added successfully", readValue.getMsg());
//	}

	@Test
	void testDeleteCar() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdateCar() {
//		fail("Not yet implemented");
		
	}

}
