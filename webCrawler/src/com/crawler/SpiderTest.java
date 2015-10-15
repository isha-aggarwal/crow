package com.crawler;

import java.text.ParseException;

public class SpiderTest {
	/**
	 * This is our test. It creates a spider (which creates spider legs) and
	 * crawls the web.
	 * 
	 * @param args
	 *            - not used
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		Spider spider = new Spider();
		spider.search("http://www.bikedekho.com/bike-reviews.html");//("http://www.gaadi.com/", "Bajaj");
	}//("http://arstechnica.com/", "computer")
}