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

		UserDTO newUser = null;

		if (user == null) {
			throw new AdministratorException("User no well formed");
		}

		try {
			newUser = mapperUser.toDTO(userRepository.save(mapperUser.toModel(user)));
		} catch (Exception e) {
			throw new AdministratorException("Error created: " + e.getMessage());
		}

		return newUser;
	}

	@Override
	public UserDTO update(UserDTO user) throws AdministratorException {

		UserDTO userUpdated = null;

		User u = userRepository.findByUsername(user.getUsername());

		if (u == null) {
			throw new AdministratorException("Error updated : not exist " + user.getUsername());
		}

		try {
			if (user.getEmail() != null) {
				u.setEmail(user.getEmail());
			}
			if (user.getLastname() != null) {
				u.setLastname(user.getLastname());
			}
			if (user.getPassword() != null) {
				u.setPassword(user.getPassword());
			}
			if (user.getEnabled()!=null) {
				u.setEnabled(user.getEnabled());
			}
			if (user.getAdmin()!=null) {
				u.setAdmin(user.getAdmin());
			}
			userUpdated = mapperUser.toDTO(userRepository.save(u));

		} catch (Exception e) {
			throw new AdministratorException("Error updated: " + e.getMessage());
		}

		return userUpdated;
	}

	@Override
	public UserDTO findByUsername(String idUser) throws AdministratorException {
		return mapperUser.toDTO(userRepository.findByUsername(idUser));
	}

	@Override
	public void delete(UserDTO user) throws AdministratorException {

		try {
			userRepository.delete(userRepository.findOne(user.getId()));
		} catch (Exception e) {
			throw new AdministratorException("Error deleted: " + e.getMessage());
		}

	}

	@Override
	public UserDTO existUsername(String idUser) throws AdministratorException {
		return mapperUser.toDTO(userRepository.findByUsername(idUser));
	}

}
