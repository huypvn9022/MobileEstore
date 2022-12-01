package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.NhanVienDAO;
import com.mobilestore.model.NhanVien;

<<<<<<< HEAD
@Service
public class NhanVienService {
	@Autowired
	NhanVienDAO nhanviendao;

	public List<NhanVien> findAll() {
		return nhanviendao.findAll();
	}
=======


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
	
>>>>>>> e601094b900b44858e2945c75c292780735634a4
}
