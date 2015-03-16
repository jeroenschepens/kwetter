package nl.jeroenschepens.kwetter.dao;

import java.util.List;

import nl.jeroenschepens.kwetter.domain.User;

public interface UserDAO {

	User findUser(String username);

	void createUser(User user);

	void editUser(User user);

	void removeUser(User user);

	List<User> findAllUsers();

	List<String> findAllFollowing(String username);

	int countFollowers(String username);

	int countFollowing(String username);
}