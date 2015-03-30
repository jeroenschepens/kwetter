package nl.jeroenschepens.kwetter.batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

@Named
public class TweetWriter extends AbstractItemWriter {

	@Override
	public void writeItems(List<Object> items) throws Exception {
		// TODO write implementation
		System.out.println("Write " + items.size() + " items");
	}
}