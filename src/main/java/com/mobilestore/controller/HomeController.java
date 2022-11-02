package com.mobilestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "layout/DangKy";
	}
	@RequestMapping("/shop-grid")
	public String index2(Model model) {
		return "layout/shop-grid";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		return "admin/index";
	}
	
}
