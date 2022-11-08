package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.entity.SanPham;

@Service
public class SanPhamService {
	@Autowired
	SanPhamDAO dao;

	public List<SanPham> findAll() {
		return dao.findAll();
	}
	
	
}
