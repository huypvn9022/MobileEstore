package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.KhachHangDAO;
import com.mobilestore.model.KhachHang;

@Service
public class KhachHangService {
	
	@Autowired
	KhachHangDAO khdao;
	
	public List<KhachHang> findAll(){
		return khdao.findAll();
	}
	
	public KhachHang findById(String username) {
		return khdao.findById(username).get();
	}
	
	public KhachHang findAccountOrEmail(String username) {
		return khdao.findAccountOrEmail(username);
	}
	
	
	
}
