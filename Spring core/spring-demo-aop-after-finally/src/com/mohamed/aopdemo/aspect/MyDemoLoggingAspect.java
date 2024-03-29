package com.mohamed.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mohamed.aopdemo.Account;

@Order(1)
@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// after calling findaccount method - comes after (after returning)
	@After("execution(* com.mohamed.aopdemo.dao.AccountDAO.findAccount(..))")
	public void AfterFinallyAddAccount(JoinPoint joinPoint) {
		
		String Method = joinPoint.getSignature().toString();
		
		System.out.println("@After Finally on Method " + Method);
		
	}


	// after throwing an exception(pointcut) // should handled in seprate class AOP Expression if the expresion is long 
	// throwing - error returning 
	@AfterThrowing(
			pointcut="execution(* com.mohamed.aopdemo.dao.AccountDAO.findAccount(..)) )",
			throwing ="error")
	public void AfterThrowingAddAccount(JoinPoint joinPoint, Throwable error) {
		System.out.println("@AfterThrowing " + error);
	}
	
	// add a new Advise for @AfterReturning on findAccount Methoder
	@AfterReturning(
			pointcut="execution(* com.mohamed.aopdemo.dao.AccountDAO.findAccount(..) )",
			returning="result")
	public void AfterReturningAddAccount(JoinPoint joinPoint, List<Account> result) {
		
		// Printout Which method we Are Advising on
		String Method = joinPoint.getSignature().toString();
		
		System.out.println("@AfterReturning on Method" + Method);
		
		System.out.println("@AfterReturning findAccount Result: " + result);
		
		getAfterReturningAddAccountToUpperCase(result);
	}
	

	private void getAfterReturningAddAccountToUpperCase(List<Account> result) {
		
		for(Account i: result) {
			i.setName(i.getName().toUpperCase());
		}
	}


	// ApplyNoGetterSetter which is collection and statement of aspect expressions
	@Before("com.mohamed.aopdemo.aspect.AOPExpression.ApplyNoGetterSetter()")
	public void addAccount(JoinPoint joinPoint) {
		
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		
		System.out.println("@Before Add Account @Method Signature "  + methodSig);
		
		//display Method Arguments
		Object args[] = joinPoint.getArgs();
		
		// iterate through arguments
		for	(Object arg : args) {
			System.out.println("@Method Arguments: " + arg);
			
			// if the passed Argument is instance of/Type of Account 
			if	(arg instanceof Account) {
				Account account = (Account) arg;
				System.out.println("@Account Name: " + account.getName());
				System.out.println("@Account Level: " + account.getLevel());
			}
		}
	}
	
	
}
