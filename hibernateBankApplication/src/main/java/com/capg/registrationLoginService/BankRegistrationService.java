package com.capg.registrationLoginService;

import com.capg.model.Account;

public interface BankRegistrationService {
	public Account Login(long accountNo,int password);
	public long registration(Account account);
	public boolean validate(long aadharNo);
}
