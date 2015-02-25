package nl.jeroenschepens.kwetter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "kwetterController")
@SessionScoped
public class KwetterController {

	@Inject
	private KwetterService kwetterService;

	@Inject
	private FacesContext facesContext;

	private boolean mention = false;

	public User getSelectedUser() {
		try {
			Map<String, String> params = facesContext.getExternalContext()
					.getRequestParameterMap();
			String username = params.get("username");
			User user = kwetterService.find(username);
			if (user != null) {
				return user;
			} else {
				throw new RuntimeException();
			}
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	public User getCurrentUser() {
		// TODO Mock implementation; always returns "Hans"
		return kwetterService.find("hans");
	}

	public int getUserCount() {
		return kwetterService.count();
	}

	public int getTweetCount() {
		return getCurrentUser().getTweets().size();
	}

	public int getFollowingCount() {
		return getCurrentUser().getFollowing().size();
	}

	public int getFollowersCount() {
		return kwetterService.getFollowersCount(getCurrentUser().getName());
	}

	public List<Tweet> getTweets() {
		if (!mention) {
			return getFollowingTweets();
		} else {
			return getMentions();
		}
	}

	private List<Tweet> getFollowingTweets() {
		User user = getCurrentUser();
		List<Tweet> tweets = new ArrayList<Tweet>();
		for (User following : user.getFollowing()) {
			tweets.addAll(following.getTweets());
		}
		return tweets;
	}

	private List<Tweet> getMentions() {
		String username = '@' + getCurrentUser().getName().toLowerCase();
		List<Tweet> mentions = new ArrayList<Tweet>();
		for (User user : getAllUsers()) {
			for (Tweet tweet : user.getTweets()) {
				if (tweet.getTweet().toLowerCase().contains(username)) {
					mentions.add(tweet);
				}
			}
		}
		return mentions;
	}

	public List<User> getAllUsers() {
		return kwetterService.findAll();
	}

	public boolean isMention() {
		return mention;
	}

	public void switchMention() {
		this.mention = !mention;
	}
}