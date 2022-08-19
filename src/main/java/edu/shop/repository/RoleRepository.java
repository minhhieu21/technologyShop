package edu.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.shop.entity.Account;
import edu.shop.entity.Category;
import edu.shop.entity.Product;
import edu.shop.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
}
