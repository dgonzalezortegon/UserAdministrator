package com.malaga.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{
	
	
	private static final long serialVersionUID = -1116973773523585385L;

	private Long id;

	private String username;

	private String lastname;
	
	private String password;

	private String email;

	private boolean enabled;
	
	private boolean admin;

}
