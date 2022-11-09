package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.LoaiSanPhamDAO;
import com.mobilestore.entity.LoaiSanPham;

@Service
public class LoaiSanPhamService {

	@Autowired
	LoaiSanPhamDAO loaisp;
	
	public List<LoaiSanPham> findAll(){
		return loaisp.findAll();
	}
	
}

