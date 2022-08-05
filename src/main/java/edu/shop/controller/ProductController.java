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
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid){
		//nếu có category id sẽ hiển thị product theo cid
		
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid);
			model.addAttribute("items", list); 
		}
		
		//hiển thị tất cả product
		else {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		}
		return "product/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id){
		Product item = productService.getById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}
}
