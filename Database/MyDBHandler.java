package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "studentDB.db";
    private static final String TABLE_STUDENT = "newstudent";
    private static final String COLUMN_RNO = "rno";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_ADDRESS = "address";

    MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "(" + COLUMN_RNO + " INTEGER PRIMARY KEY, " + COLUMN_NAME
                + " TEXT, " + COLUMN_EMAIL + " TEXT, " +
                COLUMN_COURSE + " TEXT, " + COLUMN_CONTACT + " INTEGER, " + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    //load data
    String loadHandler() {
        StringBuilder result = new StringBuilder();
        String query = "Select * From " + TABLE_STUDENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            int result_4 = cursor.getInt(4);
            String result_5 = cursor.getString(5);
            result.append(result_0).append(" ").append(result_1).append(" ").append(result_2).append(" ").append(result_3).append(" ").append(result_4).append(" ").append(result_5); //append("\n");
            System.lineSeparator();
        }
        cursor.close();
        db.close();
        if (result.toString().isEmpty())
            result = new StringBuilder("No Records Found");
        return result.toString();
    }

    //add data
    long addHandler(Student student) {
        long id;
        ContentValues values = new ContentValues();
        values.put(COLUMN_RNO, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_EMAIL, student.getEmail());
        values.put(COLUMN_COURSE, student.getCourse());
        values.put(COLUMN_CONTACT, student.getContact());
        values.put(COLUMN_ADDRESS, student.getAddress());
        SQLiteDatabase db = this.getWritableDatabase();
        id = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return id;
    }

    //update handler
    boolean updateHandler(int rno, String name, String email, String course, int contact, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_RNO, rno);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_EMAIL, email);
        args.put(COLUMN_COURSE, course);
        args.put(COLUMN_CONTACT, contact);
        args.put(COLUMN_ADDRESS, address);
        return db.update(TABLE_STUDENT, args, COLUMN_RNO + "=" +rno, null) > 0;
    }
    //delete handler
    boolean deleteHandler(int rno) {
        boolean result = false;
        String query = "Select * From " + TABLE_STUDENT + " Where " + COLUMN_RNO + "='" + rno + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENT, COLUMN_RNO + "=?" , new String[]{String.valueOf(student.getId())});
            cursor.close();
            result = true;
        }
        return result;
    }
}
