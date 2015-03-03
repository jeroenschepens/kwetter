package nl.jeroenschepens.kwetter.service;

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
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.dao.UserDAO;
import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;

@Singleton
@Startup
public class TrendWatcher {

	private static final String REGEX_PATTERN = "(#\\w+)";

	private Comparator<Entry<String, Integer>> valueComparator;

	@Inject
	private UserDAO userDAO;

	private Pattern pattern;

	private List<Entry<String, Integer>> trends;

	public List<Entry<String, Integer>> getTrends() {
		return Collections.unmodifiableList(trends);
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

	@Schedule(hour = "*", minute = "*")
	private void computeTrends() {
		trends.clear();
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		for (User user : userDAO.findAll()) {
			for (Tweet tweet : user.getTweets()) {
				Set<String> set = new HashSet<String>();
				Matcher m = pattern.matcher(tweet.getTweet());
				while (m.find()) {
					String hashtag = m.group(1);
					if (!set.contains(hashtag)) {
						int count = temp.containsKey(hashtag) ? temp
								.get(hashtag) : 0;
						temp.put(hashtag, count + 1);
						set.add(hashtag);
					}
				}
			}
		}
		int i = 0;
		for (Entry<String, Integer> entry : sortHashMapByValue(temp)) {
			if (i < 10) {
				trends.add(entry);
				i++;
			} else {
				break;
			}
		}
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