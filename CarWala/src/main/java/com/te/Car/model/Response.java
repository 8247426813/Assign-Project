package com.te.Car.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.te.Car.bean.CarDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

	public Response() {

	}

	@JsonProperty
	private String msg;
	@JsonProperty
	private String discription;
	@JsonProperty
	private int statusCode;

	private CarDetails carDetails;

	private List<CarDetails> allCars;
}
