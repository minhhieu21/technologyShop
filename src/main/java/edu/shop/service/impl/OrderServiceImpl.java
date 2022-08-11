package edu.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.shop.entity.Account;
import edu.shop.entity.Category;
import edu.shop.entity.Order;
import edu.shop.entity.OrderDetail;
import edu.shop.repository.AccountRepository;
import edu.shop.repository.CategoryRepository;
import edu.shop.repository.OrderDetailRepository;
import edu.shop.repository.OrderRepository;
import edu.shop.repository.ProductRepository;
import edu.shop.service.AccountService;
import edu.shop.service.CategoryService;
import edu.shop.service.OrderService;
import edu.shop.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	 

	@Override
	public <S extends Order> S save(S entity) {
		return orderRepository.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return orderRepository.existsById(id);
	}

	@Override
	public long count() {
		return orderRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public void delete(Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public Order getOne(Long id) {
		return orderRepository.getOne(id);
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
	}

	@Override
	public Order getById(Long id) {
		return orderRepository.getById(id);
	}

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mappper = new ObjectMapper();
		
		
		Order order = mappper.convertValue(orderData, Order.class);
		orderRepository.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mappper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		
		
		orderDetailRepository.saveAll(details);
		
		
		return order;
		
		
	}

	public List<Order> findByUsername(String username) {
		return orderRepository.findByUsername(username);
	}


	
	
	

}
