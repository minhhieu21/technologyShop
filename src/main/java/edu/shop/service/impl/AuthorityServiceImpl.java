package edu.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.shop.entity.Account;
import edu.shop.entity.Authority;
import edu.shop.repository.AccountRepository;
import edu.shop.repository.AuthorityRepository;
import edu.shop.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authorityRepository.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authorityRepository.deleteById(id);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountRepository.getAdministrators();
		return authorityRepository.authoritiesOf(accounts);
	}

}
