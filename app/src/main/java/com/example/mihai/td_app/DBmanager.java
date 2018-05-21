package com.example.mihai.td_app;
import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBmanager extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="parking.db";
    private static final int DATABASE_VERSION = 1;
    // for "users" table
    private static final String USERS_TABLE_NAME="Users";
    private static final String USERS_COLUMN_ID="user_id"; // PK
    private static final String USERS_COLUMN_USERNAME="username";
    private static final String USERS_COLUMN_PASS="password";
    private static final String USERS_COLUMN_BANK_ACCOUNT="bank_account";
    private static final String USERS_COLUMN_CAR_NUMBER="car_number";
    // for "stations" table
    private static final String STATIONS_TABLE_NAME="Stations";
    private static final String STATIONS_COLUMN_ID="station_id"; // PK
    private static final String STATIONS_COLUMN_AVAILABLE="available";
    private static final String STATIONS_COLUMN_OCCUPIED="occupied";
    private static final String STATIONS_COLUMN_RESERVED="reserved";
    // for "transactions" table
    private static final String TRANSACTIONS_TABLE_NAME="Transactions";
    private static final String TRANSACTIONS_COLUMN_ID="transaction_id"; // PK
    private static final String TRANSACTIONS_COLUMN_ACCOUNT="bank_account"; // FK from users
    private static final String TRANSACTIONS_COLUMN_CAR_NUMBER="car_number"; // FK from users
    private static final String TRANSACTIONS_COLUMN_STATION_ID="station_id"; // FK from stations
    private static final String TRANSACTIONS_COLUMN_PRICE="price";
    private static final String TRANSACTIONS_COLUMN_DATE="at_date";

    public DBmanager(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    private static final String CREATE_TABLE_USERS ="create table if not exists "+ USERS_TABLE_NAME+
            " ( "+USERS_COLUMN_ID +" integer primary key not null, "+ USERS_COLUMN_USERNAME+" text, "+
            USERS_COLUMN_PASS+" text not null,"+ USERS_COLUMN_BANK_ACCOUNT+" text not null,"+
            USERS_COLUMN_CAR_NUMBER+" text not null)";
    private static final String CREATE_TABLE_STATIONS="create table if not exists "+STATIONS_TABLE_NAME+
            " ( "+STATIONS_COLUMN_ID+" integer primary key, " +STATIONS_COLUMN_AVAILABLE+" integer not null,"+
            STATIONS_COLUMN_OCCUPIED +"integer not null, " +STATIONS_COLUMN_RESERVED +" integer not null)";
    private static final String CREATE_TABLE_TRANSACTIONS="create table if not exists "+TRANSACTIONS_TABLE_NAME+
            " ( "+TRANSACTIONS_COLUMN_ID+" integer primary key, "+TRANSACTIONS_COLUMN_ACCOUNT+" text not null, "+
            TRANSACTIONS_COLUMN_CAR_NUMBER+" not null, "+ TRANSACTIONS_COLUMN_STATION_ID+" integer not null, "+
            TRANSACTIONS_COLUMN_PRICE +"float not null, "+TRANSACTIONS_COLUMN_DATE+" text not null,"+
            " foreign key (bank_account) references Users(bank_account), foreign key (car_number) references Users(car_number),"+
            " foreign key(station_id) references Stations(station_id))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table users
            db.execSQL(CREATE_TABLE_USERS);
        // create table stations
            db.execSQL(CREATE_TABLE_STATIONS);
        // create table transactions
        db.execSQL(CREATE_TABLE_TRANSACTIONS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("drop table if exists "+ USERS_TABLE_NAME);
        db.execSQL("drop table if exists " + STATIONS_TABLE_NAME);
        db.execSQL("drop table if exists " + TRANSACTIONS_TABLE_NAME);

        //create new tables
        onCreate(db);
    }

    // create user
    public long createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values  = new ContentValues();

        values.put(USERS_COLUMN_ID,user.getUser_id());
        values.put(USERS_COLUMN_USERNAME,user.getUsername());
        values.put(USERS_COLUMN_PASS,user.getPassword());
        values.put(USERS_COLUMN_BANK_ACCOUNT,user.getBank_account());
        values.put(USERS_COLUMN_CAR_NUMBER,user.getCar_number());

        return db.insert(USERS_TABLE_NAME,null,values);
    }

    // create station
    public long createStation(Station station){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STATIONS_COLUMN_ID,station.getStation_id());
        values.put(STATIONS_COLUMN_AVAILABLE,station.getAvailable());
        values.put(STATIONS_COLUMN_OCCUPIED,station.getOccupied());
        values.put(STATIONS_COLUMN_RESERVED,station.getReserved());

        return db.insert(STATIONS_TABLE_NAME, null, values);
    }

    // create transaction
    public long createTransaction(Transaction transaction){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRANSACTIONS_COLUMN_ID,transaction.getTransaction_id());
        values.put(TRANSACTIONS_COLUMN_ACCOUNT,transaction.getBank_account());
        values.put(TRANSACTIONS_COLUMN_CAR_NUMBER, transaction.getCar_number());
        values.put(TRANSACTIONS_COLUMN_STATION_ID,transaction.getStation_id());
        values.put(TRANSACTIONS_COLUMN_PRICE,transaction.getPrice());
        values.put(TRANSACTIONS_COLUMN_DATE,transaction.getAt_date());

        return db.insert(TRANSACTIONS_TABLE_NAME,null,values);
    }
}
