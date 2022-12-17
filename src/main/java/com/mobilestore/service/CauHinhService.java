package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.CauHinhDAO;
import com.mobilestore.model.CauHinh;

@Service
public class CauHinhService {

	@Autowired
	CauHinhDAO dao;
	
	public List<CauHinh> findAll() {
		return dao.findAll();
	}
	
	public CauHinh findById(Integer maCH) {
		return dao.findById(maCH).get();
	}
	
	public CauHinh save(CauHinh maCH) {
		return dao.save(maCH);
	}
	
	public CauHinh update(CauHinh maCH) {
		return dao.save(maCH);
	}
	
	public void delete(Integer maCH) {
		 dao.deleteById(maCH);
	}
	
	public Page<CauHinh> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	public Boolean existsById(Integer maCH) {
		return dao.existsById(maCH);
	}
	
	public List<CauHinh> findAllByKeyword( String keyword) {
		return dao.findAllByKeyword(keyword);
	}
	
	public Page<CauHinh> findAllByDaXoaFalse(Pageable pageable) {
		return dao.findAllByDaXoaFalse(pageable);
	}
	
	
}
