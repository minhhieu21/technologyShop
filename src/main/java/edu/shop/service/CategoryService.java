package edu.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.shop.entity.Category;

public interface CategoryService {

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	<S extends Category> List<S> findAll(Example<S> example);

	Category getById(String id);

	void deleteAll();

	void delete(Category entity);
	
	void deleteById(String id);

	<S extends Category> boolean exists(Example<S> example);

	long count();

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(String id);

	Optional<Category> findById(String id);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

}
