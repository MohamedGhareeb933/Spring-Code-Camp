package com.mohamed.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mohamed.aopdemo.dao.AccountDAO;
import com.mohamed.aopdemo.dao.MemberShipDAO;


public class AfterReturningAppDemo {
	public static void main( String[] args) {
		
		AnnotationConfigApplicationContext Context = 
				new AnnotationConfigApplicationContext(demoConfig.class);
		
		AccountDAO accountDAO = Context.getBean("accountDAO" , AccountDAO.class);
		
		
		System.out.println("List of Accounts: " + accountDAO.findAccount(false));
		
		Context.close();
	}

}
