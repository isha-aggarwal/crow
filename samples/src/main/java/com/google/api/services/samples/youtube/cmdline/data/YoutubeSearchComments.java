package com.google.api.services.samples.youtube.cmdline.data;

import com.google.api.client.util.DateTime;

public class YoutubeSearchComments {
	public String AuthorChannelId;
	public String AuthorDisplayName;
	public String AuthorGoogleplusProfileUrl;
	public Long LikeCount;
	public DateTime PublishedAt;
	public String ViewerRating;
	public String AuthorChannelUrl;
	public String TextDisplay;
	public String SearchString;
	public String CommentId;
	
	public String getAuthorChannelId() {
		return AuthorChannelId;
	}
	public void setAuthorChannelId(String authorChannelId) {
		AuthorChannelId = authorChannelId;
	}
	public String getAuthorDisplayName() {
		return AuthorDisplayName;
	}
	public void setAuthorDisplayName(String authorDisplayName) {
		AuthorDisplayName = authorDisplayName;
	}
	public String getAuthorGoogleplusProfileUrl() {
		return AuthorGoogleplusProfileUrl;
	}
	public void setAuthorGoogleplusProfileUrl(String authorGoogleplusProfileUrl) {
		AuthorGoogleplusProfileUrl = authorGoogleplusProfileUrl;
	}
	public Long getLikeCount() {
		return LikeCount;
	}
	public void setLikeCount(Long long1) {
		LikeCount = long1;
	}
	public DateTime getPublishedAt() {
		return PublishedAt;
	}
	public void setPublishedAt(DateTime dateTime) {
		PublishedAt = dateTime;
	}
	public String getViewerRating() {
		return ViewerRating;
	}
	public void setViewerRating(String viewerRating) {
		ViewerRating = viewerRating;
	}
	public String getAuthorChannelUrl() {
		return AuthorChannelUrl;
	}
	public void setAuthorChannelUrl(String authorChannelUrl) {
		AuthorChannelUrl = authorChannelUrl;
	}
	public String getTextDisplay() {
		return TextDisplay;
	}
	public void setTextDisplay(String textDisplay) {
		TextDisplay = textDisplay;
	}
}
