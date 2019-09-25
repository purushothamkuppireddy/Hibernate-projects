package com.capg.hibernetDemo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
       
    	Emp emp=new Emp();
    	emp.setEmpId(1016);
    	emp.setName("kpre");
    	emp.setSalary(45000);
    	emp.setJoinDate(new Date());
    	
    	
    	Configuration configuration=new Configuration().configure().addAnnotatedClass(Emp.class);
    	SessionFactory sessionFactory=configuration.buildSessionFactory();
    	Session session= sessionFactory.openSession();
    	Transaction transaction= session.beginTransaction();
    	session.persist(emp);
    	transaction.commit();
    }
}
