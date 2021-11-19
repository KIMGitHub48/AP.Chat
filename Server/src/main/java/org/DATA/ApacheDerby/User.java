package org.DATA.ApacheDerby;

public class User {

    private int id;

    private String fio;

    private String post;

    private String login;

    private String password;

    public User(String fio, String post, String login, String password) {
        this.fio = fio;
        this.post = post;
        this.login = login;
        this.password = password;
    }


    public String getFio() {
        return fio;
    }


    public String getPost() {
        return post;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
