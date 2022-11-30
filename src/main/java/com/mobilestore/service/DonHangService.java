package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mobilestore.dao.DonHangDAO;
import com.mobilestore.model.DonHang;



@Service
public class DonHangService {
	@Autowired
	DonHangDAO dao;
	
	public List<DonHang> findAll() {
		return dao.findAll();
	}
	
	public DonHang findById(Integer maDon) {
		return dao.findById(maDon).get();
	}
	
	public DonHang save(DonHang maDon) {
		return dao.save(maDon);
	}
	
	public DonHang update(DonHang maDon) {
		return dao.save(maDon);
	}
	
	public void delete(Integer maDon) {
		 dao.deleteById(maDon);
	}
	
	public Page<DonHang> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Boolean existsById(Integer maDon) {
		return dao.existsById(maDon);
	}

	public DonHang create(JsonNode order) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public List<DonHang> findAllByKeyword(String keyword) {
//		return dao.findAllByKeyword(keyword);
//	}

}
