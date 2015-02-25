package nl.jeroenschepens.kwetter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.jeroenschepens.kwetter.domain.User;

public class UserDAOCollectionImpl implements UserDAO {

	private HashMap<String, User> users;

	public UserDAOCollectionImpl() {
		users = new HashMap<String, User>();
	}

	@Override
	public int count() {
		return users.size();
	}

	@Override
	public void create(User user) {
		users.put(user.getName(), user);
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
		return users.get(username);
	}
}