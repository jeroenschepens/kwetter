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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((follows == null) ? 0 : follows.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Following other = (Following) obj;
		if (follows == null) {
			if (other.follows != null)
				return false;
		} else if (!follows.equals(other.follows))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Following [user=" + user + ", follows=" + follows + "]";
	}
}