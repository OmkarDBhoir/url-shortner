package com.shorturl.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shorturl.shorturl.constants.UrlServiceConstants;
import com.shorturl.shorturl.utils.FLogger;

@SpringBootApplication
public class ShorturlApplication {
	
	private static final String LOG_CAT = UrlServiceConstants.LOGGER_CONSTANT;
	private static final String THIS_CLASS = ShorturlApplication.class.getName();

	public static void main(String[] args) {
		SpringApplication.run(ShorturlApplication.class, args);
		FLogger.error(LOG_CAT, THIS_CLASS, "Main", ShorturlApplication.class.getSimpleName() + " started.");
	}

}
