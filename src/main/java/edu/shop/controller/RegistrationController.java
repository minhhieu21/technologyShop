package edu.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("registration")
public class RegistrationController {
		
	@GetMapping("form")
	public String registerForm(Model model){
		model.addAttribute("message", "Vui lòng đăng nhập");
		return "security/registration";
	}
}