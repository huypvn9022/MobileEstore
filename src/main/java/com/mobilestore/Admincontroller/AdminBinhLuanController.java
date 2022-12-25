package com.mobilestore.Admincontroller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.BinhLuan;
import com.mobilestore.model.LoaiSanPham;
import com.mobilestore.service.BinhLuanService;
import com.mobilestore.service.LoaiSPService;


@Controller
@RequestMapping("/admin")
public class AdminBinhLuanController {	
	@Autowired
	BinhLuanService blService;
	
	@RequestMapping("/binhluan")
	public String findAll(Model model,@RequestParam("p") Optional<Integer> p) {
		
		Sort sort = Sort.by("masp.maSP").ascending();
		Pageable pageAble = PageRequest.of(p.orElse(0), 5, sort);
		Page<BinhLuan> listBinhLuan = blService.findAll(pageAble);
		
		model.addAttribute("listBinhLuan", listBinhLuan);
		return "admin/binhluan";
	}
	
	@RequestMapping("/binhluan/delete/{maBL}")
	public String loaiSP_delete(Model model, @PathVariable("maBL") Integer maBL) {
		BinhLuan bl = blService.findByID(maBL);
		blService.delete(maBL);
		return "redirect:/admin/binhluan";
	}


}
