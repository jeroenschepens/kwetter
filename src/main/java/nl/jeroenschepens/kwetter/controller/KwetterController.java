package nl.jeroenschepens.kwetter.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import nl.jeroenschepens.kwetter.service.KwetterService;

@ManagedBean(name = "kwetterController")
@ViewScoped
public class KwetterController {

	@Inject
	private KwetterService kwetterService;

	public int count() {
		return kwetterService.count();
	}
}