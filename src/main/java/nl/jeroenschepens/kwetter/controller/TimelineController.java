package nl.jeroenschepens.kwetter.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "timelineController")
@ViewScoped
public class TimelineController {

	@Inject
	private KwetterService kwetterService;

	@Inject
	private FacesContext facesContext;

	private String searchTerm;

	private Tweet draft;

	private boolean mention = false;

	private boolean search = false;

	@PostConstruct
	public void init() {
		initDraft();
	}

	private void initDraft() {
		draft = new Tweet();
		draft.setPostedfrom("Web");
		draft.setPoster(getUsername().toLowerCase());
	}

	public Tweet getDraft() {
		return draft;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public void postDraft() {
		draft.setPostdate(new Date());
		kwetterService.postTweet(draft);
		initDraft();
	}

	public String getUsername() {
		return facesContext.getExternalContext().getUserPrincipal().getName();
	}

	public User getCurrentUser() {
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
		if (mention) {
			return kwetterService.getMentions(getUsername());
		} else if (search) {
			return kwetterService.searchTweets(searchTerm);
		} else {
			return kwetterService.getNewsfeed(getUsername());
		}
	}

	public boolean isMention() {
		return mention;
	}

	public void switchToNewsFeed() {
		this.search = false;
		this.mention = false;
	}

	public void switchToMentions() {
		this.search = false;
		this.mention = true;
	}

	public void switchToSearch() {
		this.mention = false;
		this.search = true;
	}
}