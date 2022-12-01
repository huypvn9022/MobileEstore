package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.DonHangDAO;
import com.mobilestore.model.ChiTietDonHang;
import com.mobilestore.model.DonHang;

@Service
public class DonHangService {
	@Autowired
	DonHangDAO donhangdao;

	public List<DonHang> findAll() {
		return donhangdao.findAll();
	}

	public DonHang findById(Integer id) {
		return donhangdao.findById(id).get();
	}
	
}
