package edu.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.shop.entity.Account;
import edu.shop.entity.Authority;
import edu.shop.entity.Category;
import edu.shop.entity.Product;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
	
}
