package com.mobilestore.Admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.model.ChiTietDonHang;
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
	public String thongke(Model model ) {
		Pageable pageable =  PageRequest.of(0, 5);
		List<Top5SP> top5sp = tkService.getTop5(pageable).getContent();
		List<DoanhThuThang> doanhThuThang = tkService.getDoanhThuThang(pageable).getContent();
		List<DoanhThuNam> doanhThuNam = tkService.getDoanhThuNam(pageable).getContent();
		List<DoanhThuNgay> doanhThuNgay = tkService.getDoanhThuNgay();

		model.addAttribute("top5sp", top5sp);
		model.addAttribute("doanhThuThang", doanhThuThang);
		model.addAttribute("doanhThuNam", doanhThuNam);
		model.addAttribute("doanhThuNgay", doanhThuNgay);
		return "admin/thongke";
	}
}
