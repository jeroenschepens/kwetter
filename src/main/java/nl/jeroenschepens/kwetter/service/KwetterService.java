package nl.jeroenschepens.kwetter.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.dao.UserDAO;
import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;

@Stateless
public class KwetterService {

	@Inject
	private UserDAO userDAO;

	public KwetterService() {
	}

	public void create(User user) {
		userDAO.create(user);
	}

	public void postTweet(Tweet tweet) {
		userDAO.find(tweet.getPoster()).addTweet(tweet);
	}

	public void edit(User user) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void remove(User user) {
		userDAO.remove(user);
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public User find(String username) {
		return userDAO.find(username);
	}

	public int count() {
		return userDAO.count();
	}

	public int getTweetCount(String username) {
		return userDAO.find(username).getTweets().size();
	}

	public int getFollowingCount(String username) {
		return userDAO.find(username).getFollowing().size();
	}

	public List<Tweet> getNewsfeed(String username) {
		User user = this.find(username);
		List<Tweet> tweets = new ArrayList<Tweet>();
		for (User following : user.getFollowing()) {
			tweets.addAll(following.getTweets());
		}
		return tweets;
	}

	public List<Tweet> getMentions(String username) {
		username = '@' + username.toLowerCase();
		List<Tweet> mentions = new ArrayList<Tweet>();
		for (User user : findAll()) {
			for (Tweet tweet : user.getTweets()) {
				if (tweet.getTweet().toLowerCase().contains(username)) {
					mentions.add(tweet);
				}
			}
		}
		return mentions;
	}

	public int getFollowersCount(String username) {
		int followers = 0;
		User temp = new User(username);
		for (User user : findAll()) {
			if (user.getFollowing().contains(temp)) {
				followers++;
			}
		}
		return followers;
	}

	public List<Tweet> searchTweets(String text) {
		text = text.toLowerCase();
		List<Tweet> results = new ArrayList<Tweet>();
		for (User user : findAll()) {
			for (Tweet tweet : user.getTweets()) {
				if (tweet.getTweet().toLowerCase().contains(text)) {
					results.add(tweet);
				}
			}
		}
		return results;
	}
}