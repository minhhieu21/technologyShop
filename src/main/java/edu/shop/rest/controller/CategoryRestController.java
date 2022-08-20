package edu.shop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.shop.entity.Category;
import edu.shop.entity.Product;
import edu.shop.service.CategoryService;
import edu.shop.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping()
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("{id}")
	public Category getOne(@PathVariable("id") String id) {
		return categoryService.getById(id);
	}
	
	@PostMapping
	public Category create(@RequestBody Category category) {
		return categoryService.save(category);
	}
	
//	@PutMapping({"id"})
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	public Category update(@PathVariable("id") String id, @RequestBody Category category) {
		return categoryService.save(category);
	}
//	@DeleteMapping({"id"})
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		categoryService.deleteById(id);
	}
}
