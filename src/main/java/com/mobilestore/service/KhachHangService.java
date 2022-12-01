package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.KhachHangDAO;
import com.mobilestore.model.KhachHang;

@Service
public class KhachHangService {
	@Autowired
	KhachHangDAO khachhangdao;

	public List<KhachHang> findAll() {
		return khachhangdao.findAll();
	}
}
