package com.google.api.services.samples.youtube.cmdline.data;

import java.util.ArrayList;
import java.util.List;

public class YoutubeFeeder {

	public static void main(String[] args) {
		List<YoutubeSearchComments> searchCommentDataSet = new ArrayList<YoutubeSearchComments>();
		Search searchClass = new Search();
		List<String> videoids = searchClass.searchVideo("Bajaj Pulsar");
		System.out.println("Got the following videoIds: " + videoids);

		CommentHandling retriveComments = new CommentHandling();
		System.out.println("Size of Video links is : "+ videoids);
		for (String videoId : videoids) {
			
			if (videoId != null) {
				if(videoId.equals("GGCN3_mm1hk")){
					System.out.println();
				}
				List<YoutubeSearchComments> searchCommentData = retriveComments
						.SearchVideoComment(videoId, "Bajaj Pulsar");
				if(null!=searchCommentData)
				for (YoutubeSearchComments abc : searchCommentData) {
					searchCommentDataSet.add(abc);
				}
			}
		}
		
		System.out.println(searchCommentDataSet.size());
		System.out.println(searchCommentDataSet);
	}
}
