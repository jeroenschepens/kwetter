package nl.jeroenschepens.kwetter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;

@Singleton
public class UserDAOCollectionImpl implements UserDAO {

	private HashMap<String, User> users;

	public UserDAOCollectionImpl() {
		users = new HashMap<String, User>();
		initUsers();
	}

	@Override
	public int count() {
		return users.size();
	}

	@Override
	public void create(User user) {
		users.put(user.getName().toLowerCase(), user);
	}

	@Override
	public void edit(User user) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public void remove(User user) {
		users.remove(user);
	}

	@Override
	public User find(String username) {
		return users.get(username.toLowerCase());
	}

	private void initUsers() {
		User u1 = new User("Hans", "http", "geboren 1");
		User u2 = new User("Frank", "httpF", "geboren 2");
		User u3 = new User("Tom", "httpT", "geboren 3");
		User u4 = new User("Sjaak", "httpS", "geboren 4");
		u1.addFollowing(u2);
		u1.addFollowing(u3);
		u1.addFollowing(u4);

		u2.addFollowing(u1);
		u3.addFollowing(u1);

		Tweet t1 = new Tweet("Hallo", new Date(), "PC");
		Tweet t2 = new Tweet("#Hallo again", new Date(), "PC");
		Tweet t3 = new Tweet("#Hallo where are you", new Date(), "PC");
		u1.addTweet(t1);
		u1.addTweet(t2);
		u1.addTweet(t3);

		Tweet t4 = new Tweet(
				"Ik #ben #Frank #hamer #zaag #boor #machine #spijker #schroef",
				new Date(), "Kwetter for iOS");
		Tweet t5 = new Tweet("Ik #ben #Tom #Tom", new Date(),
				"Kwetter for Android");
		Tweet t6 = new Tweet("Ik #ben #Sjaak", new Date(), "PC");
		Tweet t7 = new Tweet("Chillen met @Hans", new Date(), "PC");
		u2.addTweet(t4);
		u3.addTweet(t5);
		u4.addTweet(t6);
		u4.addTweet(t7);

		this.create(u1);
		this.create(u2);
		this.create(u3);
		this.create(u4);
	}

	public void test(@Observes Tweet tweet) {
		// Mock
		System.out.println("Persist " + tweet.getTweet());
	}
}