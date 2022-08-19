package edu.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.shop.entity.Role;

public interface RoleService {

	List<Role> findAll();

}
