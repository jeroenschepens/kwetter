package nl.jeroenschepens.kwetter.domain;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tweet;
	private Date postDate;
	private String postedFrom;

	public Tweet() {
	}

	public Tweet(String tweet) {
		this.tweet = tweet;
	}

	public Tweet(String tweet, Date datum, String vanaf) {
		this.tweet = tweet;
		this.postDate = datum;
		this.postedFrom = vanaf;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Date getDatum() {
		return postDate;
	}

	public void setDatum(Date datum) {
		this.postDate = datum;
	}

	public String getVanaf() {
		return postedFrom;
	}

	public void setVanaf(String vanaf) {
		this.postedFrom = vanaf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result
				+ ((postedFrom == null) ? 0 : postedFrom.hashCode());
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
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (postedFrom == null) {
			if (other.postedFrom != null)
				return false;
		} else if (!postedFrom.equals(other.postedFrom))
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
		return "twitter.domain.Tweet[id=" + postDate.toString() + "]";
	}
}