package ru.stm.imdemo.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность Role
 * Существует в таблице "IM_ROLE"
 * Реализованы такие поля как: id, name, admin
 */
@Entity
@Table(name = "IM_ROLE")
public class Role {

	@Id
    @Column(name = "IM_ROLE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	
	@Column(name = "ADMIN", nullable = false, unique = false)
	private boolean admin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
