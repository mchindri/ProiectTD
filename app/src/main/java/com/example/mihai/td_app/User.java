package com.example.mihai.td_app;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String bank_account;
    private String car_number;

    public User(){}

    public User(String username, String password, String bank_account, String car_number) {
        this.username = username;
        this.password = password;
        this.bank_account = bank_account;
        this.car_number = car_number;
    }

    public User(int user_id, String username, String password, String bank_account, String car_number) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.bank_account = bank_account;
        this.car_number = car_number;
    }
    // setters
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    // getters

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBank_account() {
        return bank_account;
    }

    public String getCar_number() {
        return car_number;
    }

    public String toString()
    {
        return username + " have car " + car_number;
    }
}
