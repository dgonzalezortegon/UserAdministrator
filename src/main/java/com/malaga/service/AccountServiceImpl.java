package com.malaga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malaga.dto.AccountDTO;
import com.malaga.entity.Account;
import com.malaga.entity.User;
import com.malaga.exceptions.AdministratorException;
import com.malaga.mapper.AccountMapper;
import com.malaga.repository.AccountRepository;
import com.malaga.repository.UserDetailsRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	UserDetailsRepository userRepo;

	@Autowired
	AccountMapper maper;

	@Override
	public List<AccountDTO> findAccounts(String user) throws AdministratorException {
		return maper.toDTO(accountRepo.findAllByUserUsername(user));
	}

	@Override
	public AccountDTO createAccount(String username, String iban) throws AdministratorException {

		User user = userRepo.findByUsername(username);

		Account account = new Account();
		account.setUser(user);
		account.setIban(iban);
		return maper.toDTO(accountRepo.save(account));
	}

}
