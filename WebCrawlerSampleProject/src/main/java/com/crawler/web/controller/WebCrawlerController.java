package com.crawler.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.web.model.WebCrawlerResponse;
import com.crawler.web.service.WebCrawlerService;
import com.crawler.web.util.WebCrawlerUtil;

@RestController
public class WebCrawlerController {
	private static final Logger logger = LogManager.getLogger(WebCrawlerController.class);
	
	@Autowired
	private WebCrawlerService service;
	
	@RequestMapping("/")
	public String healthCheck() {
		return "You are ready to crawl!!!";
	}
	
	@RequestMapping("/webcrawl")
	public WebCrawlerResponse callWebCrawler(@RequestParam(name="crawledUrl", required=true, defaultValue="Unknown") String crawledUrl,
			@RequestParam(name="depth", required=false) String depth) {
		logger.info("callWebCrawler() START");
		WebCrawlerResponse response = new WebCrawlerResponse(null, null, "Please provide valid input as URL String for 'crawledUrl' and any Integer value for 'depth'");
		if(null !=crawledUrl && !crawledUrl.isEmpty() && null != depth) {
			boolean isValidUrl = WebCrawlerUtil.checkValidUrl(crawledUrl);
			boolean isNumber = WebCrawlerUtil.numberOrNot(depth.toString());
			
			if(isValidUrl && isNumber) {
				response = service.crawl(crawledUrl, Integer.parseInt(depth));	
			}
		}
		logger.info("callWebCrawler() END");
		return response;
	}
}
