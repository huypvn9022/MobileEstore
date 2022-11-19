package com.mobilestore.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.dao.HangSanXuatDAO;
import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.SanPham;


@Controller
@RequestMapping("/product")
public class SanPhamController {
	@Autowired
	HangSanXuatDAO hangdao;
	
	@Autowired
	SanPhamDAO spdao;
//	@RequestMapping
//	public String doGetHome(Model model) {
//		Pageable sortedByIDDesc = PageRequest.of(0, 8, Sort.by("maSP").descending());
//		List<HangSanXuat> categoris = hangdao.findAll();
//		model.addAttribute("category",categoris);
//		return "layout/header";
//	}
	
	
	@GetMapping("/{mahang}")
	public String iphone(@PathVariable("mahang") String mahang,Model model,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		System.out.println(mahang);
		Page<SanPham> products = spdao.findAllByMaHang(mahang,pageable);
		model.addAttribute("products",products);
		return "layout/shop-grid";
	}
}
