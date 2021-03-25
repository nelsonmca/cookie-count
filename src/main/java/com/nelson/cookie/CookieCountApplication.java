package com.nelson.cookie;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nelson.cookie.domain.CommandParam;
import com.nelson.cookie.exception.InValidParameterException;
import com.nelson.cookie.service.CommandLineService;
import com.nelson.cookie.service.CommandLineServiceImpl;

@SpringBootApplication(scanBasePackages = { "com.nelson.cookie" })
public class CookieCountApplication implements CommandLineRunner {
	
	private final static Logger logger = LoggerFactory.getLogger(CookieCountApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CookieCountApplication.class, args);
	}
	
	@Bean
	public CommandLineService getCommandLineService() {
		return new CommandLineServiceImpl();
	}
	

	@Override
	public void run(String... args)  {
		if(Arrays.asList(args).contains("--help")) {
			printHelp();
		}else if(args.length < 4) {
			System.err.println("Not enough parameter");
			printHelp();
		}
		else {
			try {
				CommandParam param = getCommandLineService().parseParam(args);
				List<String> cookieList = getCommandLineService().getMaxCookieByDate(param.getDate());
				if(cookieList !=null && cookieList.size() > 0) {
					cookieList.stream().forEach(cookie->System.out.println(cookie));
				}else {
					System.out.println("No data found");
				}
			}catch (InValidParameterException e) {
				logger.error(e.getMessage());
				printHelp();
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	private void printHelp() {
		System.out.println("Usage:\njava -jar <<cookie.jar>> -f <<filename.csv>> -d <<yyyy-MM-dd>>");
	}
	
}
