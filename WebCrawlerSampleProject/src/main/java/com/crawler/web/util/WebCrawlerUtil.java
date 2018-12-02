package com.crawler.web.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.web.model.CrawlURL;

public class WebCrawlerUtil {
	
	private static final Logger logger = LogManager.getLogger(WebCrawlerUtil.class);
	
	// We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	public static Map<CrawlURL, String> crawl(CrawlURL crawledUrl, Integer depth, Set<String> visited1, Set<String> visited2, Map<CrawlURL,String> linksToVisit) {
		logger.debug("crawl() START");
		Map<CrawlURL, String> links = null;
        try
        {
        	if(crawledUrl.depth < depth) {
        		Connection connection = Jsoup.connect(crawledUrl.url).userAgent(USER_AGENT);
                if(null != connection) {
                	Document htmlDocument = connection.get();
                    
                    Elements linksOnPage = htmlDocument.select("a[href]");
                    if(null != linksOnPage && !linksOnPage.isEmpty()) {
                    	links = new HashMap<>();
                        for(Element link : linksOnPage){
                        	String absUrl = link.absUrl("href");
                        	if(null != absUrl && !absUrl.isEmpty()) {
                        		
                        		if(!visited1.contains(absUrl) && !visited2.contains(absUrl) && !linksToVisit.containsKey(new CrawlURL(absUrl, crawledUrl.depth + 1))) {
                        			links.put(new CrawlURL(absUrl, crawledUrl.depth + 1), "");                    			
                        		}
                        	}
                        }
                    }            	
                }
        	}
        } catch(IOException ioe){
        	logger.error(ioe.getMessage());
        } catch(Exception e){
        	logger.error(e.getMessage());
        } finally {
        	logger.debug("crawl() END");
        }
        return links;
    }
	
	public static boolean checkValidUrl(String requestUrl) {
		logger.debug("checkValidUrl() START");
		boolean result = Boolean.FALSE;
		HttpURLConnection u = null;
		try {
			URL url = new URL(requestUrl);
			try {
				u = (HttpURLConnection )url.openConnection();
				String type = u.getHeaderField("Content-Type");
				String location = u.getHeaderField("Location");
				
				if(null != type && !type.isEmpty() 
						&& (type.equalsIgnoreCase("text/html") || type.equalsIgnoreCase("text/html; charset=utf-8"))
						&& (null == location || location.isEmpty())) {
					result = Boolean.TRUE;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(null != u) {
					u.disconnect();
					u = null;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			if(null != u) {
				u.disconnect();
				u = null;
			}
		}
		logger.debug("checkValidUrl() END");
		return result;
		
	}
	
	public static boolean numberOrNot(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }

}
