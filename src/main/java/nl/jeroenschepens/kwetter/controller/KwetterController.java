package nl.jeroenschepens.kwetter.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.domain.User;
import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "kwetterController")
@ViewScoped
public class KwetterController {

	@Inject
	private KwetterService kwetterService;

	@Inject
	private FacesContext facesContext;

	public int count() {
		return kwetterService.count();
	}

	public User getSelectedUser() {
		try {
			Map<String, String> params = facesContext.getExternalContext()
					.getRequestParameterMap();
			String username = params.get("username");
			User user = kwetterService.find(username);
			if (user != null) {
				return user;
			} else {
				throw new RuntimeException();
			}
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	public List<User> getAllUsers() {
		return kwetterService.findAll();
	}
}