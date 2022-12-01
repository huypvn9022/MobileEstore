package com.mobilestore.service;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.DonHangDAO;
import com.mobilestore.model.ChiTietDonHang;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobilestore.dao.DonHangDAO;
>>>>>>> e601094b900b44858e2945c75c292780735634a4
import com.mobilestore.model.DonHang;

@Service
public class DonHangService {
<<<<<<< HEAD
	@Autowired
	DonHangDAO donhangdao;

	public List<DonHang> findAll() {
		return donhangdao.findAll();
	}

	public DonHang findById(Integer id) {
		return donhangdao.findById(id).get();
	}
	
=======
	
	@Autowired
	DonHangDAO dhdao;

	public DonHang create(JsonNode order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
>>>>>>> e601094b900b44858e2945c75c292780735634a4
}
