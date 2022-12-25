package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.VaiTroDAO;
import com.mobilestore.model.VaiTro;



@Service
public class VaiTroService {
	@Autowired
	VaiTroDAO dao;
	
	public List<VaiTro> findAll() {
		return dao.findAll();
	}
	
	public VaiTro findById(String id) {
		return dao.findById(id).get();
	}
	
	public VaiTro save(VaiTro id) {
		return dao.save(id);
	}
	
	public VaiTro update(VaiTro id) {
		return dao.save(id);
	}
	
	public void delete(String id) {
		 dao.deleteById(id);
	}
}

















