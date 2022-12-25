package com.mobilestore.Admincontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.DoanhThuNam;
import com.mobilestore.model.DoanhThuNgay;
import com.mobilestore.model.DoanhThuThang;
import com.mobilestore.model.Top5SP;
import com.mobilestore.service.ThongKeService;

@Controller
@RequestMapping("/admin")
public class ThongKeController {

	@Autowired
	ThongKeService tkService;

	@RequestMapping("/thongke")
	public String thongke(Model model) {
		List<Top5SP> top5sp = tkService.getTopSP();
		List<DoanhThuThang> doanhThuThang = tkService.getDoanhThuThang();
		List<DoanhThuNam> doanhThuNam = tkService.getDoanhThuNam();
		List<DoanhThuNgay> doanhThuNgay = tkService.getDoanhThuNgay();
		
		model.addAttribute("top5sp", top5sp);
		model.addAttribute("doanhThuThang", doanhThuThang);
		model.addAttribute("doanhThuNam", doanhThuNam);
		model.addAttribute("doanhThuNgay", doanhThuNgay);
		return "admin/thongke";
	}
}
