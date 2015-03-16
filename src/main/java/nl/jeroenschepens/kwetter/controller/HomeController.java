package nl.jeroenschepens.kwetter.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "homeController")
@ViewScoped
public class HomeController {

	@Inject
	private KwetterService kwetterService;

	public List<User> getAllUsers() {
		return kwetterService.findAllUsers();
	}
}