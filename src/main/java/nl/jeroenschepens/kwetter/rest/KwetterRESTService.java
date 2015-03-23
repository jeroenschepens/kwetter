package nl.jeroenschepens.kwetter.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.jeroenschepens.kwetter.domain.Tweet;
import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@Path("/users")
@RequestScoped
public class KwetterRESTService {

	@Inject
	private KwetterService kwetterService;

	@GET
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public List<User> getAllUsers() {
		return kwetterService.findAllUsers();
	}

	@GET
	@Path("/{username}")
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public User getUser(@PathParam("username") String username) {
		return kwetterService.findUser(username);
	}

	@PUT
	@Path("/{username}")
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public User updateUser(@PathParam("username") String username, User user) {
		if (user.getName().equalsIgnoreCase(username)) {
			kwetterService.editUser(user);
			return user;
		} else {
			throw new RuntimeException("Failure");
		}
	}

	@GET
	@Path("/{username}/tweets")
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public List<Tweet> getTweets(@PathParam("username") String username) {
		return kwetterService.findTweetsByUser(username);
	}

	@POST
	@Path("/{username}/tweets")
	@Consumes(TEXT_PLAIN)
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public Tweet postTweet(@PathParam("username") String username,
			String content) {
		Tweet tweet = new Tweet(content);
		tweet.setPostdate(new Date());
		tweet.setPoster(username);
		tweet.setPostedfrom("API");
		kwetterService.postTweet(tweet);
		return tweet;
	}

	@GET
	@Path("/{username}/news")
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public List<Tweet> getNews(@PathParam("username") String username) {
		return kwetterService.findNews(username);
	}

	@GET
	@Path("/{username}/mentions")
	@Produces({ APPLICATION_JSON, APPLICATION_XML })
	public List<Tweet> getMentions(@PathParam("username") String username) {
		return kwetterService.findMentions(username);
	}
}