package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.NhanVienDAO;
import com.mobilestore.model.NhanVien;

@Service
public class NhanVienService {
	@Autowired
	NhanVienDAO nhanviendao;

	public List<NhanVien> findAll() {
		return nhanviendao.findAll();
	}
}
