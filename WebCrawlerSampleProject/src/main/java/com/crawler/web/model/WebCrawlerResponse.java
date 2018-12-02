package com.crawler.web.model;

import java.util.Set;

public class WebCrawlerResponse {
	private Set<String> domainLinksVisited;
	private Set<String> nonDomainLinksVisited;
	private String responseMessage;
	
	public WebCrawlerResponse(Set<String> domainLinksVisited, Set<String> nonDomainLinksVisited, String responseMessage){
		this.domainLinksVisited = domainLinksVisited;
		this.nonDomainLinksVisited = nonDomainLinksVisited;
		this.responseMessage = responseMessage;
	}

	public Set<String> getDomainLinksVisited() {
		return domainLinksVisited;
	}

	public void setDomainLinksVisited(Set<String> domainLinksVisited) {
		this.domainLinksVisited = domainLinksVisited;
	}

	public Set<String> getNonDomainLinksVisited() {
		return nonDomainLinksVisited;
	}

	public void setNonDomainLinksVisited(Set<String> nonDomainLinksVisited) {
		this.nonDomainLinksVisited = nonDomainLinksVisited;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
}
