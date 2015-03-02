package nl.jeroenschepens.kwetter.domain;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {

	private static final long serialVersionUID = 2953236183857132524L;

	private String tweet;
	private String poster;
	private Date postdate;
	private String postedfrom;

	public Tweet() {
	}

	public Tweet(String tweet) {
		this.tweet = tweet;
	}

	public Tweet(String tweet, Date postdate, String postedfrom) {
		this.tweet = tweet;
		this.postdate = postdate;
		this.postedfrom = postedfrom;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public String getPostedfrom() {
		return postedfrom;
	}

	public void setPostedfrom(String postedfrom) {
		this.postedfrom = postedfrom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((postdate == null) ? 0 : postdate.hashCode());
		result = prime * result
				+ ((postedfrom == null) ? 0 : postedfrom.hashCode());
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((tweet == null) ? 0 : tweet.hashCode());
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
		Tweet other = (Tweet) obj;
		if (postdate == null) {
			if (other.postdate != null)
				return false;
		} else if (!postdate.equals(other.postdate))
			return false;
		if (postedfrom == null) {
			if (other.postedfrom != null)
				return false;
		} else if (!postedfrom.equals(other.postedfrom))
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (tweet == null) {
			if (other.tweet != null)
				return false;
		} else if (!tweet.equals(other.tweet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "twitter.domain.Tweet[id=" + postdate.toString() + "]";
	}
}