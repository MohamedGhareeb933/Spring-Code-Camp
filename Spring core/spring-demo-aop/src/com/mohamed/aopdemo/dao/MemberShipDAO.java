package com.mohamed.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MemberShipDAO {

	public String addMembership() {
		
		System.out.println(getClass() + " add Account to MembershipDAO");
		
		return " ";
	}
}
