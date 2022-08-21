package edu.shop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.shop.entity.Account;
import edu.shop.entity.Product;
import edu.shop.service.AccountService;
import edu.shop.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/registration")
public class RegistrationRestController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping
	public Account create(@RequestBody Account account) {
		return accountService.save(account);
	}

}
