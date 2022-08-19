package edu.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.shop.service.OrderService;

@Controller
public class HomeController {
	
	
	@RequestMapping({"/","/home/index"})
	public String home(){
		return "redirect:/product/list";
	}
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin(){
		return "redirect:/assets/admin/index.html";
	}
	
}
