package com.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.dao.HinhAnhDAO;
import com.mobilestore.dao.SanPhamDAO;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.LoaiSanPham;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.HangSanXuatService;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.LoaiSanPhamService;
import com.mobilestore.service.SanPhamService;

@Controller
public class HomeController {
	
	@Autowired
	HangSanXuatService hangSXService;
	
	@Autowired
	SanPhamService spService;
	
	@Autowired
	LoaiSanPhamService loaispService;
	
	@Autowired
	HinhAnhService hinhanhService;
	
	@RequestMapping("/index")
	public String list(Model model) {
		List<SanPham> listsp = spService.findAll();
		List<HinhAnh> images = hinhanhService.findAll();
	
		model.addAttribute("images", images);
		model.addAttribute("hangsx", hangSXService.findAll());
		model.addAttribute("listsp", listsp);
		
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
