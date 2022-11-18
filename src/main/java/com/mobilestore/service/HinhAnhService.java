package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.HinhAnhDAO;
import com.mobilestore.model.HinhAnh;

@Service
public class HinhAnhService {
	@Autowired
	HinhAnhDAO hinhanhdao;

	public List<HinhAnh> findAll() {
		return hinhanhdao.findAll();
	}
	
	public HinhAnh findById(Integer id) {
		return hinhanhdao.findById(id).get();
	}

}
