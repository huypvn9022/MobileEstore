package com.mobilestore.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.KhachHang;
import com.mobilestore.service.KhachHangSevice;
import com.mobilestore.service.SessionService;

@Controller
@RequestMapping("/admin")
public class KhachHangController {
	@Autowired
	KhachHangSevice khService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/khachhang")
	public String khachhang_index(@RequestParam("p") Optional<Integer> p, Model model,
			@ModelAttribute("khachHang") KhachHang khachHang) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAll(pageable);
		model.addAttribute("pages", pages);
		return "admin/khachhang";
	}
	
	@RequestMapping("/khachhang/new")
	public String khachhang_reset() {
		return "redirect:/admin/khachhang";
	}
	
	@RequestMapping("/khachhang/create")
	public String khachhang_addNew(Model model,@Valid @ModelAttribute("khachHang") KhachHang kh,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors() || kh.getTaiKhoan().equals(null)) {
			model.addAttribute("khachHang", kh);
			return "admin/khachhang";
		}else {
			khService.save(kh);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/khachhang";
		}
	}
	
	@RequestMapping("/khachhang/update")
	public String khachhang_update(Model model, @ModelAttribute("khachHang") KhachHang kh) {
		if(khService.existsById(kh.getTaiKhoan())) {
			khService.update(kh);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/khachhang/edit/" + kh.getTaiKhoan();
		}else {
			model.addAttribute("message", "Cập nhật thất bại");
			return "redirect:/admin/khachhang";
		}
	}
	
	@RequestMapping("/khachhang/search")
	public String khachhang_search(Model model, @RequestParam("keyword") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p, @ModelAttribute("khachHang") KhachHang kh) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		String kwords = kw.orElse(sessionService.get("keyword",""));
		sessionService.get("keyword",kwords);
		Page<KhachHang> pages = khService.findAllByKeyword(pageable,"%"+kwords+"%");
		model.addAttribute("pages", pages);
		model.addAttribute("khachHang", kh);
		return "admin/khachhang";
	}
	
	@RequestMapping("/khachhang/delete/{taiKhoan}")
	public String khachhang_delete(Model model, @PathVariable("taiKhoan") String tk) {
		if(tk.isBlank()) {
			model.addAttribute("message", "Vui lòng chọn tài khoản để xóa");
			return "redirect:/admin/khachhang";
		}else {
			model.addAttribute("message", "Xóa thành công");
			khService.delete(tk);
		}

		return "redirect:/admin/khachhang";
	}
	
	@RequestMapping("/khachhang/edit/{taiKhoan}")
	public String khachhang_edit(Model model, @PathVariable("taiKhoan") String taiKhoan,
			@RequestParam("p") Optional<Integer> p) {
		KhachHang kh = khService.findById(taiKhoan);
		model.addAttribute("khachHang", kh);
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAll(pageable);
		model.addAttribute("pages", pages);
		return "admin/khachhang";
	}
}
