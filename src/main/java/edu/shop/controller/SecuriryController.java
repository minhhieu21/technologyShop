package edu.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.shop.entity.Product;
import edu.shop.service.ProductService;

@Controller
@RequestMapping("security")
public class SecuriryController {
		
	@GetMapping("login/form")
	public String loginForm(Model model){
		model.addAttribute("message", "Vui lòng đăng nhập");
		return "security/login";
	}
	
	@GetMapping("login/success")
	public String loginSuccess(Model model){
		model.addAttribute("message", "Đăng nhập thành công");
		return "redirect:/product/list";
	}
	
	@GetMapping("login/error")
	public String loginError(Model model){
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "security/login";
	}
	
	@GetMapping("unauthoried")
	public String unauthoried(Model model){
		model.addAttribute("message", "Bạn không có quyền truy xuất");
		return "security/login";
	}
	
	@GetMapping("logoff/success")
	public String logoffSuccess(Model model){
		model.addAttribute("message", "Đăng xuất thành công");
		return "security/login";
	}
}
