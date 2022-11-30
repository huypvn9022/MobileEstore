package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.SanPham;


@Service
public class SanPhamService {
	@Autowired
	SanPhamDAO dao;
	
	public List<SanPham> findAll() {
		return dao.findAll();
	}
	
	public SanPham findById(Integer maSP) {
		return dao.findById(maSP).get();
	}
	
	public SanPham save(SanPham maSP) {
		return dao.save(maSP);
	}
	
	public SanPham update(SanPham maSP) {
		return dao.save(maSP);
	}
	
	public void delete(Integer maSP) {
		 dao.deleteById(maSP);
	}
	
	public Page<SanPham> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Boolean existsById(Integer maSP) {
		return dao.existsById(maSP);
	}
	
	public List<SanPham> findAllByKeyword(String keyword) {
		return dao.findAllByKeyword(keyword);
	}

	
}
