package edu.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.shop.entity.Product;
import edu.shop.repository.ProductRepository;

public interface ProductService {

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	<S extends Product> List<S> findAll(Example<S> example);

	Product getById(Integer id);

	void deleteAll();

	void delete(Product entity);

	void deleteById(Integer id);

	long count();

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);


}
