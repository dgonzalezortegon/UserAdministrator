package com.malaga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malaga.entity.Account;
import com.malaga.entity.User;

/**
 * Account repository
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	
	public List<Account> findAllByUser(User id);
	
	
	public List<Account> findAllByUserId(Long id);

}
