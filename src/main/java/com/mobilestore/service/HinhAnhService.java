package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.HinhAnhDAO;
import com.mobilestore.model.HinhAnh;

@Service
public class HinhAnhService {
	@Autowired
	HinhAnhDAO dao;
	
	public List<HinhAnh> findAll() {
		return dao.findAll();
	}
	
	public HinhAnh findById(Integer maHinh) {
		return dao.findById(maHinh).get();
	}
	
	public HinhAnh save(HinhAnh maHinh) {
		return dao.save(maHinh);
	}
	
	public HinhAnh update(HinhAnh maHinh) {
		return dao.save(maHinh);
	}
	
	public void delete(Integer maHinh) {
		 dao.deleteById(maHinh);
	}
	
	public Page<HinhAnh> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Page<HinhAnh> findAllByDaXoaFalse(Pageable pageable) {
		return dao.findAllByDaXoaFalse(pageable);
	}
	
	public Boolean existsById(Integer maCH) {
		return dao.existsById(maCH);
	}
	
	public List<HinhAnh> findAllByKeyword(String keyword) {
		return dao.findAllByKeyword(keyword);
	}
	
	public List<HinhAnh> findByMaSP(Integer masp){
		return dao.findByMaSP(masp);
	}
	
}
