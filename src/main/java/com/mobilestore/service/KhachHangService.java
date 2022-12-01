package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.KhachHangDAO;
import com.mobilestore.model.KhachHang;

@Service
public class KhachHangService {
<<<<<<< HEAD
	@Autowired
	KhachHangDAO khachhangdao;

	public List<KhachHang> findAll() {
		return khachhangdao.findAll();
	}
=======
	
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
	
	
	
>>>>>>> e601094b900b44858e2945c75c292780735634a4
}
