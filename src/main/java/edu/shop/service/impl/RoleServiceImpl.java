package edu.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.shop.entity.Role;
import edu.shop.repository.RoleRepository;
import edu.shop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
}
