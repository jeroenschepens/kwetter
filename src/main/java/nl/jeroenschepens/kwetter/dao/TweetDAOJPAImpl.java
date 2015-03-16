package nl.jeroenschepens.kwetter.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import nl.jeroenschepens.kwetter.domain.Tweet;

@JPA
@Stateless
public class TweetDAOJPAImpl implements TweetDAO {

	@Inject
	private EntityManager em;

	public void createTweet(@Observes @JPA Tweet tweet) {
		em.persist(tweet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> findAll() {
		return (List<Tweet>) em.createQuery("select t from Tweet t")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> findByUser(String username) {
		return (List<Tweet>) em
				.createQuery("select t from Tweet t where t.poster = :poster")
				.setParameter("poster", username).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweet> findNews(List<String> usernames) {
		return (List<Tweet>) em
				.createQuery(
						"select t from Tweet t where t.poster in :usernames")
				.setParameter("usernames", usernames).getResultList();
	}
}