package edu.shop.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.shop.entity.Order;

public interface OrderService {

	Order getById(Long id);

	void deleteAll();

	Order getOne(Long id);

	void delete(Order entity);

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	List<Order> findAll();

	<S extends Order> S save(S entity);
	
	Order create(JsonNode orderData);
	 
	List<Order> findByUsername(String username);

}
