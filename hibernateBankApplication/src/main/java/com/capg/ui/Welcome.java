package com.capg.ui;

import java.util.Scanner;

import com.capg.model.Account;
import com.capg.registerationlogindao.BankRegistrationDaoImpl;
import com.capg.registrationLoginService.*;
import com.capg.transactionService.*;
import com.capg.transactiondao.BankTransactionDaoImpl;
import com.capg.userException.AadharExistException;
import com.capg.userException.WrongCredential;

public class Welcome {
	static BankRegistrationServiceImpl serv = new BankRegistrationServiceImpl();
	static BankTransactionServiceImpl transserv = new BankTransactionServiceImpl();
	static Account account = new Account();

	public static void main(String[] args) {
		int j = 1;
		while (j == 1) {
			System.out.println("enter the choice 1:Registration , 2:Login  3:Exit");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			switch (ch) {
			case 1:// Registration
				System.out.println("enter Aadhar no to register");
				long aadharNo = sc.nextLong();
				boolean bl = serv.validate(aadharNo);
				if (bl == false) {
					boolean b2 = transserv.validateAadhar(aadharNo);
					if (b2 == true) {
						account.setAadharNo(aadharNo);
						System.out.println("enter firstname");
						account.setFirstName(sc.next());
						System.out.println("enter lastname");
						account.setLastName(sc.next());
						System.out.println("enter emailId");
						account.setEmailId(sc.next());
						System.out.println("enter password for your account");
						account.setPassword(sc.nextInt());
						System.out.println("enter pancard number");
						account.setPancardNo(sc.nextInt());
						System.out.println("enter Address");
						account.setAddress(sc.next());
						System.out.println("enter mobile No");
						account.setMobileNo(sc.nextLong());
						long accNo = serv.registration(account);
						System.out.println("Account registerd successfully.your accno is: " + accNo);
					} else
						System.err.println("Aadhar number is not 12 digits");
				} else {
					try {
						throw new AadharExistException();
					} catch (AadharExistException e) {

					}
				}
				break;

			case 2: // login
				System.out.println("enter the Account no and password");
				long accountNo = sc.nextLong();
				int pwd = sc.nextInt();
				do {
					account = serv.Login(accountNo, pwd);

					if (account != null) {

						// System.out.println(account.getAccountNo()+" "+account.getBalance());
						System.out.println("Enter 1:withdraw  2: Deposit  3:Fund transfer  4:Show balance 5:exit");
						int ch2 = sc.nextInt();
						switch (ch2) {
						case 1: // withdraw
							System.out.println("enter the amount to withdraw");
							int amt = sc.nextInt();
							int m = transserv.withdraw(accountNo, account.getBalance(), amt);
							if (m==1)
								System.out.println("withdrawl successfully");
							else
								System.err.println("error in withdrawl");
							break;

						case 2: // deposit
							System.out.println("enter the amount to deposit");
							int amt2 = sc.nextInt();
							int n = transserv.deposit(accountNo, account.getBalance(), amt2);
							if (n==1)
								System.out.println("deposited successfully");
							else
								System.out.println("error in depositing");
							break;

						case 3: // fund transfer
							System.out.println("enter the amount to transfer");
							int amt3 = sc.nextInt();
							System.out.println("enter the accno to which transfer to be happen");
							long accountNo2 = sc.nextLong();
							int k= transserv.fundTransfer(accountNo, account.getBalance(), accountNo2, amt3);
							if (k == 1)
								System.out.println("successfully inserted into transaction table");
							else
								System.out.println("Error in inserting into transaction table");

							break;

						case 4: // show balance
							System.out.println("current account balance is: " + account.getBalance());
							break;

						case 5:
							System.out.println("Thank You..");
							System.exit(0);
						}

					}

					else {
						try {
							throw new WrongCredential();
						} catch (Exception e) {

						}
					}
				} while (true);
			
				
	    	case 3:System.out.println("Thank You..");
	    			System.exit(0);

			}

		}
	}

}
