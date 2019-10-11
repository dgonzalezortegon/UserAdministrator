package com.malaga.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO implements Serializable{
	
	
	private static final long serialVersionUID = 2452588116900861202L;

	private UserDTO user; 
	
	private List<ProfileDTO> profiles; 
	

	

}
