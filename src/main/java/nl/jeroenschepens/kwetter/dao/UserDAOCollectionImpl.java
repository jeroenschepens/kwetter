package nl.jeroenschepens.kwetter.dao;

import java.util.ArrayList;
import java.util.List;

import nl.jeroenschepens.kwetter.domain.User;

public class UserDAOCollectionImpl implements UserDAO {

	private List<User> users;

	public UserDAOCollectionImpl() {
		users = new ArrayList<User>();
	}

	@Override
	public int count() {
		return users.size();
	}

	@Override
	public void create(User user) {
		users.add(user);
	}

	@Override
	public void edit(User user) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<User>(users);
	}

	@Override
	public void remove(User user) {
		users.remove(user);
	}

	@Override
	public User find(Long id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}