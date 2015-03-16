package nl.jeroenschepens.kwetter.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import nl.jeroenschepens.kwetter.domain.User;

@JPA
@Stateless
public class UserDAOJPAImpl implements UserDAO {

	@Inject
	private EntityManager em;

	@Override
	public User findUser(String username) {
		return em.find(User.class, username);
	}

	@Override
	public void createUser(User user) {
		em.persist(user);
	}

	@Override
	public void editUser(User user) {
		em.merge(user);
	}

	@Override
	public void removeUser(User user) {
		em.remove(user);
	}

	@Override
	public List<User> findAllUsers() {
		@SuppressWarnings("unchecked")
		final List<User> results = em.createQuery("select u from User u")
				.getResultList();
		return results;
	}

	@Override
	public List<String> findAllFollowing(String username) {
		@SuppressWarnings("unchecked")
		final List<String> results = em
				.createNativeQuery(
						"SELECT f.follows FROM Following f where f.user = ?1")
				.setParameter(1, username).getResultList();
		return results;
	}

	@Override
	public int countFollowers(String username) {
		Number count = (Number) em
				.createNativeQuery(
						"SELECT count(f.user) FROM Following f where f.follows = ?1")
				.setParameter(1, username).getSingleResult();
		return count.intValue();
	}

	@Override
	public int countFollowing(String username) {
		Number count = (Number) em
				.createNativeQuery(
						"SELECT count(f.follows) FROM Following f where f.user = ?1")
				.setParameter(1, username).getSingleResult();
		return count.intValue();
	}
}