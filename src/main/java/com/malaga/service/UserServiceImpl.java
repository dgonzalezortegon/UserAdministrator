package com.malaga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malaga.dto.UserDTO;
import com.malaga.exceptions.AdministratorException;
import com.malaga.mapper.UserMapper;
import com.malaga.repository.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDetailsRepository userRepository;

	@Autowired
	UserMapper mapperUser;

	@Override
	public List<UserDTO> findAll() throws AdministratorException {
		log.debug("findAll");
		return mapperUser.toDTO(userRepository.findAll());
	}

	@Override
	public UserDTO create(UserDTO user) throws AdministratorException {
		return mapperUser.toDTO(userRepository.save(mapperUser.toModel(user)));
	}

	@Override
	public UserDTO update(UserDTO user) throws AdministratorException {
		return mapperUser.toDTO(userRepository.save(mapperUser.toModel(user)));
	}

	@Override
	public UserDTO findByUsername(String idUser) throws AdministratorException {
		return mapperUser.toDTO(userRepository.findByUsername(idUser));
	}

	@Override
	public void delete(UserDTO user) throws AdministratorException {
		userRepository.delete(mapperUser.toModel(user));

	}

}
