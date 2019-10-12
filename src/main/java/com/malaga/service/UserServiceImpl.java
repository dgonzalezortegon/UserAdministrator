package com.malaga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malaga.dto.UserDTO;
import com.malaga.entity.User;
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
		if (user == null) {
			throw new AdministratorException("User no well formed");
		}
		return mapperUser.toDTO(userRepository.save(mapperUser.toModel(user)));
	}

	@Override
	public UserDTO update(UserDTO user) throws AdministratorException {
		User u = userRepository.findByUsername(user.getUsername());

		UserDTO userUpdated = null;

		if (u != null) {
			u.setEmail(user.getEmail());
			u.setLastname(user.getLastname());
			u.setPassword(user.getPassword());

			userUpdated = mapperUser.toDTO(userRepository.save(u));
		}

		return userUpdated;
	}

	@Override
	public UserDTO findByUsername(String idUser) throws AdministratorException {
		return mapperUser.toDTO(userRepository.findByUsername(idUser));
	}

	@Override
	public void delete(UserDTO user) throws AdministratorException {

		userRepository.delete(userRepository.findOne(user.getId()));

	}

	@Override
	public UserDTO existUsername(String idUser) throws AdministratorException {
		return mapperUser.toDTO(userRepository.findByUsername(idUser));
	}

}
