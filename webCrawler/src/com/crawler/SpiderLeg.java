package com.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {
	// We'll use a fake USER_AGENT so the web server thinks the robot is a
	// normal web browser.
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private Set<String> reviewLinks = new HashSet<String>();
	private Set<String> pageLinks = new HashSet<String>();
	private Document htmlDocument;

	public void browseURL(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			if (connection.response().statusCode() == 200) // 200 is the HTTP OK
			{
				System.out
						.println("\n**Visiting** Received web page at " + url);
			}
			if (!connection.response().contentType().contains("text/html")) {
				System.out
						.println("**Failure** Retrieved something other than HTML");

			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	/**
	 * This performs all the work. It makes an HTTP request, checks the
	 * response, and then gathers up all the links on the page. Perform a
	 * searchForWord after the successful crawl
	 * 
	 * @param url
	 *            - The URL to visit
	 * @return whether or not the crawl was successful
	 */
	public boolean crawl(String url) {

		browseURL(url);
		Elements pageLinks = null;
		Elements reviewLinks = null;

		pageLinks = htmlDocument.select("a[data-page]");
		for (Element link : pageLinks) {
			this.pageLinks.add(link.absUrl("href"));
		}
		System.out.println("Found (" + pageLinks.size() + ") pagination links");

		reviewLinks = htmlDocument.getElementsByAttributeValueStarting("title",
				"Read Full Review");
		for (Element link : reviewLinks) {
			this.reviewLinks.add(link.absUrl("href"));
		}
		System.out.println("Found (" + reviewLinks.size() + ") review links");

		return true;
	}

	public void getReviewContent(Set<String> reviewPagesToVisit)
			throws ParseException {
		UserReview userReview = new UserReview();
		for (String reviewURLs : reviewPagesToVisit) {
			browseURL(reviewURLs);
			userReview.setReviewTitle(((Elements) htmlDocument.select("h1"))
					.text());
			userReview.setReviewComment(((Elements) htmlDocument
					.select("div.loststyle")).text());

			// userReview.setReviewComment(((Elements)htmlDocument.select("div.rvewdetail > div")).get(0).text());
			Elements userReviewElements = (((Elements) htmlDocument
					.select("div.rvewdetail > div")));

			String[] revDet = userReviewElements.get(0).childNodes().get(0)
					.toString().split("&nbsp;&nbsp; ");
			userReview.setReviewer(revDet[0]);

			Date reviewDate = new SimpleDateFormat("MMM dd, yyyy")
					.parse(revDet[1]);
			userReview.setReviewTime(reviewDate);

			userReview
					.setViewCount(Integer.parseInt((((Elements) htmlDocument
							.select("span.userrevview")).text())
							.substring(0, 4).trim()));

			// Elements
			// rating=(((Elements)htmlDocument.select("div.rating-stars")));

			System.out.println(userReview.getReviewComment());
			System.out.println(userReview.getReviewer());
			System.out.println(userReview.getReviewRating());
			System.out.println(userReview.getReviewTitle());
			System.out.println(userReview.getViewCount());
			System.out.println(userReview.getReviewTime());

		}
	}

	public Set<String> getPageLinks() {
		return this.pageLinks;
	}

	public Set<String> getReviewLinks() {
		return this.reviewLinks;
	}

}
