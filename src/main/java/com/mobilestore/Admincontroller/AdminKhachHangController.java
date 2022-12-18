package com.mobilestore.Admincontroller;

import java.util.List;
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
import com.mobilestore.model.VaiTro;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.SessionService;
import com.mobilestore.service.VaiTroService;

@Controller
@RequestMapping("/admin")
public class AdminKhachHangController {
	@Autowired
	KhachHangService khService;
	
	@Autowired
	VaiTroService vtService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/khachhang")
	public String khachhang_index(@RequestParam("p") Optional<Integer> p, Model model,
			@ModelAttribute("khachHang") KhachHang khachHang) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAllByDaXoaFalse(pageable);
		
		model.addAttribute("pages", pages);
		return "admin/khachhang";
	}
	
	@RequestMapping("/khachhang/new")
	public String khachhang_reset() {
		return "redirect:/admin/khachhang";
	}
	
	@RequestMapping("/khachhang/create")
	public String khachhang_addNew(Model model,@Valid @ModelAttribute("khachHang") KhachHang kh,
			BindingResult bindingResult,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAllByDaXoaFalse(pageable);
		VaiTro vaitro = vtService.findById("KH");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("pages", pages);
			model.addAttribute("khachHang", kh);
			return "admin/khachhang";
		}else {
			kh.setVaiTroKH(vaitro);
			khService.save(kh);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/khachhang";
		}
	}
	
	@RequestMapping("/khachhang/update")
	public String khachhang_update(Model model, @Valid @ModelAttribute("khachHang") KhachHang kh,
			 BindingResult bindingResult, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAllByDaXoaFalse(pageable);
		VaiTro vaitro = vtService.findById("KH");

		if(!khService.existsById(kh.getTaiKhoan())) {
			model.addAttribute("message", "Không tìm thấy khách hàng");
			
			model.addAttribute("pages", pages);
			model.addAttribute("khachHang", kh);
			return "admin/khachhang";
		}else if(bindingResult.hasErrors()) {
			model.addAttribute("message", "Cập nhật thất bại");
						
			model.addAttribute("pages", pages);
			model.addAttribute("khachHang", kh);
			return "admin/khachhang";
		}else {			
			kh.setVaiTroKH(vaitro);
			khService.update(kh);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/khachhang/edit/" + kh.getTaiKhoan();

		}
	}
	
	@RequestMapping("/khachhang/search")
	public String khachhang_search(Model model, @RequestParam("keyword") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p, @ModelAttribute("khachHang") KhachHang kh) {
		String kwords = kw.orElse(sessionService.get("keyword",""));
		sessionService.get("keyword",kwords);
		List<KhachHang> pages = khService.findAllByKeyword("%"+kwords+"%");
		
		if(kwords.equals("")) {
			return "redirect:/admin/khachhang";
		}else {
			model.addAttribute("pages", pages);
			model.addAttribute("khachHang", kh);
			model.addAttribute("deletePage", 1);	
			return "admin/khachhang";
		}
	}
	
	@RequestMapping("/khachhang/delete/{taiKhoan}")
	public String khachhang_delete(Model model, @ModelAttribute("khachHang") KhachHang kh,
			@PathVariable("taiKhoan") String tk, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAllByDaXoaFalse(pageable);
		
		if(tk.equals("null")) {
			model.addAttribute("message", "Vui lòng chọn một tài khoản để xóa");
			model.addAttribute("pages", pages);
			return "admin/khachhang";
		}else {	
			KhachHang khachHang = khService.findById(tk);
			khachHang.setDaXoa(true);
			khService.update(khachHang);
			model.addAttribute("message", "Xóa thành công");	
			return "redirect:/admin/khachhang";
		}
	}
	
	@RequestMapping("/khachhang/edit/{taiKhoan}")
	public String khachhang_edit(Model model, @PathVariable("taiKhoan") String taiKhoan,
			@RequestParam("p") Optional<Integer> p ) {
		KhachHang kh = khService.findById(taiKhoan);
		model.addAttribute("khachHang", kh);
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<KhachHang> pages = khService.findAllByDaXoaFalse(pageable);
		
		model.addAttribute("pages", pages);
		return "admin/khachhang";
	}
}
