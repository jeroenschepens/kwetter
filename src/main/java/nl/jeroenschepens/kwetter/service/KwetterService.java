package nl.jeroenschepens.kwetter.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import nl.jeroenschepens.kwetter.dao.UserDAO;
import nl.jeroenschepens.kwetter.dao.UserDAOCollectionImpl;
import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;

@Stateless
public class KwetterService {

	private UserDAO userDAO = new UserDAOCollectionImpl();

	public KwetterService() {
		initUsers();
	}

	public void create(User user) {
		userDAO.create(user);
	}

	public void edit(User user) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void remove(User user) {
		userDAO.remove(user);
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public User find(String username) {
		return userDAO.find(username);
	}

	public int count() {
		return userDAO.count();
	}

	private void initUsers() {
		User u1 = new User("Hans", "http", "geboren 1");
		User u2 = new User("Frank", "httpF", "geboren 2");
		User u3 = new User("Tom", "httpT", "geboren 3");
		User u4 = new User("Sjaak", "httpS", "geboren 4");
		u1.addFollowing(u2);
		u1.addFollowing(u3);
		u1.addFollowing(u4);

		Tweet t1 = new Tweet("Hallo", new Date(), "PC");
		Tweet t2 = new Tweet("Hallo again", new Date(), "PC");
		Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC");
		u1.addTweet(t1);
		u1.addTweet(t2);
		u1.addTweet(t3);

		Tweet t4 = new Tweet("Ik ben Frank", new Date(), "Kwetter for iOS");
		Tweet t5 = new Tweet("Ik ben Tom", new Date(), "Kwetter for Android");
		Tweet t6 = new Tweet("Ik ben Sjaak", new Date(), "PC");
		u2.addTweet(t4);
		u3.addTweet(t5);
		u4.addTweet(t6);

		userDAO.create(u1);
		userDAO.create(u2);
		userDAO.create(u3);
		userDAO.create(u4);
	}
}