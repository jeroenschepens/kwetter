package nl.jeroenschepens.kwetter.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@Path("/users")
@RequestScoped
public class UserService {

	@Inject
	private KwetterService kwetterService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return kwetterService.findAllUsers();
	}

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username) {
		return kwetterService.findUser(username);
	}
}