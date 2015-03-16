package nl.jeroenschepens.kwetter.controller;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;
import nl.jeroenschepens.kwetter.service.TrendWatcher;
import nl.jeroenschepens.kwetter.util.Cache;

@ManagedBean(name = "timelineController")
@ViewScoped
public class TimelineController {

	@Inject
	private KwetterService kwetterService;

	@Inject
	private TrendWatcher trendWatcher;

	@Inject
	private FacesContext facesContext;

	private String searchTerm;

	private Tweet draft;

	private boolean mention = false;

	private boolean search = false;

	private Cache<User> user = new Cache<User>() {
		@Override
		protected User storeValue() {
			return kwetterService.findUser(getUsername());
		}
	};

	private Cache<List<Tweet>> news = new Cache<List<Tweet>>() {
		@Override
		protected List<Tweet> storeValue() {
			return kwetterService.findNews(getUsername());
		}
	};

	private Cache<List<Tweet>> mentions = new Cache<List<Tweet>>() {
		@Override
		protected List<Tweet> storeValue() {
			return kwetterService.findMentions(getUsername());
		}
	};

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
		return user.getValue();
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
		if (mention) {
			return mentions.getValue();
		} else if (search) {
			return kwetterService.searchTweets(searchTerm);
		} else {
			return news.getValue();
		}
	}

	public List<Entry<String, Integer>> getTrends() {
		return trendWatcher.getTrends();
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