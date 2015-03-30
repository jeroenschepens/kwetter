package nl.jeroenschepens.kwetter.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import nl.jeroenschepens.kwetter.domain.Tweet;

@Named
public class TweetProcessor implements ItemProcessor {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public Object processItem(Object input) throws Exception {
		InputTweet inputTweet = (InputTweet) input;
		Date postDate = DATE_FORMAT.parse(inputTweet.getPostDate());
		String poster = inputTweet.getScreenName().toLowerCase();
		Tweet tweet = new Tweet();
		tweet.setTweet(inputTweet.getTweet());
		tweet.setPoster(poster);
		tweet.setPostdate(postDate);
		tweet.setPostedfrom(inputTweet.getPostedFrom());
		return tweet;
	}
}