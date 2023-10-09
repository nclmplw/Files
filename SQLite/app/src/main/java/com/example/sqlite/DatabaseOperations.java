package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final String DATABASENAME="Sales.db";
    public static final String TABLENAME="Orders";
    public static final String first="firstn";
    public static final String middle="middlen";
    public static final String last="lastn";
    public static final String item="itemn";
    public static final String quan="quantity";
    public static final String price="itemprc";
    public static final String date="orderdate";
    public static final String ttlpybl="totalpayable";
    public DatabaseOperations(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE Orders (id integer primary key, firstn text, mddlen text, lastn text, itemn text, quantity integer, itemprc double, orderdate text, totalpayable double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);

    onCreate(db);
    }

    public Cursor getData(int id){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT* FROM Orders WHERE id="+id,null);
        return res;
    }

    public boolean insertOrders(String firstn, String middlen, String lastn, String itemn, int quantity, double itemprc, String orderdate, double totalpayable) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("firstn", firstn);
        cv.put("middlen", middlen);
        cv.put("lastn", lastn);
        cv.put("itemn", itemn);
        cv.put("quantity", quantity);
        cv.put("itemprc", itemprc);
        cv.put("orderdate", orderdate);
        cv.put("totalpayable", totalpayable);
        db.insert("Orders",null,cv);
        return true;
    }
    public boolean updateOrders(int id, String firstn, String middlen, String lastn, String itemn, int quantity, double itemprc, String orderdate, double totalpayable){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("firstn", firstn);
        cv.put("middlen", middlen);
        cv.put("lastn", lastn);
        cv.put("itemn", itemn);
        cv.put("quantity", quantity);
        cv.put("itemprc", itemprc);
        cv.put("orderdate", orderdate);
        cv.put("totalpayable", totalpayable);
        db.update("Orders",cv,"id=?",new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteOrders(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("Orders","id=?",new String[]{Integer.toString(id)});
        return true;
    }

    public int numOfRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int numRows=(int) DatabaseUtils.queryNumEntries(db, TABLENAME);
        return numRows;
    }

    public ArrayList<String> getAllOrders(){
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<String> getOrders= new ArrayList<String>();
        Cursor res= db.rawQuery("SELECT * FROM Orders",null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            getOrders.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5)+"-"+res.getString(6)+"-"+res.getString(7)+"-"+res.getString(8));
        res.moveToNext();
        }
        return getOrders;
    }
}
