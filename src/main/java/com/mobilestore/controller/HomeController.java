package com.mobilestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.model.CauHinh;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.KhachHang;
import com.mobilestore.model.SanPham;
import com.mobilestore.service.CauHinhService;
import com.mobilestore.service.CookieService;
import com.mobilestore.service.HangSXService;
import com.mobilestore.service.HinhAnhService;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.LoaiSPService;
import com.mobilestore.service.SanPhamService;
import com.mobilestore.service.SessionService;

@Controller
public class HomeController {
	
	@Autowired
	HangSXService hangSXService;
	
	@Autowired
	SanPhamService spService;
	
	@Autowired
	LoaiSPService loaispService;
	
	@Autowired
	HinhAnhService hinhanhService;
	
	@Autowired
	CauHinhService cauhinhService;
	
	@Autowired
	SessionService session;
	
	@Autowired
	KhachHangService khservice;
	
	@Autowired
	CookieService cookieService;
	
	@RequestMapping("/index")
	public String list(Model model) {
		List<SanPham> listsp = spService.findAll();
		List<HinhAnh> images = hinhanhService.findAll();
	
		model.addAttribute("images", images);
		model.addAttribute("listsp", listsp);
		
		return "layout/index";
	}
	
	
	// product detail
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		SanPham sanpham = spService.findById(id);
		double priceOld = sanpham.getDonGia() - ((sanpham.getDonGia() / 100) * 5);
		model.addAttribute("priceOld", priceOld);
		
		// hiện thị ảnh chính theo id
		HinhAnh anh = hinhanhService.findById(id);
		
		// Hiện thị ảnh chính giỏ hàng
		List<HinhAnh> anhCart = hinhanhService.findAll();
		
		// lưu các sản phẩm thành một list
		List<String> listAnh = new ArrayList<String>();
		listAnh.add(anh.getHinhAnh1());
		listAnh.add(anh.getHinhAnh2());
		listAnh.add(anh.getHinhAnh3());
		listAnh.add(anh.getHinhAnh4());
		
		// hiện thị cấu hình sản phẩm
		CauHinh cauHinh = cauhinhService.findById(id);
		
		// hiện thị các ảnh sản phẩm cùng hãng
		List<HinhAnh> listImg = hinhanhService.findAll(); 
		
		model.addAttribute("hangsx", hangSXService.findAll());
		model.addAttribute("anhs", anh);
		model.addAttribute("sanpham", sanpham);
		model.addAttribute("listAnh", listAnh);
		model.addAttribute("cauHinh", cauHinh);
		model.addAttribute("listImg", listImg);
		model.addAttribute("anhCart", anhCart);
		return "layout/ChiTietSanPham";
	}
	

	@RequestMapping("/cart")
	public String cart(Model model) {
		// hiện thị ảnh giỏ hàng
		List<HinhAnh> lisImg = hinhanhService.findAll();
		model.addAttribute("listImg" ,lisImg);	
		return "layout/giohang";
	} 
	
	// thanh toán
	@RequestMapping("/user/checkout")
	public String pay(Model model, @ModelAttribute("khachhang") KhachHang kh) {
		String taikhoankh = session.get("taiKhoanKH");
		KhachHang khachhang = khservice.findById(taikhoankh);
		model.addAttribute("khachhang", khachhang);
		return "layout/thanhtoan";
	}

	
	@RequestMapping("/product-details")
	public String productDetails(Model model) {
		return "layout/ChiTietSanPham";
	}

	
//	@RequestMapping("/order-management")
//	public String orderManagement(Model model) {
//		return "layout/qldonhang";
//	}

	@RequestMapping("/shop-grid")
	public String shopGrid(Model model) {
		return "layout/shop-grid";
	}
	
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		return "admin/index";
	}
}
