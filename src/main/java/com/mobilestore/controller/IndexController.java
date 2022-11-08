package com.mobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.entity.HangSanXuat;
import com.mobilestore.entity.SanPham;
import com.mobilestore.service.HangSanXuatService;
import com.mobilestore.service.SanPhamService;

@Controller
public class IndexController {
	@Autowired
	HangSanXuatService hangSXService;
	
	@Autowired
	SanPhamService sanPhamService;
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("hangsx", hangSXService.findAll());
		
//		model.addAttribute("sanpham", sanPhamService.findAll());
		
//		SanPhamService sanphamentity = new SanPhamService();
//		List<SanPham> sanphamentities = sanPhamService.findAll();
		
//		model.addAttribute("sanphamentity", sanphamentity);
//		model.addAttribute("sanphamentities", sanphamentities);
		return "layout/index";
	}
	
//	@RequestMapping("/index")
//	public String index1(Model model) {
//		
//		SanPhamService sanphamentity = new SanPhamService();
//		List<SanPham> sanphamentities = sanPhamService.findAll();
//		
//		model.addAttribute("sanphamentity", sanphamentity);
//		model.addAttribute("sanphamentities", sanphamentities);
//		
//		return "layout/index";
//	}
	
}
