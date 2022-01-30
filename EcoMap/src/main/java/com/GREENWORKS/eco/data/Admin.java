package com.GREENWORKS.eco.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(name="username")
	protected String username;
	@Column(name="password")
    protected String password;
	
	public Admin() {
		
	}
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Admin(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 13 * result + (getUsername() != null ? getUsername().hashCode() : 0);
		result = 13 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		return result;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		Admin otherAdmin = (Admin) o;

		if (getId() != null ? !getId().equals(otherAdmin.getId()) : otherAdmin.getId() != null)
			return false;
		if (getUsername() != null ? !getUsername().equals(otherAdmin.getUsername()) : otherAdmin.getUsername() != null)
			return false;
		if (getPassword() != null ? !getPassword().equals(otherAdmin.getPassword()) : otherAdmin.getPassword() != null)
			return false;
	
		return true;
	}	
}
