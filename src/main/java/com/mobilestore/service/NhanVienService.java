package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.NhanVienDAO;
import com.mobilestore.model.NhanVien;



@Service
public class NhanVienService {

	@Autowired
	NhanVienDAO nvdao;
	
	public List<NhanVien> findAll() {
		return nvdao.findAll();
	}
	
	public NhanVien findById(String username) {
		return nvdao.findById(username).get();
	}
	
	public NhanVien findAccountorEmail(String taikhoan) {
		return nvdao.findAccountOrEmail(taikhoan);
	}
	
}
