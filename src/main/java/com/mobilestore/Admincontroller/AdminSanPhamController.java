package com.mobilestore.Admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminSanPhamController {
	@RequestMapping("/sanpham")
	public String sanPham_index() {
		return "admin/sanpham";
	}
}
