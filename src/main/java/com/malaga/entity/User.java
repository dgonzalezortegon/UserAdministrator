package com.malaga.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User entity
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user", schema = "public")
public class User implements Serializable {

	private static final long serialVersionUID = 5393104620820065367L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "password")
	private String password;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "admin", nullable = false)
	private boolean admin;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Account> accountsUser;

}
