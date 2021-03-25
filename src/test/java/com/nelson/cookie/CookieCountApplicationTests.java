package com.nelson.cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import com.nelson.cookie.domain.CommandParam;
import com.nelson.cookie.exception.InValidParameterException;
import com.nelson.cookie.service.CommandLineService;

@SpringBootTest
class CookieCountApplicationTests {
	
	
	@Autowired
	CommandLineService commandLineService;
	
	@Autowired
    ApplicationContext ctx;
	
	@Test
	void contextLoads(@Autowired Environment env) throws Exception {
		String[] args = {"-f","input.csv","-d","2018-12-09"};
		try {
			CommandParam commandParam = commandLineService.parseParam(args);
			assertEquals("AtY0laUfhglK3lC7", commandLineService.getMaxCookieByDate(commandParam.getDate()).get(0));
		}catch(Exception e) {
			assertFalse(Boolean.TRUE);
		}
	}

}
