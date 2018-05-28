package com.example.mihai.td_app;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.TransitionDrawable;
import android.net.TrafficStats;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DBmanager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "parking.db";
    private static final int DATABASE_VERSION = 1;
    // for "users" table
    private static final String USERS_TABLE_NAME = "Users";
    private static final String USERS_COLUMN_ID = "user_id"; // PK
    private static final String USERS_COLUMN_USERNAME = "username";
    private static final String USERS_COLUMN_PASS = "password";
    private static final String USERS_COLUMN_BANK_ACCOUNT = "bank_account";
    private static final String USERS_COLUMN_CAR_NUMBER = "car_number";
    // for "stations" table
    private static final String STATIONS_TABLE_NAME = "Stations";
    private static final String STATIONS_COLUMN_ID = "station_id"; // PK
    private static final String STATIONS_COLUMN_AVAILABLE = "available";
    private static final String STATIONS_COLUMN_OCCUPIED = "occupied";
    private static final String STATIONS_COLUMN_RESERVED = "reserved";
    // for "transactions" table
    private static final String TRANSACTIONS_TABLE_NAME = "Transactions";
    private static final String TRANSACTIONS_COLUMN_ID = "transaction_id"; // PK
    private static final String TRANSACTIONS_COLUMN_ACCOUNT = "bank_account"; // FK from users
    private static final String TRANSACTIONS_COLUMN_CAR_NUMBER = "car_number"; // FK from users
    private static final String TRANSACTIONS_COLUMN_STATION_ID = "station_id"; // FK from stations
    private static final String TRANSACTIONS_COLUMN_PRICE = "price";
    private static final String TRANSACTIONS_COLUMN_DATE = "at_date";

    public DBmanager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_USERS = "create table " + USERS_TABLE_NAME +
            " ( " + USERS_COLUMN_ID + " integer primary key, " + USERS_COLUMN_USERNAME + " text, " +
            USERS_COLUMN_PASS + " text not null," + USERS_COLUMN_BANK_ACCOUNT + " text not null," +
            USERS_COLUMN_CAR_NUMBER + " text not null)";
    private static final String CREATE_TABLE_STATIONS = "create table " + STATIONS_TABLE_NAME +
            " ( " + STATIONS_COLUMN_ID + " integer primary key, " + STATIONS_COLUMN_AVAILABLE + " integer not null," +
            STATIONS_COLUMN_OCCUPIED + "integer not null, " + STATIONS_COLUMN_RESERVED + " integer not null)";
    private static final String CREATE_TABLE_TRANSACTIONS = "create table " + TRANSACTIONS_TABLE_NAME +
            " ( " + TRANSACTIONS_COLUMN_ID + " integer primary key, " + TRANSACTIONS_COLUMN_ACCOUNT + " text not null, " +
            TRANSACTIONS_COLUMN_CAR_NUMBER + " not null, " + TRANSACTIONS_COLUMN_STATION_ID + " integer not null, " +
            TRANSACTIONS_COLUMN_PRICE + "float not null, " + TRANSACTIONS_COLUMN_DATE + " text not null," +
            " foreign key (bank_account) references Users(bank_account), foreign key (car_number) references Users(car_number)," +
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
        db.execSQL("drop table if exists " + USERS_TABLE_NAME);
        db.execSQL("drop table if exists " + STATIONS_TABLE_NAME);
        db.execSQL("drop table if exists " + TRANSACTIONS_TABLE_NAME);

        //create new tables
        onCreate(db);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    // create user
    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(USERS_COLUMN_ID, user.getUser_id());
        values.put(USERS_COLUMN_USERNAME, user.getUsername());
        values.put(USERS_COLUMN_PASS, user.getPassword());
        values.put(USERS_COLUMN_BANK_ACCOUNT, user.getBank_account());
        values.put(USERS_COLUMN_CAR_NUMBER, user.getCar_number());
        long user_id = db.insert(USERS_TABLE_NAME, null, values);
        return user_id;
    }

    // create station
    public long createStation(Station station) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STATIONS_COLUMN_ID, station.getStation_id());
        values.put(STATIONS_COLUMN_AVAILABLE, station.getAvailable());
        values.put(STATIONS_COLUMN_OCCUPIED, station.getOccupied());
        values.put(STATIONS_COLUMN_RESERVED, station.getReserved());

        return db.insert(STATIONS_TABLE_NAME, null, values);
    }

    // create transaction
    public long createTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRANSACTIONS_COLUMN_ID, transaction.getTransaction_id());
        values.put(TRANSACTIONS_COLUMN_ACCOUNT, transaction.getBank_account());
        values.put(TRANSACTIONS_COLUMN_CAR_NUMBER, transaction.getCar_number());
        values.put(TRANSACTIONS_COLUMN_STATION_ID, transaction.getStation_id());
        values.put(TRANSACTIONS_COLUMN_PRICE, transaction.getPrice());
        values.put(TRANSACTIONS_COLUMN_DATE, transaction.getAt_date());

        return db.insert(TRANSACTIONS_TABLE_NAME, null, values);
    }

    // get user via username
    public User getUserViaUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "Select * from " + USERS_TABLE_NAME + " where " + USERS_COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        else return null;

        User user = new User();
        user.setUser_id(c.getInt(c.getColumnIndex(USERS_COLUMN_ID)));
        user.setUsername(c.getString(c.getColumnIndex(USERS_COLUMN_USERNAME)));
        user.setPassword(c.getString(c.getColumnIndex(USERS_COLUMN_PASS)));
        user.setBank_account(c.getString(c.getColumnIndex(USERS_COLUMN_BANK_ACCOUNT)));
        user.setCar_number(c.getString(c.getColumnIndex(USERS_COLUMN_CAR_NUMBER)));
        return user;
    }

    // get station via ID
    public Station getStaion(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + STATIONS_TABLE_NAME + " WHERE " + STATIONS_COLUMN_ID + " = " + id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Station s = new Station();
        s.setStation_id(c.getInt(c.getColumnIndex(STATIONS_COLUMN_ID)));
        s.setAvailable(c.getInt(c.getColumnIndex(STATIONS_COLUMN_AVAILABLE)));
        s.setOccupied(c.getInt(c.getColumnIndex(STATIONS_COLUMN_OCCUPIED)));
        s.setReserved(c.getInt(c.getColumnIndex(STATIONS_COLUMN_RESERVED)));

        return s;

    }

    // get transaction via ID
    public Transaction getTransaction(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TRANSACTIONS_TABLE_NAME + " WHERE " + TRANSACTIONS_COLUMN_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Transaction t = new Transaction();
        t.setTransaction_id(c.getInt(c.getColumnIndex(TRANSACTIONS_COLUMN_ID)));
        t.setStation_id(c.getInt(c.getColumnIndex(TRANSACTIONS_COLUMN_STATION_ID)));
        t.setBank_account(c.getString(c.getColumnIndex(TRANSACTIONS_COLUMN_ACCOUNT)));
        t.setCar_number(c.getString(c.getColumnIndex(TRANSACTIONS_COLUMN_CAR_NUMBER)));
        t.setPrice(c.getFloat(c.getColumnIndex(TRANSACTIONS_COLUMN_PRICE)));
        t.setAt_date(c.getString(c.getColumnIndex(TRANSACTIONS_COLUMN_DATE)));

        return t;
    }

    // update a user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_USERNAME, user.getUsername());
        values.put(USERS_COLUMN_PASS, user.getPassword());
        values.put(USERS_COLUMN_BANK_ACCOUNT, user.getBank_account());
        values.put(USERS_COLUMN_CAR_NUMBER, user.getCar_number());

        return db.update(USERS_TABLE_NAME, values, USERS_COLUMN_ID + " = ? ",
                new String[]{String.valueOf(user.getUser_id())});
    }

    // update a station
    public int updateStation(Station station) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STATIONS_COLUMN_ID, station.getStation_id());
        values.put(STATIONS_COLUMN_AVAILABLE, station.getAvailable());
        values.put(STATIONS_COLUMN_OCCUPIED, station.getOccupied());
        values.put(STATIONS_COLUMN_RESERVED, station.getReserved());

        return db.update(STATIONS_TABLE_NAME, values, STATIONS_COLUMN_ID + " = ? ",
                new String[]{String.valueOf(station.getStation_id())});
    }

    // update a transaction
    public int updateTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TRANSACTIONS_COLUMN_ID, transaction.getTransaction_id());
        values.put(TRANSACTIONS_COLUMN_ACCOUNT, transaction.getBank_account());
        values.put(TRANSACTIONS_COLUMN_CAR_NUMBER, transaction.getCar_number());
        values.put(TRANSACTIONS_COLUMN_STATION_ID, transaction.getStation_id());
        values.put(TRANSACTIONS_COLUMN_PRICE, transaction.getPrice());
        values.put(TRANSACTIONS_COLUMN_DATE, transaction.getAt_date());

        return db.update(TRANSACTIONS_TABLE_NAME, values, TRANSACTIONS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(transaction.getTransaction_id())});
    }

    // delete a user searched by username
    public void deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS_TABLE_NAME, USERS_COLUMN_USERNAME + " = ? ", new String[]{username});
    }

    // delete a station
    public void deleteStaion(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STATIONS_TABLE_NAME, STATIONS_COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)});
    }

    // delete a transaction
    public void deleteTransaction(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TRANSACTIONS_TABLE_NAME,TRANSACTIONS_COLUMN_ID+" = ? ",
                new String[]{String.valueOf(id)});
    }

    // get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String selectQuery = "Select * from " + USERS_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                User u = new User();
                u.setUser_id(c.getInt(c.getColumnIndex(USERS_COLUMN_ID)));
                u.setUsername(c.getString(c.getColumnIndex(USERS_COLUMN_USERNAME)));
                u.setPassword(c.getString(c.getColumnIndex(USERS_COLUMN_PASS)));
                u.setBank_account(c.getString(c.getColumnIndex(USERS_COLUMN_BANK_ACCOUNT)));
                u.setCar_number(c.getString(c.getColumnIndex(USERS_COLUMN_CAR_NUMBER)));

                users.add(u);
            } while (c.moveToNext());
        }
        return users;
    }

    // all stations
    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<Station>();
        String selectQuery = "SELECT * FROM " + STATIONS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Station s = new Station();
                s.setStation_id(c.getInt(c.getColumnIndex(STATIONS_COLUMN_ID)));
                s.setAvailable(c.getInt(c.getColumnIndex(STATIONS_COLUMN_AVAILABLE)));
                s.setOccupied(c.getInt(c.getColumnIndex(STATIONS_COLUMN_OCCUPIED)));
                s.setReserved(c.getInt(c.getColumnIndex(STATIONS_COLUMN_RESERVED)));

                stations.add(s);
            } while (c.moveToNext());
        }
        return stations;
    }

    // all transactions
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String selectQuery = "SELECT * FROM " + TRANSACTIONS_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Transaction t = new Transaction();
                t.setTransaction_id(c.getInt(c.getColumnIndex(TRANSACTIONS_COLUMN_ID)));
                t.setStation_id(c.getInt(c.getColumnIndex(TRANSACTIONS_COLUMN_STATION_ID)));
                t.setBank_account(c.getString(c.getColumnIndex(TRANSACTIONS_COLUMN_ACCOUNT)));
                t.setCar_number(c.getString(c.getColumnIndex(TRANSACTIONS_COLUMN_CAR_NUMBER)));
                t.setPrice(c.getFloat(c.getColumnIndex(TRANSACTIONS_COLUMN_PRICE)));
                t.setAt_date(c.getString(c.getColumnIndex(TRANSACTIONS_COLUMN_DATE)));
                transactions.add(t);
            } while (c.moveToNext());
        }
        return transactions;

    }


}
