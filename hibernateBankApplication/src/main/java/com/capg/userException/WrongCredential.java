package com.capg.userException;

public class WrongCredential extends Exception {

	public WrongCredential()
	{
		System.err.println("Entered AccountNo and password does not exist");
	}
}
