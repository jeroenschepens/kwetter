package nl.jeroenschepens.kwetter.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class TweetProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object input) throws Exception {
		// TODO write implementation
		System.out.println(input);
		return input;
	}
}