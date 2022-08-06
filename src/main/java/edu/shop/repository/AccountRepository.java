package edu.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.shop.entity.Account;
import edu.shop.entity.Category;
import edu.shop.entity.Product;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	
}
