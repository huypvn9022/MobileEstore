package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.KhachHangDAO;
import com.mobilestore.model.KhachHang;

@Service
public class KhachHangService {
	@Autowired
	KhachHangDAO dao;
	
	public List<KhachHang> findAll(){
		return dao.findAll();
	}
	
	public KhachHang findById(String username) {
		return dao.findById(username).get();
	}
	
	public KhachHang save(KhachHang taiKhoan) {
		return dao.save(taiKhoan);
	}
	
	public KhachHang update(KhachHang taiKhoan) {
		return dao.save(taiKhoan);
	}
	
	public void delete(String taiKhoan) {
		 dao.deleteById(taiKhoan);
	}
	
	public Page<KhachHang> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Page<KhachHang> findAllByDaXoaFalse(Pageable pageable) {
		return dao.findAllByDaXoaFalse(pageable);
	}
	
	public Boolean existsById(String taikhoan) {
		return dao.existsById(taikhoan);
	}
	
	public List<KhachHang> findAllByKeyword(String keyword) {
		return dao.findAllByKeyword(keyword);
	}
	public KhachHang findAccountOrEmail(String username) {
		return dao.findAccountOrEmail(username);
	}
	
}
