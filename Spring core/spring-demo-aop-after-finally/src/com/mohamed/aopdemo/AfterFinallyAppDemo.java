package com.mohamed.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mohamed.aopdemo.dao.AccountDAO;


public class AfterFinallyAppDemo {
	public static void main( String[] args) {
		
		AnnotationConfigApplicationContext Context = 
				new AnnotationConfigApplicationContext(demoConfig.class);
		
		
		AccountDAO accountDAO = Context.getBean("accountDAO" , AccountDAO.class);
		
		List<Account> AccountList = null;

		try {
			AccountList = accountDAO.findAccount(false);
			
			System.out.println("List of Accounts: " + AccountList);
			
		}catch(Exception exc) {
			System.out.println("Exception Handling " + exc);
			
		}finally {	
			Context.close();
			
		}
		
	}

}
