package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.NhanVienDAO;
import com.mobilestore.model.NhanVien;

@Service
public class NhanVienService {
	@Autowired
	NhanVienDAO dao;

	public List<NhanVien> findAll() {
		return dao.findAll();
	}

	public NhanVien findById(String taikhoan) {
		return dao.findById(taikhoan).get();
	}

	public NhanVien save(NhanVien taiKhoan) {
		return dao.save(taiKhoan);
	}

	public NhanVien update(NhanVien taiKhoan) {
		return dao.save(taiKhoan);
	}

	public void delete(String taiKhoan) {
		dao.deleteById(taiKhoan);
	}

	public Page<NhanVien> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Page<NhanVien> findAllByDaXoaFalse(Pageable pageable) {
		return dao.findAllByDaXoaFalse(pageable);
	}

	public Boolean existsById(String taikhoan) {
		return dao.existsById(taikhoan);
	}

	public List<NhanVien> findAllByKeyword( String keyword) {
		return dao.findAllByKeyword(keyword);
	}

	public NhanVien findAccountorEmail(String taikhoan) {
		return dao.findAccountOrEmail(taikhoan);
	}

}
