package com.example.queennestoystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final String DATABASE = "queennes.db";
    //table1 qcustomer, c_firstname, c_lastname, c_age, c_phonenum, c_address, c_email, c_username, c_password
    public static final String TABLE1 = "qcustomer";
    public static final String  QFirstname ="c_firstname";
    public static final String QLastname = "c_lastname";
    public static final String QAge = "c_age";
    public static final String QPhone = "c_phonenum";
    public static final String QAddress = "c_address";
    public static final String QEmail = "c_email";
    public static final String QUsername = "c_username";
    public static final String QPassword = "c_password";
    //table2 stock_id, product_id, qprice, qshelf, qnumstock, qnumsold
    public static final String TABLE2 = "qstock";
    public static final String QPrice = "qprice";
    public static final String QShelf ="qshelf";
    public static final String QStock = "qnumstock";
    public static final String QSold = "qnumsold";
    //table3 sale_id, stock_id, product_id, qamntsold, qnum_sold
    public static final String TABLE3 = "qsales";
    public static final String QAmntsold = "qamntsold";
    public static final String Q_Sold = "qnum_sold";
    //table4 purchase_id, customer_id, q_product, qquantity, qdate, product_id, category_id
    public static final String TABLE4 = "qpurchase";
    public static final String Q_Product = "q_product";
    public static final String QQuantity = "qquantity";
    public static final String QDate = "qdate";
    //table5 product_id, qproductname, q_price, qbrand, qpquantity, q_category
    public static final String TABLE5 = "qproduct";
    public static final String QProductname = "qproductname";
    public static final String Q_Price ="q_price";
    public static final String QBrand = "qbrand";
    public static final String QPQuantity = "qpquantity";
    public static final String Q_Category = "q_category";
    //table6 category_id, qcategory, qnumproduct
    public static final String TABLE6 = "qpcategory";
    public static final String QCategory = "qcategory";
    public static final String QNumproduct= "qnumproduct";
    public DatabaseOperations(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE TABLE1 (customer_id integer primary key, c_firstname text, c_lastname text, c_age integer, c_phonenum integer, c_address text, c_email text, c_username text, c_password text)");
    db.execSQL("CREATE TABLE TABLE2 (stock_id integer primary key, product_id integer, qprice double, qshelf integer, qnumstock integer, qnumsold integer)");
    db.execSQL("CREATE TABLE TABLE3 (sale_id integer primary key, stock_id integer, product_id integer, qamntsold integer, qnum_sold integer)");
    db.execSQL("CREATE TABLE TABLE4 (purchase_id integer primary key, customer_id integer, q_product text, qquantity integer, qdate text, product_id integer, category_id integer)");
    db.execSQL("CREATE TABLE TABLE5 (product_id integer primary key, qproductname text, q_price double, qbrand text, qpquantity integer, q_category text)");
    db.execSQL("CREATE TABLE TABLE6 (category_id integer primary key, qcategory text, qnumproduct integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE1);
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE2);
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE3);
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE4);
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE5);
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE6);
        onCreate(db);
    }

//TABLE1  customer_id, c_firstname, c_lastname, c_age, c_phonenum, c_address, c_email, c_username, c_password

    public Cursor getCustomerData (int customer_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM TABLE1 WHERE customer_id="+customer_id,null);
        return res;
    }

    public boolean insertCustomer(String c_firstname, String c_lastname, String c_age, int c_phonenum, String c_address, String c_email, String c_username, String c_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("c_age", c_age);
        cv.put("c_phonenum", c_phonenum);
        cv.put("c_address", c_address);
        cv.put("c_email", c_email);
        cv.put("c_username", c_username);
        cv.put("c_password", c_password);
        db.insert("TABLE1", null, cv);
        return true;
    }

    public boolean updateCustomer(int customer_id, String c_firstname, String c_lastname, String c_age, int c_phonenum, String c_address, String c_email, String c_username, String c_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("c_age", c_age);
        cv.put("c_phonenum", c_phonenum);
        cv.put("c_address", c_address);
        cv.put("c_email", c_email);
        cv.put("c_username", c_username);
        cv.put("c_password", c_password);
        db.update("TABLE1",cv,"customer_id=?", new String[]{Integer.toString(customer_id)});
        return true;
    }

    public boolean deleteCustomer(int customer_id){
    SQLiteDatabase db=this.getReadableDatabase();
    db.delete("TABLE1", "customer_id=?",new String[]{Integer.toString(customer_id)});
        return true;
    }

    public int numCustomerRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int CustomerRows=(int) DatabaseUtils.queryNumEntries(db, TABLE1);
        return CustomerRows;
    }

    public ArrayList<String> getAllCustomer(){
    SQLiteDatabase db = this.getReadableDatabase();
    ArrayList<String> getCustomer= new ArrayList<String>();
    Cursor res=db.rawQuery("SELECT * FROM TABLE1", null);
    res.moveToFirst();
    while (res.isAfterLast() == false){
        getCustomer.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5)+"-"+res.getString(6)+"-"+res.getString(7)+"-"+res.getString(8));
        res.moveToNext();
    }
    return getCustomer;
    }
//TABLE2 int stock_id, int product_id, double qprice, int qshelf, int qnumstock, int qnumsold

    public Cursor getStockData (int stock_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM TABLE2 WHERE stock_id="+stock_id,null);
        return res;
    }

    public boolean insertStock(int product_id, double qprice, int qshelf, int qnumstock, int qnumsold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("product_id", product_id);
        cv.put("qprice", qprice);
        cv.put("qshelf", qshelf);
        cv.put("qnumstock", qnumstock);
        cv.put("qnumsold", qnumsold);
        db.insert("TABLE2", null, cv);
        return true;
    }

    public boolean updateStock(int stock_id, int product_id, double qprice, int qshelf, int qnumstock, int qnumsold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qprice", qprice);
        cv.put("qshelf", qshelf);
        cv.put("qnumstock", qnumstock);
        cv.put("qnumsold", qnumsold);
        db.update("TABLE2",cv,"stock_id=?", new String[]{Integer.toString(stock_id)});
        return true;
    }

    public boolean deleteStock(int stock_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("TABLE2", "stock_id=?",new String[]{Integer.toString(stock_id)});
        return true;
    }

    public int numStockRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int StockRows=(int) DatabaseUtils.queryNumEntries(db, TABLE2);
        return StockRows;
    }

    public ArrayList<String> getAllStock(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getStock= new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM TABLE2", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getStock.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4));
            res.moveToNext();
        }
        return getStock;
    }

//TABLE3 sale_id integer primary key, stock_id integer, product_id integer, qamntsold integer, qnum_sold integer

    public Cursor getSalesData (int sale_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM TABLE3 WHERE sale_id="+sale_id,null);
        return res;
    }

    public boolean insertSales(int stock_id, int product_id, double qamntsold, int qnum_sold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("stock_id", stock_id);
        cv.put("product_id", product_id);
        cv.put("qamntsold", qamntsold);
        cv.put("qnum_sold", qnum_sold);
        db.insert("TABLE3", null, cv);
        return true;
    }

    public boolean updateSales(int sale_id, int stock_id, int product_id, double qamntsold, int qnum_sold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("stock_id", stock_id);
        cv.put("product_id", product_id);
        cv.put("qamntsold", qamntsold);
        cv.put("qnum_sold", qnum_sold);
        db.update("TABLE3",cv,"sale_id=?", new String[]{Integer.toString(sale_id)});
        return true;
    }

    public boolean deleteSales(int sale_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("TABLE3", "sale_id=?",new String[]{Integer.toString(sale_id)});
        return true;
    }

    public int numSalesRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int SalesRows=(int) DatabaseUtils.queryNumEntries(db, TABLE3);
        return SalesRows;
    }

    public ArrayList<String> getAllSales(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getSales= new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM TABLE3", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getSales.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4));
            res.moveToNext();
        }
        return getSales;
    }

//TABLE4 purchase_id integer primary key, customer_id integer, q_product text, qquantity integer, qdate text, product_id integer, category_id integer

    public Cursor getPurchaseData (int purchase_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM TABLE4 WHERE purchase_id="+purchase_id,null);
        return res;
    }

    public boolean insertPurchase(int customer_id, String q_product, int qquantity, String qdate, int product_id, int category_id){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("customer_id", customer_id);
        cv.put("q_product", q_product);
        cv.put("qquantity", qquantity);
        cv.put("qdate", qdate);
        cv.put("product_id", product_id);
        cv.put("category_id", category_id);
        db.insert("TABLE4", null, cv);
        return true;
    }

    public boolean updatePurchase(int purchase_id, int customer_id, String q_product, int qquantity, String qdate, int product_id, int category_id){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("customer_id", customer_id);
        cv.put("q_product", q_product);
        cv.put("qquantity", qquantity);
        cv.put("qdate", qdate);
        cv.put("product_id", product_id);
        cv.put("category_id", category_id);
        db.update("TABLE4",cv,"purchase_id=?", new String[]{Integer.toString(purchase_id)});
        return true;
    }

    public boolean deletePurchase(int purchase_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("TABLE4", "purchase_id=?",new String[]{Integer.toString(purchase_id)});
        return true;
    }

    public int numPurchaseRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int PurchaseRows=(int) DatabaseUtils.queryNumEntries(db, TABLE4);
        return PurchaseRows;
    }

    public ArrayList<String> getAllPurchase(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getPurchase= new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM TABLE4", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getPurchase.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5)+"-"+res.getString(6));
            res.moveToNext();
        }
        return getPurchase;
    }

//TABLE5 product_id integer primary key, qproductname text, q_price double, qbrand text, qpquantity integer, q_category text

    public Cursor getProductData (int product_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM TABLE5 WHERE product_id="+product_id,null);
        return res;
    }

    public boolean insertProduct(String qproductname, double q_price, String qbrand, int qpquantity, String q_category){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qproductname", qproductname);
        cv.put("q_price", q_price);
        cv.put("qbrand", qbrand);
        cv.put("qpquantity", qpquantity);
        cv.put("q_category", q_category);
        db.insert("TABLE5", null, cv);
        return true;
    }

    public boolean updateProduct(int product_id, String qproductname, double q_price, String qbrand, int qpquantity, String q_category){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qproductname", qproductname);
        cv.put("q_price", q_price);
        cv.put("qbrand", qbrand);
        cv.put("qpquantity", qpquantity);
        cv.put("q_category", q_category);
        db.update("TABLE5",cv,"product_id=?", new String[]{Integer.toString(product_id)});
        return true;
    }

    public boolean deleteProduct(int product_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("TABLE5", "product_id=?",new String[]{Integer.toString(product_id)});
        return true;
    }

    public int numProductRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int ProductRows=(int) DatabaseUtils.queryNumEntries(db, TABLE5);
        return ProductRows;
    }

    public ArrayList<String> getAllProduct(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getProduct= new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM TABLE5", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getProduct.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5));
            res.moveToNext();
        }
        return getProduct;
    }

//TABLE6 category_id integer primary key, qcategory text, qnumproduct integer

    public Cursor getCategoryData (int category_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM TABLE6 WHERE category_id="+category_id,null);
        return res;
    }

    public boolean insertCategory(String qcategory, int qnumproduct){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qcategory", qcategory);
        cv.put("qnumproduct", qnumproduct);
        db.insert("TABLE6", null, cv);
        return true;
    }

    public boolean updateCategory(int category_id, String qcategory, int qnumproduct){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qcategory", qcategory);
        cv.put("qnumproduct", qnumproduct);
        db.update("TABLE6",cv,"category_id=?", new String[]{Integer.toString(category_id)});
        return true;
    }

    public boolean deleteCategory(int category_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("TABLE6", "category_id=?",new String[]{Integer.toString(category_id)});
        return true;
    }

    public int numCategoryRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int CustomerRows=(int) DatabaseUtils.queryNumEntries(db, TABLE6);
        return CustomerRows;
    }

    public ArrayList<String> getAllCategory() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getCategory = new ArrayList<String>();
        Cursor res = db.rawQuery("SELECT * FROM TABLE6", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            getCategory.add(res.getString(0) + "-" + res.getString(1) + "-" + res.getString(2));
            res.moveToNext();
        }
        return getCategory;
    }
    }
/*Database: queennetoystore
        TABLE1: qcustomer

        COLUMN: customer_id
        c_firstname
        c_lastname
        c_age
        c_phonenum
        c_address
        c_email
        c_username
        c_password

        TABLE2: qstock

        COLUMN: stock_id
        product_id
        qprice
        qshelf
        qnumstock
        qnumsold

        TABLE3: qsales

        COLUMN: sale_id
        stock_id
        product_id
        amntsold
        numsold

        TABLE4: qpurchase

        COLUMN: purchase_id
        customer_id
        qproduct
        quantity
        qdate
        product_id
        category_id

        TABLE5: qproduct

        COLUMN: product_id
        qproduct
        qprice
        qbrand
        qquantity
        qcategory

        TABLE6: qcategory

        COLUMN:	category_id
        qcategory
        qnumproduct
 */