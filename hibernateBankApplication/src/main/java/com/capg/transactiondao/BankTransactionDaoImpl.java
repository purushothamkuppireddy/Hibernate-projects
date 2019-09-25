package com.capg.transactiondao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.capg.model.Account;
import com.capg.model.TransactionDetails;

public class BankTransactionDaoImpl implements BankTransactionDao {
	Scanner sc = new Scanner(System.in);

//withdraw
	public int withdraw(long accountNo, int amount, int amt) {

		Configuration configuration = new Configuration().configure().addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
//		Account account1 = session.get(Account.class, accountNo);
//		if(amt<amount) {
//		account1.setBalance(amount - amt);
//		Transaction transaction = session.beginTransaction();
//		session.update(account1);
//		transaction.commit();
//		System.out.println(account1.getBalance());
//		return account1.getBalance();
//		}
//		else return 0;
		if (amt < amount) {
			session.beginTransaction();
			Query query = session.createQuery("update Account set balance = :balance" + " where accountNo = :accountNo");
			query.setParameter("balance", (amount - amt));
			query.setParameter("accountNo", accountNo);
			int i = query.executeUpdate();
			if (i == 1)
				return i;
			else
				return 0;

		} else
			return 0;
	}

//deposit
	public int deposit(long accountNo, int amount, int amt) {

		Configuration configuration = new Configuration().configure().addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
//		Account account1 = session.get(Account.class, accountNo);
//		account1.setBalance(amount + amt);
//		Transaction transaction = session.beginTransaction();
//		session.update(account1);
//		transaction.commit();
//
//		return account1.getBalance();
		session.beginTransaction();
		Query query = session.createQuery("update Account set balance = :balance" + " where accountNo = :accountNo");
		query.setParameter("balance", (amount + amt));
		query.setParameter("accountNo", accountNo);
		int i = query.executeUpdate();
		if (i == 1)
			return i;
		else
			return 0;

	}

//fundTransfer
	public int fundTransfer(long fromAccountNo, int amount, long toAccountNo, int necessaryAmt) {
		int balance=0;
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
//		Account account1 = session.get(Account.class, fromAccountNo);
//		if (necessaryAmt < amount) {
//			account1.setBalance(amount - necessaryAmt);
//			// Transaction transaction = session.beginTransaction();
//			session.update(account1);
//			// transaction.commit();
//		} else
//			System.out.println("insufficient bal");

//		Account account2 = session.get(Account.class, toAccountNo);
//		account2.setBalance(account2.getBalance() + necessaryAmt);
//		Transaction transaction = session.beginTransaction();
//		session.update(account2);
//		transaction.commit();

		if (necessaryAmt < amount) {
			session.beginTransaction();
			Query query = session
					.createQuery("update Account set balance = :balance" + " where accountNo = :accountNo");
			query.setParameter("balance", (amount - necessaryAmt));
			query.setParameter("accountNo", fromAccountNo);
			query.executeUpdate();

//			Query query3 = session.createQuery(" select balance from Account where accountNo=:accountNo");
//			query3.setParameter("accountNo", toAccountNo);
//			List list = query3.list();
//			Iterator iterator = list.iterator(); 
//			while(iterator.hasNext()) {
//				
//			System.out.println(iterator.next());
//			 balance= (Integer) (iterator.next());
//			 System.out.println("bal "+(Integer) (iterator.next()));
//			}

			Account account2 = session.get(Account.class, toAccountNo);
			Query query2 = session.createQuery("update Account set balance = :balance" + " where accountNo = :accountNo");
			query2.setParameter("balance", (account2.getBalance() + necessaryAmt));
			query2.setParameter("accountNo", toAccountNo);
			int i = query2.executeUpdate();

			TransactionDetails transactionDetails = new TransactionDetails();
			transactionDetails.setFromAccountNo(fromAccountNo);
			transactionDetails.setToAccountNo(toAccountNo);
			transactionDetails.setAmount(necessaryAmt);
			Configuration configuration2 = new Configuration().configure().addAnnotatedClass(TransactionDetails.class);
			SessionFactory sessionFactory2 = configuration2.buildSessionFactory();
			Session session2 = sessionFactory2.openSession();
			Transaction transaction2 = session2.beginTransaction();
			session2.save(transactionDetails);
			transaction2.commit();

			if (i == 1)
				return i;
			else
				return 0;

		} else
			return 0;

	}

}
