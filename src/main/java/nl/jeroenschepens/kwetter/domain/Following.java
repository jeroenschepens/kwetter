package nl.jeroenschepens.kwetter.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Following implements Serializable {

	private static final long serialVersionUID = -1105498347027267560L;

	@Id
	@Size(min = 1, max = 32)
	public String user;

	@Id
	@Size(min = 1, max = 140)
	public String follows;

	public Following() {
	}

	public Following(String user, String follows) {
		this.user = user;
		this.follows = follows;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFollows() {
		return follows;
	}

	public void setFollows(String follows) {
		this.follows = follows;
	}
}