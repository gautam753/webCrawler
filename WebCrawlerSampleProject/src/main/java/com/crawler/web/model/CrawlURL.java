package com.crawler.web.model;

public class CrawlURL {
  public String url;
  public Integer depth;

  public CrawlURL(String url, Integer depth) {
    this.url = url;
    this.depth = depth;
  }

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public Integer getDepth() {
	return depth;
}

public void setDepth(Integer depth) {
	this.depth = depth;
}

@Override
public String toString() {
	return "CrawlURL [url=" + url + ", depth=" + depth + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((depth == null) ? 0 : depth.hashCode());
	result = prime * result + ((url == null) ? 0 : url.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CrawlURL other = (CrawlURL) obj;
	if (depth == null) {
		if (other.depth != null)
			return false;
	} else if (!depth.equals(other.depth))
		return false;
	if (url == null) {
		if (other.url != null)
			return false;
	} else if (!url.equals(other.url))
		return false;
	return true;
}
  
  
}
