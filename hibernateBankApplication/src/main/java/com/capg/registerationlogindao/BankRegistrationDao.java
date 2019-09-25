package com.capg.registerationlogindao;

import com.capg.model.Account;

public interface BankRegistrationDao {
	public Account Login(long accountNo,int password);
	public long registration(Account account);
	
}
