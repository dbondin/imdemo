package ru.stm.imdemo.server.dto;

import ru.stm.imdemo.server.domain.User;

public class SendMessageBody {
   private String text;
   private User user_id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
}
