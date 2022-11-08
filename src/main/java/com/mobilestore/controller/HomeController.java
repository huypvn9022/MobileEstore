package com.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.entity.SanPham;
import com.mobilestore.service.HangSanXuatService;
import com.mobilestore.service.SanPhamService;

@Controller
public class HomeController {
	
	@Autowired
	HangSanXuatService hangSXService;
	
	@Autowired
	SanPhamService sanPhamService;
	
	@RequestMapping("/index")
	public String list(Model model) {
		List<SanPham> listsanpham = sanPhamService.findAll();
		
		model.addAttribute("hangsx", hangSXService.findAll());
		model.addAttribute("listsanpham", listsanpham);
		
		return "layout/index";
	}
	
	@RequestMapping("/shop-grid")
	public String index2(Model model) {
		return "layout/shop-grid";
	}
	@RequestMapping("/cart")
	public String cart(Model model) {
		return "layout/GioHang";
	}
	@RequestMapping("/product-details")
	public String productDetails(Model model) {
		return "layout/ChiTietGioHang";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		return "admin/index";
	}
	
	
}
