package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.HangSanXuatDAO;
import com.mobilestore.model.HangSanXuat;


@Service
public class HangSXService {
	@Autowired
	HangSanXuatDAO dao;
	
	public List<HangSanXuat> findAll() {
		return dao.findAll();
	}
	
	public HangSanXuat findById(String maHang) {
		return dao.findById(maHang).get();
	}
	
//	public CauHinh save(CauHinh maCH) {
//		return dao.save(maCH);
//	}
//	
//	public CauHinh update(CauHinh maCH) {
//		return dao.save(maCH);
//	}
//	
//	public void delete(Integer maCH) {
//		 dao.deleteById(maCH);
//	}
//	
//	public Page<CauHinh> findAll(Pageable pageable) {
//		return dao.findAll(pageable);
//	}
//	
//	public Boolean existsById(Integer maCH) {
//		return dao.existsById(maCH);
//	}
//	
//	public Page<CauHinh> findAllByKeyword(Pageable pageable, String keyword) {
//		return dao.findAllByKeyword(pageable,keyword);
//	}
	
}
