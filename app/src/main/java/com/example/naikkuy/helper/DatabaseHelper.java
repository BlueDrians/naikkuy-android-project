package com.example.naikkuy.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.naikkuy.model.BookingModel;
import com.example.naikkuy.model.UserModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "naikkuy.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_BOOKINGS = "bookings";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, " +
                "phone TEXT NOT NULL)";

        String createBookings = "CREATE TABLE " + TABLE_BOOKINGS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "passenger_name TEXT NOT NULL, " +
                "transport TEXT NOT NULL, " +
                "destination TEXT NOT NULL, " +
                "date TEXT NOT NULL, " +
                "ticket_count INTEGER NOT NULL, " +
                "total_price INTEGER NOT NULL)";

        db.execSQL(createUsers);
        db.execSQL(createBookings);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    public boolean registerUser(String name, String username, String password, String phone) {
        if (isUsernameExists(username)) {
            return false;
        }

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("username", username);
        values.put("password", password);
        values.put("phone", phone);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM " + TABLE_USERS + " WHERE username = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id FROM " + TABLE_USERS + " WHERE username = ? AND password = ?",
                new String[]{username, password}
        );
        boolean valid = cursor.getCount() > 0;
        cursor.close();
        return valid;
    }

    public UserModel getUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT name, username, phone FROM " + TABLE_USERS + " WHERE username = ?",
                new String[]{username}
        );

        UserModel user = null;
        if (cursor.moveToFirst()) {
            user = new UserModel(
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone"))
            );
        }
        cursor.close();
        return user;
    }

    public boolean addBooking(String username, String passengerName, String transport, String destination, String date, int ticketCount, int totalPrice) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("passenger_name", passengerName);
        values.put("transport", transport);
        values.put("destination", destination);
        values.put("date", date);
        values.put("ticket_count", ticketCount);
        values.put("total_price", totalPrice);

        long result = db.insert(TABLE_BOOKINGS, null, values);
        return result != -1;
    }

    public ArrayList<BookingModel> getBookings(String username) {
        ArrayList<BookingModel> bookings = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_BOOKINGS + " WHERE username = ? ORDER BY id DESC",
                new String[]{username}
        );

        if (cursor.moveToFirst()) {
            do {
                bookings.add(new BookingModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("passenger_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("transport")),
                        cursor.getString(cursor.getColumnIndexOrThrow("destination")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("ticket_count")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("total_price"))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return bookings;
    }

    public boolean deleteBooking(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_BOOKINGS, "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public void deleteAllBookings(String username) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BOOKINGS, "username = ?", new String[]{username});
    }
}
