package nl.jeroenschepens.kwetter.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.dao.JPA;
import nl.jeroenschepens.kwetter.dao.TweetDAO;
import nl.jeroenschepens.kwetter.dao.UserDAO;
import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.interceptor.Trend;

@Stateless
public class KwetterService {

	@JPA
	@Inject
	Event<Tweet> tweetEvent;

	@JPA
	@Inject
	private UserDAO userDAO;

	@JPA
	@Inject
	private TweetDAO tweetDAO;

	public KwetterService() {
	}

	public User findUser(String username) {
		User user = userDAO.findUser(username);
		return user;
	}

	public void createUser(User user) {
		userDAO.createUser(user);
	}

	public void editUser(User user) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void removeUser(User user) {
		userDAO.removeUser(user);
	}

	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}

	public int countFollowers(String username) {
		return userDAO.countFollowers(username);
	}

	public int countFollowing(String username) {
		return userDAO.countFollowing(username);
	}

	@Trend
	public void postTweet(Tweet tweet) {
		tweetEvent.fire(tweet);
	}

	public List<Tweet> findTweetsByUser(String username) {
		return tweetDAO.findByUser(username);
	}

	public List<Tweet> findNews(String username) {
		List<String> usernames = userDAO.findAllFollowing(username);
		if (usernames.size() > 0) {
			return tweetDAO.findNews(usernames);
		} else {
			return new ArrayList<Tweet>();
		}
	}

	public List<Tweet> findMentions(String username) {
		username = '@' + username.toLowerCase();
		List<Tweet> mentions = new ArrayList<Tweet>();
		for (Tweet tweet : tweetDAO.findAll()) {
			if (tweet.getTweet().toLowerCase().contains(username)) {
				mentions.add(tweet);
			}
		}
		return mentions;
	}

	public List<Tweet> searchTweets(String text) {
		text = text.toLowerCase();
		List<Tweet> results = new ArrayList<Tweet>();
		for (Tweet tweet : tweetDAO.findAll()) {
			if (tweet.getTweet().toLowerCase().contains(text)) {
				results.add(tweet);
			}
		}
		return results;
	}

	public int countTweets(String username) {
		return tweetDAO.findByUser(username).size();
	}
}