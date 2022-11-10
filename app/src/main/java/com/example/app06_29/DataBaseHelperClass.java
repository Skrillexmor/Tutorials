package com.example.app06_29;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelperClass extends SQLiteOpenHelper {
    private static final String dbname = "tutorial7";
    private static final int version = 1;

    public DataBaseHelperClass(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table users (id integer primary key autoincrement, fname text,lname text,email text,pass text,branch text,gender text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public boolean checkEmail(String tt7_email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users where email = ?", new String[]{tt7_email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addUser(tt7UserHelperClass tt7UserHelperClass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fname", tt7UserHelperClass.getFname());
        values.put("lname", tt7UserHelperClass.getLname());
        values.put("email", tt7UserHelperClass.getEmail());
        values.put("pass", tt7UserHelperClass.getPass());
        values.put("branch", tt7UserHelperClass.getBranch());
        values.put("gender", tt7UserHelperClass.getGender());
        long r = db.insert("users", null, values);
        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLogin(String tt7_email, String tt7_pass) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users where email = ? and pass = ?", new String[]{tt7_email, tt7_pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<tt7UserHelperClass> getUsers() {
        String sql = "select * from users";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<tt7UserHelperClass> readUsers = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String email = cursor.getString(3);
                String pass = cursor.getString(4);
                String branch = cursor.getString(5);
                String gender = cursor.getString(6);
                readUsers.add(new tt7UserHelperClass(id, fname, lname, email, pass, branch, gender));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return readUsers;
    }
}
