package nl.jeroenschepens.kwetter.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String web;
	private String bio;

	private Collection<User> following = new ArrayList<User>();
	private Collection<Tweet> tweets = new ArrayList<Tweet>();

	public User() {
	}

	public User(String naam) {
		this.name = naam;
	}

	public User(String naam, String web, String bio) {
		this.name = naam;
		this.web = web;
		this.bio = bio;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Collection<User> getFollowing() {
		return Collections.unmodifiableCollection(following);
	}

	public void setFollowing(Collection<User> following) {
		this.following = following;
	}

	public Collection<Tweet> getTweets() {
		return Collections.unmodifiableCollection(tweets);
	}

	public void setTweets(Collection<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Boolean addFollowing(User following) {
		return this.following.add(following);
	}

	public Boolean addTweet(Tweet tweet) {
		return this.tweets.add(tweet);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((web == null) ? 0 : web.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (other.getName().toLowerCase().equals(this.getName().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "twitter.domain.User[naam=" + name + "]";
	}
}