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

import com.mobilestore.model.NhanVien;
import com.mobilestore.model.VaiTro;
import com.mobilestore.service.NhanVienService;
import com.mobilestore.service.SessionService;
import com.mobilestore.service.VaiTroService;

@Controller
@RequestMapping("/admin")
public class AdminNhanVienController {
	@Autowired
	NhanVienService nvService;

	@Autowired
	VaiTroService vtService;
	
	@Autowired
	SessionService sessionService;

	@RequestMapping("/nhanvien")
	public String nhanvieng_index(@RequestParam("p") Optional<Integer> p, Model model,
			@ModelAttribute("nhanVien") NhanVien nhanVien) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanVien> pages = nvService.findAllByDaXoaFalse(pageable);
		List<VaiTro> listVaiTro = vtService.findAll();
		NhanVien nv = nvService.findById(sessionService.get("taiKhoanNV"));
		if(nv.getVaiTroNV().getId().equals("NV")) {
			return "admin/phanquyen";
		} else {
			model.addAttribute("listVaiTro", listVaiTro);
			model.addAttribute("pages", pages);
			return "admin/nhanvien";
		}
	}

	@RequestMapping("/nhanvien/new")
	public String nhanvien_reset() {
		return "redirect:/admin/nhanvien";
	}

	@RequestMapping("/nhanvien/create")
	public String nhanvien_addNew(Model model, @Valid @ModelAttribute("nhanVien") NhanVien nv,
			BindingResult bindingResult, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanVien> pages = nvService.findAllByDaXoaFalse(pageable);
		List<VaiTro> listVaiTro = vtService.findAll();


		if (bindingResult.hasErrors()) {
			model.addAttribute("nhanVien", nv);
			model.addAttribute("pages", pages);
			model.addAttribute("listVaiTro", listVaiTro);
			return "admin/nhanvien";
		} else {
			nvService.save(nv);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/nhanvien";
		}
	}

	
	@RequestMapping("/nhanvien/update")
	public String nhanvien_update(Model model, @Valid @ModelAttribute("nhanVien") NhanVien nv,
			BindingResult bindingResult, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanVien> pages = nvService.findAllByDaXoaFalse(pageable);
		List<VaiTro> listVaiTro = vtService.findAll();

		if (!nvService.existsById(nv.getTaiKhoan())) {
			model.addAttribute("message", "Không tìm thấy nhân viên");
			model.addAttribute("listVaiTro", listVaiTro);
			model.addAttribute("pages", pages);
			model.addAttribute("nhanVien", nv);
			return "admin/nhanvien";
		} else if (bindingResult.hasErrors()) {	
			model.addAttribute("message", "Cập nhật thất bại");
			model.addAttribute("listVaiTro", listVaiTro);
			model.addAttribute("pages", pages);
			model.addAttribute("nhanVien", nv);
			return "admin/khachhang";
		} else {
			nvService.update(nv);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/nhanvien/edit/" + nv.getTaiKhoan();

		}
	}

	
	@RequestMapping("/nhanvien/search")
	public String nhanvien_search(Model model, @RequestParam("keyword") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p, @ModelAttribute("nhanVien") NhanVien nhanVien) {
		String kwords = kw.orElse(sessionService.get("keyword", ""));
		sessionService.get("keyword", kwords);
		List<NhanVien> pages = nvService.findAllByKeyword("%" + kwords + "%");
		List<VaiTro> listVaiTro = vtService.findAll();

		if (kwords.equals("")) {
			return "redirect:/admin/nhanvien";
		} else {
			model.addAttribute("listVaiTro", listVaiTro);
			model.addAttribute("pages", pages);
			model.addAttribute("nhanVien", nhanVien);
			model.addAttribute("deletePage", 1);
			return "admin/nhanvien";
		}

	}

	
	@RequestMapping("/nhanvien/delete/{taiKhoan}")
	public String nhanvien_delete(Model model, @ModelAttribute("nhanVien") NhanVien nv,
			@PathVariable("taiKhoan") String tk, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanVien> pages = nvService.findAllByDaXoaFalse(pageable);
		List<VaiTro> listVaiTro = vtService.findAll();

		if (tk.equals("null")) {
			model.addAttribute("message", "Vui lòng chọn một tài khoản để xóa");
			model.addAttribute("pages", pages);
			model.addAttribute("listVaiTro", listVaiTro);		
			return "admin/nhanvien";	
		} else {
			NhanVien nhanVien = nvService.findById(tk);
			nhanVien.setDaXoa(true);
			nvService.update(nhanVien);	
			model.addAttribute("message", "Xóa thành công");
			return "redirect:/admin/nhanvien";
		}
	}

	
	@RequestMapping("/nhanvien/edit/{taiKhoan}")
	public String nhanvien_edit(Model model, @PathVariable("taiKhoan") String taiKhoan,
			@RequestParam("p") Optional<Integer> p) {
		NhanVien nv = nvService.findById(taiKhoan);
		model.addAttribute("nhanVien", nv);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NhanVien> pages = nvService.findAllByDaXoaFalse(pageable);
		List<VaiTro> listVaiTro = vtService.findAll();
		
		model.addAttribute("listVaiTro", listVaiTro);
		model.addAttribute("pages", pages);
		return "admin/nhanvien";
	}
}
