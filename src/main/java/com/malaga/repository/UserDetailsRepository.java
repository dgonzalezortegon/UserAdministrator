package com.malaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malaga.entity.User;

/**
 * User detail repository
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

public interface UserDetailsRepository extends JpaRepository<User, Long> {

	/**
	 * Find user by username
	 * 
	 * @param name The username
	 * @return Optional<User>
	 */
	public User findByUsername(String name);

}
