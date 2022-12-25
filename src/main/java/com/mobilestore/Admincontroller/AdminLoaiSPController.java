package com.mobilestore.Admincontroller;

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

import com.mobilestore.model.LoaiSanPham;
import com.mobilestore.service.LoaiSPService;


@Controller
@RequestMapping("/admin")
public class AdminLoaiSPController {	
	@Autowired
	LoaiSPService lspService;
	
	@RequestMapping("/loaiSP")
	public String loaisp_index(Model model,@RequestParam("p") Optional<Integer> p,
			@ModelAttribute("loaisp") LoaiSanPham loaisp) {

		Pageable pageAble = PageRequest.of(p.orElse(0), 5);
		Page<LoaiSanPham> listLoaiSP = lspService.findAllByDaXoaFalse(pageAble);
		
		model.addAttribute("listLoaiSP", listLoaiSP);
		return "admin/loaisp";
	}
	
	
	@RequestMapping("/loaiSP/create")
	public String LoaiSP_create(Model model,@Valid @ModelAttribute("loaisp") LoaiSanPham loaisp,
			BindingResult bindingResult , @RequestParam("p") Optional<Integer> p) {
		Pageable pageAble = PageRequest.of(p.orElse(0), 5);
		Page<LoaiSanPham> listLoaiSP = lspService.findAllByDaXoaFalse(pageAble);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("loaisp", loaisp);
			model.addAttribute("listLoaiSP", listLoaiSP);
			return"admin/loaisp";
		}else {
			lspService.save(loaisp);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/loaiSP";
		}

	}
	
	@RequestMapping("/loaiSP/update")
	public String loaiSP_update(Model model,@Valid @ModelAttribute("loaisp") LoaiSanPham loaisp,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page) {

		Pageable pageAble = PageRequest.of(page.orElse(0), 5);
		Page<LoaiSanPham> listLoaiSP = lspService.findAllByDaXoaFalse(pageAble);
		
		if (!lspService.existsById(loaisp.getMaLoai())) {
			model.addAttribute("message", "Loại sản phẩm không tồn tại");
			model.addAttribute("listLoaiSP", listLoaiSP);
			return"admin/loaisp";
		}else if(bindingResult.hasErrors()) {
			model.addAttribute("loaisp", loaisp);
			model.addAttribute("listLoaiSP", listLoaiSP);
			return "admin/loaisp";
		}else {
			lspService.save(loaisp);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/loaiSP/edit/" + loaisp.getMaLoai();
		}
	}

	@RequestMapping("/loaiSP/delete/{maloai}")
	public String loaiSP_delete(Model model, @ModelAttribute("loaisp") LoaiSanPham loaisp,
			@PathVariable("maloai") String maLoai, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<LoaiSanPham> listLoaiSP = lspService.findAllByDaXoaFalse(pageable);
		
		if(maLoai.equalsIgnoreCase("null")) {
			model.addAttribute("message", "Vui lòng chọn một loại sản phẩm để xóa");
			model.addAttribute("listLoaiSP", listLoaiSP);
			return "admin/loaisp";
		}else {
			LoaiSanPham loaiSP = lspService.findById(maLoai);
			loaiSP.setDaXoa(true);
			lspService.update(loaiSP);
			model.addAttribute("message", "Xóa thành công");	
			return "redirect:/admin/loaiSP";
		}
	}

	@RequestMapping("/loaiSP/reset")
	public String loaiSP_reset(Model model) {	
		return "redirect:/admin/loaiSP";
	}

	@RequestMapping("/loaiSP/edit/{maloai}")
	public String loaiSP_edit(Model model, @PathVariable("maloai") String maloai,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageAble = PageRequest.of(p.orElse(0), 5);
		Page<LoaiSanPham> listLoaiSP = lspService.findAllByDaXoaFalse(pageAble);
		
		LoaiSanPham loaisp = lspService.findById(maloai);
		
		model.addAttribute("listLoaiSP", listLoaiSP);
		model.addAttribute("loaisp", loaisp);
		return "admin/loaisp";
	}
}
