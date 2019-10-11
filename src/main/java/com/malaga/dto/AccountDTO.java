package com.malaga.dto;

import java.io.Serializable;

import com.malaga.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {

	
	private static final long serialVersionUID = -3787731424257668071L;

	private Long id;

	private User user;

	private String iban;

}
