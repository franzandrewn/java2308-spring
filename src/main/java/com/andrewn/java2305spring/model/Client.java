package com.andrewn.java2305spring.model;

public class Client {
    static long count = 0;
    long id;
    String login;
    String password;

    public Client(String login, String password) {
        count++;
        this.id = count;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static long getCount() {
        return count;
    }

    public static void setCount(long count) {
        Client.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
