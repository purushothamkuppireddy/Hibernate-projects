package com.capg.registerationlogindao;

import com.capg.model.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class BankRegistrationDaoImpl implements BankRegistrationDao {
	Account account = new Account();
	Scanner sc = new Scanner(System.in);

//registration
	public long registration(Account account) {

		long accNo = 0;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(account);
		transaction.commit();


		System.out.println("Inserted");

		return account.getAccountNo();
	}

//Login	
	public Account Login(long accountNo, int password) {
		// int count = 0;

		Configuration configuration = new Configuration().configure().addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Account account1 = session.get(Account.class, accountNo);
		if (account1 != null && account1.getPassword() == password)
			return account1;
		else
			return null;
	}

//	session.beginTransaction();
//    
//    Criteria criteria = session.createCriteria(Account.class);
//    criteria.add(Restrictions.eq("accountNo", accountNo));
//     
//    Account account = (Account) criteria.uniqueResult();
//    
//     System.out.println(account.getBalance());
//    if (account!=null) {
//        System.out.println("Employee found:");
//        return account;
//    }
//    else {
//    	return null;
//    	
//    }
//	}
	// session.getTransaction().commit();

//		try {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","capg","oracle123");
//		PreparedStatement ps=con.prepareStatement("select * from customer_details where account_no=? and password=?");
//		ps.setLong(1,accountNo);
//		ps.setLong(2,password);
//		ResultSet rs = ps.executeQuery();	
//		while(rs.next()) {
//			
//			account.setBalance(rs.getInt(10));
//			//b=true;
//			count++;
//		}
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	if(count==0)
//		return null;
//	else
//		return account;
//	}

//validate method	
	public boolean validate(long aadharNo) {
		boolean b = false;
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Account.class);
	    criteria.add(Restrictions.eq("aadharNo", aadharNo));
	     
	    Account account = (Account) criteria.uniqueResult();
		if (account != null)
			return true;
		else
			return b;
	}

}
