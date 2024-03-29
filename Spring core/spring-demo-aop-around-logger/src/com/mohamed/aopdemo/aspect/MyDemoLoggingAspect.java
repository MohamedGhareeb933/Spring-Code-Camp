package com.mohamed.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.mohamed.aopdemo.service.FortuneService.getFortuneService(..))")
	public Object aroundGetFortune(ProceedingJoinPoint ProJoinPoint) throws Throwable {
		
		// before calling the advise method 
		String Method = ProJoinPoint.getSignature().toString();
		
		logger.info("@Around Finally on Method: " + Method);
		
		long timeBegin = System.currentTimeMillis();
		
		// call advise method 
		Object result = ProJoinPoint.proceed();
		
		long timeEnd = System.currentTimeMillis();
		
		long duration = timeEnd - timeBegin;
		
		
		logger.info("duration: " + duration / 1000.0 + " seconds");
		
		// return adivse method 
		return result;
	}
	
	@After("execution(* com.mohamed.aopdemo.dao.AccountDAO.findAccount(..))")
	public void AfterFinallyAddAccount(JoinPoint joinPoint) {
		
		String Method = joinPoint.getSignature().toString();
		
		logger.info("@After Finally on Method " + Method);
		
	}

	
	@AfterThrowing(
			pointcut="execution(* com.mohamed.aopdemo.dao.AccountDAO.findAccount(..)) )",
			throwing ="error")
	public void AfterThrowingAddAccount(JoinPoint joinPoint, Throwable error) {
		logger.info("@AfterThrowing " + error);
	}
	
	// add a new Advise for @AfterReturning on findAccount Methoder
	@AfterReturning(
			pointcut="execution(* com.mohamed.aopdemo.dao.AccountDAO.findAccount(..) )",
			returning="result")
	public void AfterReturningAddAccount(JoinPoint joinPoint, List<Account> result) {
		
		// Printout Which method we Are Advising on
		String Method = joinPoint.getSignature().toString();
		
		logger.info("@AfterReturning on Method" + Method);
		
		logger.info("@AfterReturning findAccount Result: " + result);
		
		getAfterReturningAddAccountToUpperCase(result);
	}
	

	private void getAfterReturningAddAccountToUpperCase(List<Account> result) {
		
		for(Account i: result) {
			i.setName(i.getName().toUpperCase());
		}
	}


	@Before("com.mohamed.aopdemo.aspect.AOPExpression.ApplyNoGetterSetter()")
	public void addAccount(JoinPoint joinPoint) {
		
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		
		logger.info("@Before Add Account @Method Signature "  + methodSig);
		
		//display Method Arguments
		Object args[] = joinPoint.getArgs();
		
		// iterate through arguments
		for	(Object arg : args) {
			logger.info("@Method Arguments: " + arg);
			
			// if the passed Argument is instance of/Type of Account 
			if	(arg instanceof Account) {
				Account account = (Account) arg;
				logger.info("@Account Name: " + account.getName());
				logger.info("@Account Level: " + account.getLevel());
			}
		}
	}
	
	
}
