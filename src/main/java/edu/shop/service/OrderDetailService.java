package edu.shop.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import edu.shop.entity.Order;
import edu.shop.entity.OrderDetail;

public interface OrderDetailService {

	void deleteAll();

	void delete(OrderDetail entity);

	void deleteById(Long id);

	long count();

	Optional<OrderDetail> findById(Long id);

	List<OrderDetail> findAll();

	<S extends OrderDetail> S save(S entity);

	<S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

	

}
