package com.example.hays;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseOperation extends SQLiteOpenHelper {
    public static final String DATABASE= "operations.db";

    //First table
    public static final String Table1= "instructor";
    public static final String attribute1_1= "id_instructor";
    public static final String attribute1_2= "firstname_instructor";
    public static final String attribute1_3= "middlename_instructor";
    public static final String attribute1_4= "lastname_instructor";
    public static final String attribute1_5= "email_instructor";
    public static final String attribute1_6= "password_instructor";
    public static final String attribute1_7= "image_instructor";

    //Second Table
    public static final String Table2= "mayor";
    public static final String attribute2_1= "id_mayor";
    public static final String attribute2_2= "firstname_mayor";
    public static final String attribute2_3= "middlename_mayor";
    public static final String attribute2_4= "lastname_mayor";
    public static final String attribute2_5= "email_mayor";
    public static final String attribute2_6= "year_mayor";
    public static final String attribute2_7= "block_mayor";
    public static final String attribute2_8= "password_mayor";
    public static final String attribute2_9= "image_mayor";
    //Third Table
    public static final String Table3 ="admin";
    public static final String attribute3_1= "id_admin";
    public static final String attribute3_2= "firstname_admin";
    public static final String attribute3_3= "middlename_admin";
    public static final String attribute3_4= "lastname_admin";
    public static final String attribute3_5= "email_admin";
    public static final String attribute3_6= "password_admin";

    //Fourth Table
    public static final String Table4= "history";
    public static final String attribute4_1= "instructor_history";
    public static final String attribute4_2= "date_history";
    public static final String attribute4_3= "time_history";
    public static final String attribute4_4= "room_history";
    public static final String attribute4_5= "year_history";
    public static final String attribute4_6= "block_history";
    public static final String attribute4_7= "subject_history";
    //Fifth Table
    public static final String Table5= "rooms";
    public static final String attribute5_1= "ctas_comlabA";
    public static final String attribute5_2= "ctas_comlabB";
    public static final String attribute5_3= "ctas_comlabC";
    public static final String attribute5_4= "ctas_comlabD";
    //Sixth table
    public static final String Table6= "request";
    public static final String attribute6_1= "instructor_name";
    public static final String attribute6_2= "subject_name";
    public static final String attribute6_3= "year_level";
    public static final String attribute6_4= "block";
    public static final String attribute6_5= "time";
    public static final String attribute6_6= "room";

    public DatabaseOperation(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Table1( attribute1_1, attribute1_2, attribute1_3, attribute1_4, attribute1_5, attibute1_6, attribute1_7)");
        db.execSQL("CREATE TABLE Table2( attribute2_1, attribute2_2, attribute2_3, attribute2_4, attribute2_5, attribute2_6, attribute2_7, attribute2_8, attribute_9)");
        db.execSQL("CREATE TABLE Table3( attribute3_1, attribute3_2, attribute3_3, attribute3_4, attribute3_5, attribute3_6)");
        db.execSQL("CREATE TABLE Table4( attribute4_1, attribute4_2, attribute4_3, attribute4_4, attribute4_5, attribute4_6, attribute4_7)");
        db.execSQL("CREATE TABLE Table5( attribute5_1, attribute5_2, attribute5_3, attribute5_4)");
        db.execSQL("CREATE TABLE Table6( attribute6_1, attribute6_2, attribute6_3, attribute6_4, attribute6_5, attribute6_6)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table1);
        db.execSQL("DROP TABLE IF EXISTS "+ Table2);
        db.execSQL("DROP TABLE IF EXISTS "+ Table3);
        db.execSQL("DROP TABLE IF EXISTS "+ Table4);
        db.execSQL("DROP TABLE IF EXISTS "+ Table5);
        db.execSQL("DROP TABLE IF EXISTS "+ Table6);
        onCreate(db);
    }

//GET ID
    @SuppressLint("Range")
    public String getId (String email_instructor, String password_instructor){
    SQLiteDatabase db=this.getReadableDatabase();
    String rv="instructor doesn't exist";
    String column="id_instructor";
    Cursor res=db.rawQuery("SELECT email_instructor FROM Table1 WHERE email_instructor=? and password_instructor=?",new String[]{email_instructor,password_instructor});
    if(res.moveToFirst()){
        rv=res.getString(res.getColumnIndex(column));
    }else{
        System.out.println("hays");
    }
    return rv;
    }
    @SuppressLint("Range")
    public String getId (){
        SQLiteDatabase db=this.getReadableDatabase();
        String rv="instructor doesn't exist";
        String column="customer_id";
        Cursor res=db.rawQuery("SELECT a FROM b WHERE c=? and d=?",new String[]{c,d});
        if(res.moveToFirst()){
            rv=res.getString(res.getColumnIndex(column));
        }else{
            System.out.println("hays");
        }
        return rv;
    }
//GET CONTENT
    @SuppressLint("Range")
    public String getContent(int id, String column, String table, String whereclause){
    SQLiteDatabase db=this.getReadableDatabase();
    String rv="column doesn't exist";
    String[] whereargs=new String[]{String.valueOf(id)};
    Cursor res=db.query(table,null,whereclause, whereargs, null, null, null);
    if(res.moveToFirst()){
        rv=res.getString(res.getColumnIndex(column));
    }else{
        System.out.println();
    }
    return rv;
    }

    public boolean insertInstructor(String i_firstname, String i_middlename, String i_lastname){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", i_firstname);
        cv.put("c_lastname", i_middlename);
        cv.put("c_course", i_lastname);

        db.insert("instructor", null, cv);
        return true;
    }

    public boolean updateCustomer(int instructor_id, String c_firstname, String c_lastname, String c_course, String c_email, String c_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("c_course", c_course);
        cv.put("c_email", c_email);
        cv.put("c_password", c_password);
        db.update("instructor",cv,"instructor_id=?", new String[]{Integer.toString(instructor_id)});
        return true;
    }

    public boolean deleteCustomer(int customer_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("instructor", "instructor_id=?",new String[]{Integer.toString(instructor_id)});
        return true;
    }

    public ArrayList<String> getAllInstructor(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getInstructor= new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM instructor", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getInstructor.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5));
            res.moveToNext();
        }
        return getInstructor;
    }
}
