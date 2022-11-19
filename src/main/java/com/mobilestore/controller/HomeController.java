package com.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.dao.HangSanXuatDAO;
import com.mobilestore.model.HangSanXuat;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	HangSanXuatDAO hangdao;
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "layout/comment";
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
	
	@RequestMapping
	public String doGetHome(Model model) {
		List<HangSanXuat> categoris = hangdao.findAll();
		model.addAttribute("category",categoris);
		return "layout/index";
	}
}
