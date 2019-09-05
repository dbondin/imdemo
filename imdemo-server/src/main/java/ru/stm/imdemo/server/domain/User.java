package ru.stm.imdemo.server.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность пользователя, в базе данных сохраняется в табличке "IM_USER"
 * Реализованы такие поля как: id, username, password, active, sub
 */

@Entity
@Table(name = "IM_USER")
public class User {

	@Id
	@Column(name = "IM_USER_ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false, unique = false)
	private String password;

	@Column(name = "ACTIVE", nullable = false, unique = false)
	private boolean active;
	
	@Column(name = "SUB", nullable = false, unique = false)
	private String sub;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "IM_USER_ROLE",
			joinColumns = @JoinColumn(name = "IM_USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "IM_ROLE_ID"))
	private Set<Role> roles = new HashSet<Role>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return new HashSet<Role>(roles);
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getSub() {
		return sub;
	}
	
	public void setSub(String sub) {
		this.sub = sub;
	}
}
