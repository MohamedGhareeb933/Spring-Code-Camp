package com.mohamed.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mohamed.aopdemo.dao.AccountDAO;
import com.mohamed.aopdemo.dao.MemberShipDAO;


public class MainAppDemo {
	public static void main( String[] args) {
		
		AnnotationConfigApplicationContext Context = 
				new AnnotationConfigApplicationContext(demoConfig.class);
		
		AccountDAO accountDAO = Context.getBean("accountDAO" , AccountDAO.class);
		
		Account account = Context.getBean("account", Account.class);
		
		accountDAO.addAccount(account, "Mohamed");
		

		Context.close();
	}

}
