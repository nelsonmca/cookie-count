package com.nelson.cookie.validator;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.nelson.cookie.exception.InValidParameterException;

public class ValidateDateParameter implements Validate{
	
	@Override
	public boolean validate(String dateStr) throws InValidParameterException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new InvalidParameterException(dateStr + " Date format in Invalid. Use <<yyyy-MM-dd>>");
        }
		return true;
	}

}
