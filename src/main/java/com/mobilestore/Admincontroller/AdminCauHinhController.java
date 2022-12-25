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

import com.mobilestore.model.CauHinh;
import com.mobilestore.model.HangSanXuat;
import com.mobilestore.service.CauHinhService;
import com.mobilestore.service.HangSXService;
import com.mobilestore.service.SessionService;

@Controller
@RequestMapping("/admin")
public class AdminCauHinhController {
	@Autowired
	CauHinhService chService;
	
	@Autowired
	HangSXService hsxService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/cauhinh")
	public String cauhinh_index(@RequestParam("p") Optional<Integer> p, Model model,
			@ModelAttribute("cauhinh") CauHinh cauHinh) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<CauHinh> pages = chService.findAllByDaXoaFalse(pageable);
		
		List<HangSanXuat> hangSX = hsxService.findAll();
		
		model.addAttribute("hangsx", hangSX);
		model.addAttribute("pages", pages);
		return "admin/cauhinh";
	}
	
	@RequestMapping("/cauhinh/new")
	public String cauhinh_reset() {
		return "redirect:/admin/cauhinh";
	}

	@RequestMapping("/cauhinh/create")
	public String cauhinh_addNew(Model model,@Valid @ModelAttribute("cauhinh") CauHinh ch,
			BindingResult bindingResult,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<CauHinh> pages = chService.findAllByDaXoaFalse(pageable);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("pages", pages);
			model.addAttribute("cauhinh", ch);
			return "admin/cauhinh";
		}else {
			chService.save(ch);
			model.addAttribute("message", "Thêm mới thành công");
			return "redirect:/admin/cauhinh";
		}
	}

	@RequestMapping("/cauhinh/update")
	public String cauhinh_update(Model model, @Valid @ModelAttribute("cauhinh") CauHinh ch,
			 BindingResult bindingResult, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<CauHinh> pages = chService.findAllByDaXoaFalse(pageable);
		List<HangSanXuat> hangSX = hsxService.findAll();

		
		if(!chService.existsById(ch.getMaCH())) {
			model.addAttribute("message", "Không tìm thấy Cấu hình");
			model.addAttribute("hangsx", hangSX);
			model.addAttribute("pages", pages);
			model.addAttribute("cauhinh", ch);
			return "admin/cauhinh";
		}else if(bindingResult.hasErrors()) {
			model.addAttribute("message", "Cập nhật thất bại");
			model.addAttribute("hangsx", hangSX);						
			model.addAttribute("pages", pages);
			model.addAttribute("cauhinh", ch);
			return "admin/cauhinh";
		}else {
			model.addAttribute("message", "Cập nhật thành công");
			chService.update(ch);
			return "redirect:/admin/cauhinh/edit/" + ch.getMaCH();

		}
	}
	
	@RequestMapping("/cauhinh/search")
	public String cauhinh_search(Model model, @RequestParam("keyword") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p, @ModelAttribute("cauhinh") CauHinh ch) {
		String kwords = kw.orElse(sessionService.get("keyword",""));
		sessionService.get("keyword",kwords);
		List<CauHinh> pages = chService.findAllByKeyword("%"+kwords+"%");
		List<HangSanXuat> hangSX = hsxService.findAll();

		if (kwords.equals("")) {
			return "redirect:/admin/cauhinh";
		}else {
			model.addAttribute("pages", pages);
			model.addAttribute("hangsx", hangSX);
			model.addAttribute("deletePage", 1);
			return "admin/cauhinh";	
		}

	}

	@RequestMapping("/cauhinh/delete/{mach}")
	public String cauhinh_delete(Model model, @ModelAttribute("cauhinh") CauHinh ch,
			@PathVariable("mach") Integer maCH, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<CauHinh> pages = chService.findAllByDaXoaFalse(pageable);
		List<HangSanXuat> hangSX = hsxService.findAll();
		
		if(maCH.equals(0)) {
			model.addAttribute("message", "Vui lòng chọn một cấu hình để xóa");
			model.addAttribute("pages", pages);
			model.addAttribute("hangsx", hangSX);
			return "admin/cauhinh";
		}else {
			CauHinh cauHinh = chService.findById(maCH);
			cauHinh.setDaXoa(true);
			chService.save(cauHinh);
			model.addAttribute("message", "Xóa thành công");	
			return "redirect:/admin/cauhinh";
		}
	}

	@RequestMapping("/cauhinh/edit/{mach}")
	public String cauhinh_edit(Model model, @PathVariable("mach") Integer maCH,
			@RequestParam("p") Optional<Integer> p ) {
		CauHinh ch = chService.findById(maCH);
		model.addAttribute("cauhinh", ch);
		
		Pageable pageable = PageRequest.of(p.orElse(0),5);
		Page<CauHinh> pages = chService.findAllByDaXoaFalse(pageable);
		List<HangSanXuat> hangSX = hsxService.findAll();
		
		model.addAttribute("hangsx", hangSX);
		model.addAttribute("pages", pages);
		return "admin/cauhinh";
	}
}
