package com.example.mihai.td_app;

public class Transaction {
    private int transaction_id;
    private String bank_account;
    private String car_number;
    private String station_id;
    private int price;
    private int at_date;

    public Transaction(){};

    public Transaction(String bank_account, String car_number, String station_id, int price, int at_date) {
        this.bank_account = bank_account;
        this.car_number = car_number;
        this.station_id = station_id;
        this.price = price;
        this.at_date = at_date;
    }

    public Transaction(int transaction_id, String bank_account, String car_number, String station_id, int price, int at_date) {
        this.transaction_id = transaction_id;
        this.bank_account = bank_account;
        this.car_number = car_number;
        this.station_id = station_id;
        this.price = price;
        this.at_date = at_date;
    }

    // setters

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAt_date(int at_date) {
        this.at_date = at_date;
    }

    //getters

    public int getTransaction_id() {
        return transaction_id;
    }

    public String getBank_account() {
        return bank_account;
    }

    public String getCar_number() {
        return car_number;
    }

    public String getStation_id() {
        return station_id;
    }

    public int getPrice() {
        return price;
    }

    public int getAt_date() {
        return at_date;
    }
}
