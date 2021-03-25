package com.nelson.cookie.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.nelson.cookie.domain.CommandParam;
import com.nelson.cookie.exception.InValidParameterException;

public interface CommandLineService {
	List<String> getMaxCookieByDate(String date) throws InValidParameterException,FileNotFoundException, IOException, ParseException;
	public CommandParam parseParam(String[] args) throws InValidParameterException, IOException, ParseException; 
}
