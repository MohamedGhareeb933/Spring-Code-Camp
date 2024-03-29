package com.mohamed.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mohamed.aopdemo.dao.AccountDAO;
import com.mohamed.aopdemo.dao.MemberShipDAO;


public class MainAppDemo {
	public static void main( String[] args) {
		
		AnnotationConfigApplicationContext Context = 
				new AnnotationConfigApplicationContext(demoConfig.class);
		
		AccountDAO accountDAO = Context.getBean("accountDAO" , AccountDAO.class);
		MemberShipDAO membership = Context.getBean("memberShipDAO" , MemberShipDAO.class);
		
		Account account = Context.getBean("account", Account.class);
		
		// AspectJ method will be called  before execution of addAccount method
		accountDAO.addAccount(account, "Mohamed");
		
		membership.addMembership();

		Context.close();
	}

}
