package com.mobilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilestore.dao.HangSanXuatDAO;
import com.mobilestore.entity.HangSanXuat;

@Service
public class HangSanXuatService {
	@Autowired
	HangSanXuatDAO dao;

	public List<HangSanXuat> findAll() {
		return dao.findAll();
	}
}
