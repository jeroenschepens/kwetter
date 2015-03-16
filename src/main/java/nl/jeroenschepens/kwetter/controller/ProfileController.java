package nl.jeroenschepens.kwetter.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "profileController")
@ViewScoped
public class ProfileController {

	@Inject
	private KwetterService kwetterService;

	@Inject
	private FacesContext facesContext;

	public String getUsername() {
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();
		String username = params.get("username");
		if (username != null) {
			return username;
		} else {
			throw new NullPointerException("User does not exist!");
		}
	}

	public User getSelectedUser() {
		return kwetterService.findUser(getUsername());
	}

	public int getTweetCount() {
		return kwetterService.countTweets(getUsername());
	}

	public int getFollowingCount() {
		return kwetterService.countFollowing(getUsername());
	}

	public int getFollowersCount() {
		return kwetterService.countFollowers(getUsername());
	}

	public List<Tweet> getTweets() {
		return kwetterService.findTweetsByUser(getUsername());
	}
}