package com.mobilestore.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.CauHinh;
import com.mobilestore.model.HinhAnh;
import com.mobilestore.model.KhachHang;
import com.mobilestore.model.SanPham;
import com.mobilestore.model.Top5SP;
import com.mobilestore.service.CTDHService;
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
	CTDHService chitietdonhangService;

	SessionService session;
	
	@Autowired
	KhachHangService khservice;
	
	@Autowired
	CookieService cookieService;
	
	@RequestMapping("/index")
	public String list(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageableTop5SP = PageRequest.of(0, 5);
		Pageable pageableListdt = PageRequest.of(p.orElse(0), 10);
		Pageable pageableListpk = PageRequest.of(p.orElse(0), 10);
		List<Top5SP> top5sp = chitietdonhangService.getTop5(pageableTop5SP);
		Page<SanPham> listdt = spService.findAllByMaLoai(pageableListdt);
		Page<SanPham> listpk = spService.findAllByMaLoai1(pageableListpk);
		List<HinhAnh> images = hinhanhService.findAll();
		
		Set<Integer> imgSet = new HashSet<Integer>();
		images = images.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		
		model.addAttribute("top5sp", top5sp);
		model.addAttribute("images", images);
		model.addAttribute("listdt", listdt);
		model.addAttribute("listpk", listpk);
		return "layout/index";
	}
	
	
	// product detail
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		SanPham sanpham = spService.findById(id);
		double priceOld = sanpham.getDonGia() - ((sanpham.getDonGia() / 100) * 5);
		model.addAttribute("priceOld", priceOld);
		
		// hiện thị ảnh chính theo id
		List<HinhAnh> anh = hinhanhService.findByMaSP(id);
		Set<Integer> imgSet = new HashSet<Integer>();
		anh = anh.stream().filter( img -> imgSet.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		
		// Hiện thị ảnh chính giỏ hàng
		List<HinhAnh> anhCart = hinhanhService.findAll();
		
		
		// lưu các sản phẩm thành một list
		List<HinhAnh> listAnh = hinhanhService.findAll();
		
		// hiện thị cấu hình sản phẩm
		CauHinh cauHinh = cauhinhService.findById(id);
		
		// hiện thị các ảnh sản phẩm cùng hãng
		List<HinhAnh> listImg = hinhanhService.findAll();
		Set<Integer> setImg = new HashSet<Integer>();
		listImg = listImg.stream().filter(img -> setImg.add(img.getMasp().getMaSP())).collect(Collectors.toList());
		
		model.addAttribute("hangsx", hangSXService.findAll());
		model.addAttribute("anh", anh);
		model.addAttribute("sanpham", sanpham);
		model.addAttribute("listAnh", listAnh);
		model.addAttribute("cauHinh", cauHinh);
		model.addAttribute("listImg", listImg);
		model.addAttribute("anhCart", anhCart);
		return "layout/chitietsanpham";
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
		String tk = cookieService.getValue("taiKhoanKH",null);
		KhachHang khachhang = khservice.findById(tk);
		model.addAttribute("khachhang", khachhang);
		return "layout/thanhtoan";
	}

}
