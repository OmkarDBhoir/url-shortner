package com.shorturl.shorturl.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.shorturl.shorturl.services.UrlService;
import com.shorturl.shorturl.utils.FLogger;


@RestController
@RequestMapping("/api")
public class UrlController {
	
	private static final Logger logger_cat = LogManager.getLogger(UrlController.class);
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping(value="/url/shorten", consumes="text/plain")
	@ResponseBody
	public String shortUrl(@RequestBody String longUrl) {
		String shortUrl = null;
		String methodName = "shortUrl";
		
		try {
			FLogger.debug(logger_cat, "Entered into method " + methodName);
			
			FLogger.debug(logger_cat, "Long Url: " + longUrl);
			
			shortUrl = urlService.generateShortUrl(longUrl);
			
			FLogger.debug(logger_cat, "Short Url generated: " + shortUrl); 
			
			
		} catch (Exception e) {
			
			FLogger.error(logger_cat, "Error", e);
		}
		
		return shortUrl;
		
	}
	
	@GetMapping(value="/{shortUrl}")
	public RedirectView redirectToOriginalUrl(@PathVariable String shortUrl) {
		
		RedirectView redirectView = new RedirectView();
		String methodName = "redirectToOriginalUrl";
		
		try {
			FLogger.debug(logger_cat, "Entered into method " + methodName);
			
			FLogger.debug(logger_cat, "Short Url: " + shortUrl); 
			
			String longUrl = urlService.getLongUrl(shortUrl);
			
			FLogger.debug(logger_cat, "Redirecting to...." + longUrl);
			
			redirectView.setUrl(longUrl);
			redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			return redirectView;
			
		} catch (Exception e) {

			FLogger.error(logger_cat, "Error", e);
			
			redirectView.setUrl(shortUrl);
			redirectView.setStatusCode(HttpStatus.BAD_REQUEST);
		}
		
		return redirectView;
	}
}
