package com.mobilestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilestore.service.SessionService;

@Controller
public class SecurityController {

	
	@RequestMapping("/security/form/login")
	public String formLogin(Model model) {
		return "/form/dangnhap";
	}
	
	@RequestMapping("/security/form/logout")
	public String formLogout(Model model) {
		return "/form/dangnhap";
	}
	
	@RequestMapping("/form/login/success")
	public String loginSuccess(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("principal" , principal);
		model.addAttribute("message", "Đăng nhập thành công !");
		return "forward:/security/form/login";
	}
	
	@RequestMapping("/form/login/fail")
	public String loginFail(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập !");
		return "forward:/security/form/login";
	}
	
	@RequestMapping("/security/access/denied")
	public String accessDenied(Model model) {
		model.addAttribute("message", "Không có quyền truy cập !");
		return "forward:/security/form/login";
	}
	
	
	
}

