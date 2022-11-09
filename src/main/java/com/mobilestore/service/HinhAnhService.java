package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.HinhAnhDAO;
import com.mobilestore.entity.HinhAnh;

@Service
public class HinhAnhService {
	@Autowired
	HinhAnhDAO hinhanhdao;

	public List<HinhAnh> getAnhs() {
		return hinhanhdao.findAll();
	}

}
