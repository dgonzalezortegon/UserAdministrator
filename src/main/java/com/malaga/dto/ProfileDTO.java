package com.malaga.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO implements Serializable {
	

	private static final long serialVersionUID = 5055682428875786616L;
	private String authority;

}
