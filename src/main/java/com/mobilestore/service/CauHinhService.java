package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.CauHinhDAO;
import com.mobilestore.model.CauHinh;

@Service
public class CauHinhService {

	@Autowired
	CauHinhDAO cauHinhDAO;
	
	public List<CauHinh> findAll(){
		return cauHinhDAO.findAll();
	}
	
	public CauHinh findById(Integer id) {
		return cauHinhDAO.findById(id).get();
	}
	
}
