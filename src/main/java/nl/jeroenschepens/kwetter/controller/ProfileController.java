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
		return params.get("username");
	}

	public User getSelectedUser() {
		return kwetterService.find(getUsername());
	}

	public int getUserCount() {
		return kwetterService.count();
	}

	public int getTweetCount() {
		return kwetterService.getTweetCount(getUsername());
	}

	public int getFollowingCount() {
		return kwetterService.getFollowingCount(getUsername());
	}

	public int getFollowersCount() {
		return kwetterService.getFollowersCount(getUsername());
	}

	public List<Tweet> getTweets() {
		return new ArrayList<Tweet>(getSelectedUser().getTweets());
	}
}