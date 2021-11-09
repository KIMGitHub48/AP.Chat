package org.Domain.Net;

import java.io.Serializable;

public class Message implements Serializable {
    private String id;
    private String login;
    private String password;
    private String chatChannelText;
    private String chatChannelName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChatChannelText() {
        return chatChannelText;
    }

    public void setChatChannelText(String chatChannelText) {
        this.chatChannelText = chatChannelText;
    }

    public String getChatChannelName() {
        return chatChannelName;
    }

    public void setChatChannelName(String chatChannelName) {
        this.chatChannelName = chatChannelName;
    }
}
