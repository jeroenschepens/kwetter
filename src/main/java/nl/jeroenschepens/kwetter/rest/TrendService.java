package nl.jeroenschepens.kwetter.rest;

import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.jeroenschepens.kwetter.service.TrendWatcher;

@Path("/trends")
public class TrendService {

	@Inject
	private TrendWatcher trendWatcher;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Entry<String, Integer>> getTrends() {
		return trendWatcher.getTrends();
	}
}