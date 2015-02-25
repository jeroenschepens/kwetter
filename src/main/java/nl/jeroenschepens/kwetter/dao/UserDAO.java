package nl.jeroenschepens.kwetter.dao;

import nl.jeroenschepens.kwetter.domain.User;
import java.util.List;

public interface UserDAO {

	int count();

	void create(User user);

	void edit(User user);

	List<User> findAll();

	User find(String username);

	void remove(User user);
}