package nl.jeroenschepens.kwetter.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Size(min = 1, max = 32)
	private String name;

	@NotNull
	@Size(min = 1, max = 32)
	private String password;

	@NotNull
	@Size(min = 1, max = 32)
	private String web;

	@NotNull
	@Size(min = 1, max = 255)
	private String bio;

	@NotNull
	@Size(min = 1, max = 32)
	private String role;

	public User() {
	}

	public User(String naam) {
		this.name = naam;
	}

	public User(String naam, String web, String bio) {
		this.name = naam;
		this.web = web;
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((web == null) ? 0 : web.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (other.getName().toLowerCase().equals(this.getName().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "twitter.domain.User[naam=" + name + "]";
	}
}