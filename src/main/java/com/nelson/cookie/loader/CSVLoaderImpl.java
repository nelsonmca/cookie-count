package com.nelson.cookie.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nelson.cookie.domain.CookieDetail;
import com.nelson.cookie.exception.InValidParameterException;

@Component
public class CSVLoaderImpl implements CSVLoader{
	static final String SEPARATOR = ",";

	
	@Override
	public Map<String, List<CookieDetail>> loadCSV(String fileName) throws IOException, ParseException, InValidParameterException {
			Map<String,List<CookieDetail>> cookieDetailMap = new HashMap<>();
			boolean isFirstLine = true;
		    try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
		        String line;
		        while ((line = in.readLine()) != null) {
		            if (isFirstLine) {
		                isFirstLine = false;
		                continue;
		            }
		            String fields[] = line.split(SEPARATOR);
		            if (fields.length != 2){
		            	throw new InValidParameterException("Error: Invalid File contents");
		            }
		            
		            Date date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss+00:00").
		            		parse(line.split(",", 2)[1]);  
		            
		            CookieDetail cookieDetail = new CookieDetail(line.split(",", 2)[0], 
		            		date);
		            
		            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);  
		            
		            cookieDetailMap.computeIfPresent(dateString, (key,val)->{
		            	val.add(cookieDetail);
		            	return val;
		            });
		            
		            cookieDetailMap.computeIfAbsent(dateString, key->{
		            	List<CookieDetail> cookieDetailList = new ArrayList<>();
		            	cookieDetailList.add(cookieDetail);
		            	return cookieDetailList;
		            });
		            
		           
		        }
		    }
			return cookieDetailMap;
	}
	
}
