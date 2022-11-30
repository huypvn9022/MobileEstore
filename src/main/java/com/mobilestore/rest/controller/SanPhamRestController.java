package com.mobilestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.SanPhamService;

@RestController
@CrossOrigin("*")
public class SanPhamRestController {
	
	@Autowired
	SanPhamService spservice;
	
	@Autowired
	HinhAnhService haservice;

	@GetMapping("/rest/products/{id}")
	public SanPham getOne(@PathVariable("id") Integer id) {
		return spservice.findById(id);
	}
	
	@GetMapping("/cart/images")
	public List<HinhAnh> getImage(Model model){
		return haservice.findAll();
	}
	
}
