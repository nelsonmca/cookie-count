package com.nelson.cookie.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelson.cookie.domain.CommandParam;
import com.nelson.cookie.domain.CookieDetail;
import com.nelson.cookie.exception.InValidParameterException;
import com.nelson.cookie.loader.CSVLoader;
import com.nelson.cookie.validator.Validate;
import com.nelson.cookie.validator.ValidateDateParameter;
import com.nelson.cookie.validator.ValidateFileParameter;


public class CommandLineServiceImpl implements CommandLineService {
	
	private Map<String,List<CookieDetail>> dateCookieMap;
	
	@Autowired
	private CSVLoader csvloader;
	
	
	@Override
	public List<String> getMaxCookieByDate(String date) throws  InValidParameterException, IOException, ParseException{
		 Map<String, AtomicInteger> cookieMap = new HashMap<>();
		 List<CookieDetail> cookieDetails = dateCookieMap.get(date);
		 if(cookieDetails !=null && cookieDetails.size()>0) {
			//get Map of cookie string and count of each occurrence
			 cookieDetails.stream().forEach(cookiedetail -> cookieMap.computeIfAbsent(cookiedetail.getCookieValue(), key -> new AtomicInteger())
	                 .incrementAndGet());
			 // find max value of the count
			 int maxValueInMap = cookieMap.values().stream().mapToInt(AtomicInteger::intValue).max().getAsInt();
			 
			 //return all cookie matching the max count
			 return cookieMap.entrySet()
             .stream()
             .filter(entry -> maxValueInMap == entry.getValue().intValue())
             .map(Entry::getKey)
             .collect(Collectors.toCollection(ArrayList::new));
		 }
		return null;
	}

	@Override
	public CommandParam parseParam(String[] args) throws InValidParameterException, IOException, ParseException {
		List<String>  paramsList= Arrays.asList(args);
		Validate validate;
		CommandParam commandParam = new CommandParam();
		
		try {
			// check if file parameter exists
			if(paramsList.contains("-f")) {
				validate = new ValidateFileParameter();
				String fileInput = paramsList.get(paramsList.indexOf("-f")+1);
				validate.validate(fileInput);
				commandParam.setFileName(fileInput);
			}else {
				throw new InValidParameterException("Missing file (-f) parameter");
			}
			// check if date parameter exists
			if(paramsList.contains("-d")) {
				validate = new ValidateDateParameter();
				String dateInput = paramsList.get(paramsList.indexOf("-d")+1);
				validate.validate(dateInput);
				commandParam.setDate(dateInput);
			}else {
				throw new InValidParameterException("Missing date (-d) parameter");
			}
			dateCookieMap = csvloader.loadCSV(commandParam.getFileName());
		} catch (InValidParameterException e) {
			throw e;
		}
		return commandParam;
	}


	
	
	

}
