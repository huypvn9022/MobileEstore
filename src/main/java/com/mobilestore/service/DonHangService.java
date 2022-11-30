package com.mobilestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobilestore.dao.DonHangDAO;
import com.mobilestore.model.DonHang;

@Service
public class DonHangService {
	
	@Autowired
	DonHangDAO dhdao;

	public DonHang create(JsonNode order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
