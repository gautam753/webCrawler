package com.crawler.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCrawlerSampleProjectApplication {
	private static final Logger logger = LogManager.getLogger(WebCrawlerSampleProjectApplication.class);
	
	public static void main(String[] args) {
		logger.info("WebCrawlerSampleProjectApplication START");
		SpringApplication.run(WebCrawlerSampleProjectApplication.class, args);
		logger.info("WebCrawlerSampleProjectApplication End");
	}
}
