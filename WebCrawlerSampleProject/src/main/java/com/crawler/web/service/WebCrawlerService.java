package com.crawler.web.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crawler.web.model.CrawlURL;
import com.crawler.web.model.WebCrawlerResponse;
import com.crawler.web.util.WebCrawlerUtil;

@Component
public class WebCrawlerService {

	private static final Logger logger = LogManager.getLogger(WebCrawlerService.class);
	
	private Set<String> domainLinksVisited = null;
	private Set<String> nonDomainLinksVisited = null;
	
	public WebCrawlerResponse crawl(String url, Integer depth) {
		logger.debug("crawl() STARTS");
		WebCrawlerResponse response = new WebCrawlerResponse(null, null, "Success");
		domainLinksVisited = new HashSet<>();
		nonDomainLinksVisited = new HashSet<>();
		Map<CrawlURL,String> hrefLinks = new ConcurrentHashMap<CrawlURL, String>();
		hrefLinks.put(new CrawlURL(url, 0),"");
		while(null != hrefLinks && !hrefLinks.isEmpty()) {
			hrefLinks.forEach((linkToVisit, value) -> {
				CrawlURL crawledUrl = linkToVisit;
				hrefLinks.remove(linkToVisit);
				if(((crawledUrl.url.contains(url) && crawledUrl.depth <= depth))) {
					logger.info("Remaining links to visit count: "+hrefLinks.size()+", Visiting link at: " + crawledUrl.toString());
					Map<CrawlURL,String> newLinksToCrawl = WebCrawlerUtil.crawl(crawledUrl,depth, domainLinksVisited, nonDomainLinksVisited, hrefLinks);
					if(null != newLinksToCrawl && !newLinksToCrawl.isEmpty()) {
						hrefLinks.putAll(newLinksToCrawl);						
					}
					domainLinksVisited.add(crawledUrl.url);	
				} else {
					nonDomainLinksVisited.add(crawledUrl.url);
				}
			});
		}
		if(!domainLinksVisited.isEmpty()) {
			response.setDomainLinksVisited(domainLinksVisited);
		}
		if(!nonDomainLinksVisited.isEmpty()) {
			response.setNonDomainLinksVisited(nonDomainLinksVisited);
		}
		logger.info("domainLinksVisited count: "+domainLinksVisited.size());
		logger.info("nonDomainLinksVisited count: "+nonDomainLinksVisited.size());
		logger.debug("crawl() END");
		return response;
	}
	
}
