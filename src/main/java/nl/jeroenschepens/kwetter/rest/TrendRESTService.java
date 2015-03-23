package nl.jeroenschepens.kwetter.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.jeroenschepens.kwetter.service.TrendWatcher;

@Path("/trends")
public class TrendRESTService {

	@Inject
	private TrendWatcher trendWatcher;

	@GET
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public List<Entry<String, Integer>> getTrends() {
		return trendWatcher.getTrends();
	}
}