package com.crawler;

import java.util.Date;

public class UserReview {
public String reviewer;
public String reviewComment;
public Date reviewTime;
public int  viewCount;
public String reviewTitle;
public String reviewRating;
public String getReviewer() {
	return reviewer;
}
public void setReviewer(String reviewer) {
	this.reviewer = reviewer;
}
public String getReviewComment() {
	return reviewComment;
}
public void setReviewComment(String reviewComment) {
	this.reviewComment = reviewComment;
}
public Date getReviewTime() {
	return reviewTime;
}
public void setReviewTime(Date reviewTime) {
	this.reviewTime = reviewTime;
}
public int getViewCount() {
	return viewCount;
}
public void setViewCount(int viewCount) {
	this.viewCount = viewCount;
}
public String getReviewTitle() {
	return reviewTitle;
}
public void setReviewTitle(String reviewTitle) {
	this.reviewTitle = reviewTitle;
}
public String getReviewRating() {
	return reviewRating;
}
public void setReviewRating(String reviewRating) {
	this.reviewRating = reviewRating;
}

}
