package com.crawler;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Spider {
	private Set<String> pagesVisited = new HashSet<String>();
	private Set<String> pagesToVisit = new HashSet<String>();
	private Set<String> reviewPagesToVisit = new HashSet<String>();

	/**
	 * Our main launching point for the Spider's functionality. Internally it
	 * creates spider legs that make an HTTP request and parse the response (the
	 * web page).
	 * 
	 * @param url
	 *            - The starting point of the spider
	 * @param searchWord
	 *            - The word or string that you are searching for
	 * @throws ParseException
	 */
	public void search(String url) throws ParseException {
		SpiderLeg leg = new SpiderLeg();
		if (this.pagesToVisit.isEmpty()) {
			this.pagesToVisit.add(url);
		}

		for (Iterator iterator = pagesToVisit.iterator(); iterator.hasNext();) {
			String page = (String) iterator.next();
			leg.crawl(page);
			this.pagesToVisit.addAll(leg.getPageLinks());
			this.reviewPagesToVisit.addAll(leg.getReviewLinks());
			this.pagesVisited.add(page);
		}

		leg.getReviewContent(this.reviewPagesToVisit);
		System.out.println("\n**Done** Visited " + this.pagesVisited.size()
				+ " web page(s)");
	}

}