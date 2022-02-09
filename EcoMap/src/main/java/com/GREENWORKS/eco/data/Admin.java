package com.GREENWORKS.eco.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * The Admin class is for handling admin related database transactions. This 
 * class is exclusively used for verifying user credentials in the admin panel. 
 */
@Entity
@Table(name = "users")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id", unique = true, nullable = false)
	protected Integer id;
	@Column(name="username", unique = false, nullable = true, length = 30)
	protected String username;
	@Column(name="password", unique = false, nullable = true, length = 30)
    protected String password;
	
	/***
	 * Zero parameter constructor. 
	 */
	public Admin() {
		
	}
	
	/***
	 * Two parameter constructor. 
	 * @param username The value to be assigned to the username. 
	 * @param password The value to be assigned to the password. 
	 */
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/***
	 * Single parameter constructor. This constructor should be used carefully
	 * because it is easy to cause primary key constraint violations in certain
	 * use cases. 
	 * @param id The value to be assigned to the id. 
	 */
	public Admin(Integer id) {
		this.id = id;
	}
	
	/***
	 * Accessor method for the id instance variable. 
	 * @return The contents of id. 
	 */
	public Integer getId() {
		return id;
	}
	
	/***
	 * Mutator method for the id instance variable. 
	 * @param id The parameter to be assigned to id. 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/***
	 * Accessor method for the username instance variable. 
	 * @return The contents of username. 
	 */
	public String getUsername() {
		return username;
	}
	
	/***
	 * Mutator method for the username instance variable. 
	 * @param username The parameter to be assigned to username. 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/***
	 * Accessor method for the password instance variable. 
	 * @return The contents of password. 
	 */
	public String getPassword() {
		return password;
	}
	
	/***
	 * Mutator method for the password instance variable. 
	 * @param password The parameter to be assigned to password. 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/***
	 * Overridden toString() that returns the object contents as a String. 
	 */
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	/***
	 * Overridden hashCode() method for creating a hashcode based on object parameters. 
	 */
	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 13 * result + (getUsername() != null ? getUsername().hashCode() : 0);
		result = 13 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		return result;
	}
	
	/***
	 * Overridden equals() method for conducting object comparisons. 
	 */
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
