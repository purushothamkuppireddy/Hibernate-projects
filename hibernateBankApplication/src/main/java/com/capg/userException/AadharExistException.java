package com.capg.userException;

public class AadharExistException extends Exception {
public AadharExistException()
{
	System.err.println("Entered Aadhar number already existed");
}
}
