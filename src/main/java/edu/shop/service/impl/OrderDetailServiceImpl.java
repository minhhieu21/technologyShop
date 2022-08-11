package edu.shop.service.impl;

import java.util.List;
import java.util.Optional;

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
import edu.shop.service.OrderDetailService;
import edu.shop.service.OrderService;
import edu.shop.service.ProductService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	@Override
	public void deleteById(Long id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}
	
	

}
