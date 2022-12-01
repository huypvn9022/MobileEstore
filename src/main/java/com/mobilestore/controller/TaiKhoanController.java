package com.mobilestore.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobilestore.model.KhachHang;
import com.mobilestore.model.MailInfo;
import com.mobilestore.model.VaiTro;
import com.mobilestore.service.KhachHangService;
import com.mobilestore.service.MailerService;
import com.mobilestore.service.SessionService;
import com.mobilestore.service.VaiTroService;

@Controller
@RequestMapping("/index")
public class TaiKhoanController {
	@Autowired
	KhachHangService khService;

	@Autowired
	MailerService mailService;
	
	@Autowired
	SessionService session;

	@Autowired
	VaiTroService vtService;

	@GetMapping("/dangky")
	public String dangky(Model model, @ModelAttribute("dangky") KhachHang kh) {
		return "form/dangky";
	}

	@PostMapping("/dangky")
	public String dangky_submit(Model model, @Valid @ModelAttribute("dangky") KhachHang kh,
			BindingResult bingdingResult) {
		VaiTro vt = vtService.findById("KH");
		kh.setVaiTroKH(vt);

		if (bingdingResult.hasErrors()) {
			return "form/dangky";
		} else {
			if (khService.existsById(kh.getTaiKhoan())) {
				model.addAttribute("message", "Tài khoản này đã tồn tại vui lòng tạo tài khoản khác");
				return "form/dangky";
			} else {
				khService.save(kh);
				return "redirect:/security/form/login";
			}
		}
	}

	@GetMapping("/doimk")
	public String doimk(Model model) {
		return "form/Doimk";
	}

	@PostMapping("/doimk")
	public String doimk_submit(Model model, @RequestParam("taiKhoan") String tk, @RequestParam("matKhauCu") String mkCu,
			@RequestParam("matKhauMoi") String mkMoi, @RequestParam("matKhauMoi2") String mkMoi2) {

		if (tk.isBlank() || mkCu.isBlank() || mkMoi.isBlank() || mkMoi2.isBlank()) {
			model.addAttribute("message", "Vui lòng không để trống dữ liệu");
			return "form/Doimk";
		} else {

			if (!khService.existsById(tk)) {
				model.addAttribute("message", "Sai thông tin tài khoản hoặc tài khoản không tồn tại");
				return "form/Doimk";
			} else {
				KhachHang kh = khService.findById(tk);

				if (!kh.getMatKhau().equals(mkCu)) {
					model.addAttribute("message", "Mật khẩu cũ không khớp với tài khoản");
					return "form/Doimk";
				} else if (!mkMoi.equals(mkMoi2)) {
					model.addAttribute("message", "Nhập lại mật khẩu không khớp vui lòng nhập lại");
					return "form/Doimk";
				} else {
					kh.setTaiKhoan(tk);
					kh.setMatKhau(mkMoi2);
					khService.save(kh);
					model.addAttribute("message", "Đổi mật khẩu thành công");
					return "form/Doimk";
				}
			}
		}
	}

	@GetMapping("/thong-tin-tai-khoan")
	public String thongTinTK(Model model, @ModelAttribute("khachhang") KhachHang kh) {
		String tk  = session.get("taiKhoanKH");
		String mk =  session.get("matKhau");
		KhachHang khachhang = khService.findById(tk);
		kh.setTaiKhoan(tk);
		kh.setMatKhau(mk);

		model.addAttribute("khachhang", khachhang);
		return "form/TTTaiKhoan";
	}

	@PostMapping("/thong-tin-tai-khoan")
	public String thongTinTK_submit(Model model,@Valid @ModelAttribute("khachhang") KhachHang kh, BindingResult bindingResult) {
		String tk  = session.get("taiKhoanKH");
		VaiTro vt = vtService.findById(session.get("vaiTroKH"));
		KhachHang khachhang = khService.findById(tk);		
		
		kh.setTaiKhoan(khachhang.getTaiKhoan());
		kh.setMatKhau(khachhang.getMatKhau());
		kh.setVaiTroKH(vt);
		
		if(!bindingResult.hasErrors()) {
			model.addAttribute("message", "Cật nhập thất bại");
			model.addAttribute("khachhang", khachhang);
			return "form/TTTaiKhoan";
		}else {
			khService.save(kh);
			model.addAttribute("message", "Cật nhập thành công");
			return "form/TTTaiKhoan";
		}
		
	}

	@GetMapping("/quenmk")
	public String quenMK_submit() {
		return "form/QMatKhau";
	}

	@PostMapping("/quenmk")
	public String quenMK(@RequestParam String username, @RequestParam String email, Model model) {

		if (username.isBlank() || email.isBlank()) {
			model.addAttribute("message", "Vui lồng không để trống dữ liệu!");
			return "form/QMatKhau";
		} else {
			if (!khService.existsById(username)) {
				model.addAttribute("message", "Tài khoản không tồn tại");
			} else {
				KhachHang acc = khService.findById(username);
				String newPass = ((long) Math.floor(Math.random() * (999999999 - 100000000 + 1) + 100000000)) + "";
				acc.setMatKhau(newPass);
				khService.save(acc);
				try {
					MailInfo mail = new MailInfo();
					mail.setFrom("tientai9a9@gmail.com");
					mail.setTo(email);
					mail.setSubject("Mobile Estore - Lấy lại mật khẩu");
					mail.setBody("Xin chào " + acc.getHoTen() + ", mật khẩu của bạn là: " + newPass + ""
							+ ". Vui lòng không được gửi mật khẩu này cho bất cứ để tránh mất thông tin.");
					mailService.send(mail);
					model.addAttribute("message", "Gửi thành công!");
				} catch (MessagingException e) {
					model.addAttribute("message", "Gửi thất bại!");
					e.printStackTrace();
				}
				model.addAttribute("message", "Chúng tôi đã gửi mật khẩu về email của bạn, vui lòng kiểm tra email!");
			}
			return "form/QMatKhau";
		}

	}
}
