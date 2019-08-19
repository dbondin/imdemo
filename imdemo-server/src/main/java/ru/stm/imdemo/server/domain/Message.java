package ru.stm.imdemo.server.domain;

import javax.persistence.*;

@Entity
@Table(name = "IM_MESSAGE")
public class Message {
	
	@Id
	@Column(name = "IM_MESSAGE_ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "TEXT", nullable = false, unique = false)
    private String text;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_IM_USER_ID")
    private User fromUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_IM_USER_ID")
    private User toUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
}
