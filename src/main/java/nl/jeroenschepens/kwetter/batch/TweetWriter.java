package nl.jeroenschepens.kwetter.batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import nl.jeroenschepens.kwetter.domain.Tweet;

@Named
public class TweetWriter extends AbstractItemWriter {

	@Inject
	private EntityManager em;

	@Override
	public void writeItems(List<Object> items) throws Exception {
		@SuppressWarnings("unchecked")
		List<Tweet> tweets = (List<Tweet>) (List<?>) items;
		for (Tweet tweet : tweets) {
			System.out.println(tweet);
			em.persist(tweet);
		}
	}
}