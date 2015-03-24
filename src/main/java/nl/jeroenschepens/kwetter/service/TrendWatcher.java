package nl.jeroenschepens.kwetter.service;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.dao.JPA;
import nl.jeroenschepens.kwetter.dao.TweetDAO;
import nl.jeroenschepens.kwetter.domain.Tweet;

@Lock(READ)
@Singleton
@Startup
public class TrendWatcher {

	private static final String REGEX_PATTERN = "(#\\w+)";

	private Comparator<Entry<String, Integer>> valueComparator;

	@JPA
	@Inject
	private TweetDAO tweetDAO;

	private Pattern pattern;

	private List<Entry<String, Integer>> trends;

	public List<Entry<String, Integer>> getTrends() {
		List<Entry<String, Integer>> topics = new ArrayList<>();
		topics.addAll(trends);
		return topics;
	}

	@PostConstruct
	private void init() {
		pattern = Pattern.compile(REGEX_PATTERN);
		valueComparator = new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		};
		trends = new ArrayList<>();
		computeTrends();
	}

	@AccessTimeout(-1)
	@Schedule(hour = "*", minute = "*", second = "0")
	private void computeTrends() {
		trends.clear();
		List<Entry<String, Integer>> topics = new ArrayList<>();
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		for (Tweet tweet : tweetDAO.findAll()) {
			Set<String> set = new HashSet<String>();
			Matcher m = pattern.matcher(tweet.getTweet());
			while (m.find()) {
				String hashtag = m.group(1);
				if (!set.contains(hashtag)) {
					int count = temp.containsKey(hashtag) ? temp.get(hashtag)
							: 0;
					temp.put(hashtag, count + 1);
					set.add(hashtag);
				}
			}
		}
		int i = 0;
		for (Entry<String, Integer> entry : sortHashMapByValue(temp)) {
			if (i < 10) {
				topics.add(entry);
				i++;
			} else {
				break;
			}
		}
		writeList(topics);
	}

	@Lock(WRITE)
	private void writeList(List<Entry<String, Integer>> trends) {
		this.trends.clear();
		this.trends.addAll(trends);
	}

	private List<Entry<String, Integer>> sortHashMapByValue(
			HashMap<String, Integer> temp) {
		Set<Entry<String, Integer>> entryOfMap = temp.entrySet();
		List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(
				entryOfMap);
		Collections.sort(entries, Collections.reverseOrder(valueComparator));
		return entries;
	}
}