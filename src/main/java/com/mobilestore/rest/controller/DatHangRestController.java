package com.mobilestore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobilestore.model.DonHang;
import com.mobilestore.service.DonHangService;

@RestController
@CrossOrigin("*")
public class DatHangRestController {
	
	@Autowired
	DonHangService dhservice;
	
	@PostMapping("/rest/orders")
	public DonHang create(@RequestBody JsonNode order) {
		return dhservice.create(order);
	} 
	
}
