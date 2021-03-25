package com.nelson.cookie.validator;

import com.nelson.cookie.exception.InValidParameterException;

@FunctionalInterface
public interface Validate {
	
	public boolean validate(String param) throws InValidParameterException;
	
}
