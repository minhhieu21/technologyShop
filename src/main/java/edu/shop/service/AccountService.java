package edu.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.shop.entity.Account;
import edu.shop.entity.Category;

public interface AccountService {

	<S extends Account> List<S> findAll(Example<S> example, Sort sort);

	<S extends Account> List<S> findAll(Example<S> example);

	Account getById(String id);

	void deleteAll();

	void delete(Account entity);

	void deleteById(String id);

	<S extends Account> boolean exists(Example<S> example);

	long count();

	<S extends Account> long count(Example<S> example);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(String id);

	void flush();

	Optional<Account> findById(String id);

	Page<Account> findAll(Pageable pageable);

	List<Account> findAll();

	<S extends Account> S save(S entity);

	List<Account> getAdministrators();

}
