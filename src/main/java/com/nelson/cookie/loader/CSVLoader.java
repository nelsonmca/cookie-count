package com.nelson.cookie.loader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.nelson.cookie.domain.CookieDetail;
import com.nelson.cookie.exception.InValidParameterException;

@FunctionalInterface
public interface CSVLoader {
	
	public Map<String,List<CookieDetail>> loadCSV(String fileName) throws IOException, ParseException, InValidParameterException;
	

}
