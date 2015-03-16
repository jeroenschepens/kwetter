package nl.jeroenschepens.kwetter.dao;

import java.util.List;

import javax.enterprise.event.Observes;

import nl.jeroenschepens.kwetter.domain.Tweet;

public interface TweetDAO {

	void createTweet(@Observes Tweet tweet);

	public List<Tweet> findAll();

	public List<Tweet> findByUser(String username);

	public List<Tweet> findNews(List<String> usernames);
}