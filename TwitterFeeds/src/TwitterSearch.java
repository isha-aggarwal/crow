import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterSearch {
	public static void main(String[] args) {

		Twitter twitter = new TwitterFactory().getInstance();

		AccessToken accessToken = new AccessToken(
				"3969471922-JivFit869fPYnqsg2tNsYgCPjhgRtWighCEUR0w",
				"ngyMlFfQABUSI3Hbs0jDz8C3nmiJ0HS7sWMLmwm5Ex4l8");
		twitter.setOAuthConsumer("QY292PoDDpawFOKtU524LfzuC",
				"ekdzYpOkuOg87St5iTzKhMwXAmAtUeze0mxeVmV7tWhdxMvTJo");
		twitter.setOAuthAccessToken(accessToken);
		Query query = new Query("car");
		query.setCount(100);
		// query.setSince("1999-12-12");
		// query.setResultType(Query.MIXED);

		int numberOfTweets = 1600;
		long lastID = Long.MAX_VALUE;
		List<Status> tweets = new ArrayList<Status>();
		while (tweets.size() < numberOfTweets) {
			if (numberOfTweets - tweets.size() > 100)
				query.setCount(100);
			else
				query.setCount(numberOfTweets - tweets.size());

			try {

				QueryResult result = twitter.search(query);
				tweets.addAll(result.getTweets());
				System.out.println("Gathered " + tweets.size() + " tweets");
				for (Status t : tweets)
					if (t.getId() < lastID)
						lastID = t.getId();
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to search tweets: "
						+ te.getMessage());
				System.exit(-1);
			}
			query.setMaxId(lastID - 1);
		}
		List<UserTweet> myTweets = showTweetDetails(tweets);
		System.out.println("Tweet Search Finished...!!"+myTweets.size());
	}

	public static List<UserTweet> showTweetDetails(List<Status> tweets) {
		List<UserTweet> myTweets=new ArrayList<UserTweet>();
		  for (int i = 0; i < tweets.size(); i++) {
			    Status t = (Status) tweets.get(i);

			    GeoLocation loc = t.getGeoLocation();
			    
			    String user = t.getUser().getScreenName();
			    String tweetMessage = t.getText();
			    if (loc!=null) {
			      Double lat = t.getGeoLocation().getLatitude();
			      Double lon = t.getGeoLocation().getLongitude();
			      //System.out.println(i + " USER: " + user + " wrote: " + tweetMessage + " located at " + lat + ", " + lon);
			    } 
			    else; 
			      //System.out.println(i + " USER: " + user + " wrote: " + tweetMessage);
			  
		      //Create Tweet Data
		  
		  	 
		  	 UserTweet userTweetData= new UserTweet();
		    Date tweetTime = t.getCreatedAt();
		    int tweetFavouriteCount = t.getFavoriteCount();
		    Long tweeetID = t.getId();
		    String tweetCountry = null;
		    String tweetCountryCode = null;
		    String countryFullName = null ;
		    if(null!=t.getPlace()){
		     tweetCountry =  t.getPlace().getCountry();
		     tweetCountryCode = t.getPlace().getCountryCode();
		     countryFullName = t.getPlace().getFullName();
		    }
		    double tweetCountryLatitude = 0;
		    double tweetCountryLongitude = 0;
		    if(null!=t.getGeoLocation()){
		    tweetCountryLatitude = t.getGeoLocation().getLatitude();
		    tweetCountryLongitude = t.getGeoLocation().getLongitude();
		    }
		    String tweetLanguage= t.getLang();
		    String tweetSource=null;
		    if(null!=t.getSource()){
			    Document doc=Jsoup.parse(t.getSource());
			    Element link = doc.select("a").first();
			     tweetSource= link.attr("href");
			    }
		    
		    userTweetData.setUser(user);
		  	 userTweetData.setTweetMessage(tweetMessage);
		  	 userTweetData.setTweeetID(tweeetID);
		  	 userTweetData.setTweetCountry(tweetCountry);
		  	 userTweetData.setTweetCountryCode(tweetCountryCode);
		  	 userTweetData.setTweetCountryLatitude(tweetCountryLatitude);
		  	 userTweetData.setTweetCountryLongitude(tweetCountryLongitude);;
		  	 userTweetData.setCountryFullName(countryFullName);
		  	 userTweetData.setTweetFavouriteCount(tweetFavouriteCount);
		  	 userTweetData.setTweetLanguage(tweetLanguage);
		  	 userTweetData.setTweetSource(tweetSource);
		  	 userTweetData.setTweetTime(tweetTime);
		  	 userTweetData.setTweetUserId(t.getUser().getId());
		  	 
		  	 myTweets.add(userTweetData);
		  }
		return myTweets;
	}
}