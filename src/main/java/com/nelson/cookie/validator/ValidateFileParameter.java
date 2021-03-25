package com.nelson.cookie.validator;

import java.security.InvalidParameterException;

import com.nelson.cookie.exception.InValidParameterException;

public class ValidateFileParameter implements Validate{

	@Override
	public boolean validate(String fileName) throws InValidParameterException{
		if(fileName != null && fileName.endsWith(".csv")) {
			return true;
		}
		throw new InvalidParameterException(fileName+ " FileName parameter is invalid. Only csv file supported");
	}
	

}
