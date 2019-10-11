package com.malaga.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account entity
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
@Table(name = "account", schema = "public")
public class Account implements Serializable {

	private static final long serialVersionUID = -2058093563784989338L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName="id", nullable = false , foreignKey = @ForeignKey(name = "FK_ACCOUNT_TO_USER_ID"))
	private User user;

	@Column(name = "iban", nullable = false, unique = true)
	private String iban;

}
