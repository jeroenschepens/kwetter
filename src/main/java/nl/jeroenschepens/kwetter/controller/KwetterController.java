package nl.jeroenschepens.kwetter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "kwetterController")
@ViewScoped
public class KwetterController {

	@Inject
	private KwetterService kwetterService;

	@Inject
	private FacesContext facesContext;

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

	public List<Tweet> getAllTweets() {
		User user = getCurrentUser();
		List<Tweet> tweets = new ArrayList<Tweet>();
		for (User following : user.getFollowing()) {
			tweets.addAll(following.getTweets());
		}
		return tweets;
	}

	public List<User> getAllUsers() {
		return kwetterService.findAll();
	}
}