package com.example.bisubusinessaffairsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final String DATABASE = "bisu.db";
//table1 qcustomer, c_firstname, c_lastname, c_age, c_phonenum, c_address, c_email, c_username, c_password
    public static final String TABLE1 = "qcustomer";
    public static final String QIdcustomer="c_id";
    public static final String  QFirstname ="c_firstname";
    public static final String QLastname = "c_lastname";
    public static final String Qcourse = "c_course";
    public static final String QEmail = "c_email";
    public static final String QPassword = "c_password";
//table2 stock_id, product_id, qprice, qshelf, qnumstock, qnumsold
    public static final String TABLE2 = "qstock";
    public static final String QShelf ="qshelf";
    public static final String QStock = "qnumstock";
    public static final String QSold = "qnumsold";
//table3 sale_id, stock_id, product_id, qamntsold, qnum_sold
    public static final String TABLE3 = "qsales";
    public static final String QSsid= "sales_id";
    public static final String QAmntsold = "qamntsold";
//table4 purchase_id, customer_id, q_product, qquantity, qdate, product_id, category_id
    public static final String TABLE4 = "qpurchase";
    public static final String QQuantity = "qquantity";
    public static final String QDate = "qdate";
//table5 product_id, qproductname, q_price, qbrand, qpquantity, q_category
    public static final String TABLE5 = "qproduct";
    public static final String QPrid= "product_id";
    public static final String QProductname = "qproductname";
    public static final String QPrice = "qprice";
    public static final String QSize = "qsize";
//table6 category_id, qcategory, qnumproduct
    public static final String TABLE6 = "qadmin";
    public static final String QAid= "admin_id";
    public static final String QAFirstname ="a_firstname";
    public static final String QALastname = "a_lastname";
    public static final String QAEmail = "a_email";
    public static final String QAPassword = "a_password";

    public DatabaseOperations(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE qcustomer (customer_id integer primary key autoincrement,c_firstname text," +
                " c_lastname text, c_course text, c_email text, c_password text)");
        System.out.println("GOODs");
        System.out.println(db);
        db.execSQL("CREATE TABLE qstock (stock_id integer primary key autoincrement, qproductname text, qprice double," +
                " qshelf integer, qstock integer, qnumsold integer,"+
                "FOREIGN KEY (qproductname) REFERENCES qproduct(qproductname) ON DELETE CASCADE ON UPDATE CASCADE )");
        System.out.println("GOODs");
        System.out.println(db);
        db.execSQL("CREATE TABLE qsales (sale_id integer primary key, stock_id integer, qproductname text, qnumsold integer," +
                " qamntsold integer," +
                "FOREIGN KEY(stock_id) REFERENCES qstock(stock_id) ON DELETE CASCADE ON UPDATE CASCADE )");
        System.out.println("GOODs");
        System.out.println(db);
        db.execSQL("CREATE TABLE qpurchase (purchase_id integer primary key autoincrement," +
                " c_firstname text," +
                " c_lastname text," +
                " qproductname text," +
                " qprice integer," +
                " qquantity integer," +
                " qsize integer," +
                " qdate text," +
                "FOREIGN KEY(c_firstname) REFERENCES qcustomer(c_firstname) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(c_lastname) REFERENCES qcustomer(c_lastname) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(qproductname) REFERENCES qproduct(qproductname) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(qprice) REFERENCES qproduct(qprice) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(qsize) REFERENCES qproduct(qsize) ON DELETE CASCADE ON UPDATE CASCADE)");
        System.out.println("GOODs");
        System.out.println(db);
        db.execSQL("CREATE TABLE qproduct (product_id integer primary key autoincrement, qproductname text, qprice double, qsize text)");
        System.out.println("GOODs");
        System.out.println(db);
        db.execSQL("CREATE TABLE qadmin (admin_id integer primary key autoincrement, a_firstname text, a_lastname text," +
                " a_email text, a_password text)");
        System.out.println("GOODs");
        System.out.println(db);
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
        Cursor res= db.rawQuery("SELECT * FROM qcustomer WHERE customer_id="+customer_id,null);
        return res;
    }

    public boolean insertCustomer(String c_firstname, String c_lastname, String c_course, String c_email, String c_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("c_course", c_course);
        cv.put("c_email", c_email);
        cv.put("c_password", c_password);
        db.insert("qcustomer", null, cv);
        return true;
    }

    public boolean updateCustomer(int customer_id, String c_firstname, String c_lastname, String c_course, String c_email, String c_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("c_course", c_course);
        cv.put("c_email", c_email);
        cv.put("c_password", c_password);
        db.update("qcustomer",cv,"customer_id=?", new String[]{Integer.toString(customer_id)});
        return true;
    }

    public boolean deleteCustomer(int customer_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("qcustomer", "customer_id=?",new String[]{Integer.toString(customer_id)});
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
        Cursor res=db.rawQuery("SELECT * FROM qcustomer", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getCustomer.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5));
            res.moveToNext();
        }
        return getCustomer;
    }

    public Boolean checkemail(String c_email){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM qcustomer WHERE c_email=?",new String[]{c_email});
        if(res.getCount() > 0){
            return true;
        }else
            return false;
    }

    public Boolean checkpassword(String c_password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM qcustomer WHERE c_password=?",new String[]{c_password});
        if(res.getCount() > 0){
            return true;
        }else
            return false;
    }
    //TABLE2 int stock_id, int product_id, double qprice, int qshelf, int qnumstock, int qnumsold
//stock_id integer primary key, qproductname text, qprice double, qshelf integer, qnumstock integer, qnumsold integer
    public Cursor getStockData (int stock_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM qstocks WHERE stock_id="+stock_id,null);
        return res;
    }

    public boolean insertStock(String productname, double qprice, int qshelf, int qnumstock, int qnumsold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("productname", productname);
        cv.put("qprice", qprice);
        cv.put("qshelf", qshelf);
        cv.put("qnumstock", qnumstock);
        cv.put("qnumsold", qnumsold);
        db.insert("qstocks", null, cv);
        return true;
    }

    public boolean updateStock(int stock_id, String productname, double qprice, int qshelf, int qnumstock, int qnumsold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("productname", productname);
        cv.put("qprice", qprice);
        cv.put("qshelf", qshelf);
        cv.put("qnumstock", qnumstock);
        cv.put("qnumsold", qnumsold);
        db.update("qstocks",cv,"stock_id=?", new String[]{Integer.toString(stock_id)});
        return true;
    }

    public boolean deleteStock(int stock_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("qstocks", "stock_id=?",new String[]{Integer.toString(stock_id)});
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
            getStock.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5));
            res.moveToNext();
        }
        return getStock;
    }

    //TABLE3 sale_id integer primary key, stock_id integer, product_id integer, qamntsold integer, qnum_sold integer
//sale_id integer primary key, stock_id integer, qproductname text, qamntsold integer, qnumsold integer
    public Cursor getSalesData (int sale_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM qsales WHERE sale_id="+sale_id,null);
        return res;
    }

    public boolean insertSales(int stock_id, String productname, double qamntsold, int qnum_sold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("stock_id", stock_id);
        cv.put("productname", productname);
        cv.put("qamntsold", qamntsold);
        cv.put("qnum_sold", qnum_sold);
        db.insert("qsales", null, cv);
        return true;
    }

    public boolean updateSales(int sale_id, int stock_id, String productname, double qamntsold, int qnum_sold){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("stock_id", stock_id);
        cv.put("productname", productname);
        cv.put("qamntsold", qamntsold);
        cv.put("qnum_sold", qnum_sold);
        db.update("qsales",cv,"sale_id=?", new String[]{Integer.toString(sale_id)});
        return true;
    }

    public boolean deleteSales(int sale_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("qsales", "sale_id=?",new String[]{Integer.toString(sale_id)});
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
//purchase_id integer primary key,  c_firstname text, c_lastname text, qproductname text, qquantity integer, size integer, qdate text
    public Cursor getPurchaseData (int purchase_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM qpurchase WHERE purchase_id="+purchase_id,null);
        return res;
    }

    public boolean insertPurchase(int c_firstname, String c_lastname, String qproductname, int qquantity, int size, String qdate){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("qproductname", qproductname);
        cv.put("qquantity", qquantity);
        cv.put("size", size);
        cv.put("qdate", qdate);
        db.insert("qpurchase", null, cv);
        return true;
    }

    public boolean updatePurchase(int purchase_id, int c_firstname, String c_lastname, String qproductname, int qquantity, int size, String qdate){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("c_firstname", c_firstname);
        cv.put("c_lastname", c_lastname);
        cv.put("qproductname", qproductname);
        cv.put("qquantity", qquantity);
        cv.put("size", size);
        cv.put("qdate", qdate);
        db.update("qpurchase",cv,"purchase_id=?", new String[]{Integer.toString(purchase_id)});
        return true;
    }

    public boolean deletePurchase(int purchase_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("qpurchase", "purchase_id=?",new String[]{Integer.toString(purchase_id)});
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
        Cursor res=db.rawQuery("SELECT * FROM qpurchase", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getPurchase.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4)+"-"+res.getString(5)+"-"+res.getString(6));
            res.moveToNext();
        }
        return getPurchase;
    }

    //TABLE5 product_id integer primary key, qproductname text, q_price double, qbrand text, qpquantity integer, q_category text
//product_id integer primary key, qproductname text, qprice double, qsize
    public Cursor getProductData (int product_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM qproduct WHERE product_id="+product_id,null);
        return res;
    }

    public boolean insertProduct(String qproductname, double qprice, String qsize){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qproductname", qproductname);
        cv.put("qprice", qprice);
        cv.put("qsize", qsize);
        db.insert("qproduct", null, cv);
        return true;
    }

    public boolean updateProduct(int product_id, String qproductname, double qprice, String qsize){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("qproductname", qproductname);
        cv.put("qprice", qprice);
        cv.put("qsize", qsize);
        db.update("qproduct",cv,"product_id=?", new String[]{Integer.toString(product_id)});
        return true;
    }

    public boolean deleteProduct(int product_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("qproduct", "product_id=?",new String[]{Integer.toString(product_id)});
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
        Cursor res=db.rawQuery("SELECT * FROM qproduct", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getProduct.add(res.getString(0)+"_"+res.getString(1)+"_"+res.getString(2)+"_"+ res.getString(3));
            res.moveToNext();
        }
        return getProduct;
    }

//TABLE6  admin_id, a_firstname, a_lastname, a_username, a_password

    public Cursor getAdminData (int admin_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM qadmin WHERE admin_id="+admin_id,null);
        return res;
    }

    public boolean insertAdmin(String a_firstname, String a_lastname, String a_email, String a_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("a_firstname", a_firstname);
        cv.put("a_lastname", a_lastname);
        cv.put("a_email", a_email);
        cv.put("a_password", a_password);
        db.insert("qadmin", null, cv);
        return true;
    }

    public boolean updateAdmin(int admin_id, String a_firstname, String a_lastname, String a_email, String a_password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("a_firstname", a_firstname);
        cv.put("a_lastname", a_lastname);
        cv.put("a_email", a_email);
        cv.put("a_password", a_password);
        db.update("qadmin",cv,"admin_id=?", new String[]{Integer.toString(admin_id)});
        return true;
    }

    public boolean deleteAdmin(int admin_id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("qadmin", "customer_id=?",new String[]{Integer.toString(admin_id)});
        return true;
    }

    public int numAdminRows(){
        SQLiteDatabase db=this.getReadableDatabase();
        int AdminRows=(int) DatabaseUtils.queryNumEntries(db, TABLE6);
        return AdminRows;
    }

    public ArrayList<String> getAllAdmin(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> getAdmin= new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM qadmin", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            getAdmin.add(res.getString(0)+"-"+res.getString(1)+"-"+res.getString(2)+"-"+res.getString(3)+"-"+res.getString(4));
            res.moveToNext();
        }
        return getAdmin;
    }

    public Boolean chckusername(String a_username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM qadmin WHERE a_email=?",new String[]{a_username});
        if(res.getCount() > 0){
            return true;
        }else
            return false;
    }

    public Boolean chckpassword(String a_password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM qadmin WHERE a_password=?",new String[]{a_password});
        if(res.getCount() > 0){
            return true;
        }else
            return false;
    }
//PRODUCT TABLE GET ROWS OF A SPECIFIC COLUMN "START"
    public ArrayList<String> getProductId(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> ProductId= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            ProductId.add(res.getString(0));
            res.moveToNext();
        }
        return ProductId;
    }

    public ArrayList<String> getProductName(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> ProductName= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            ProductName.add(res.getString(1));
            res.moveToNext();
        }
        return ProductName;
    }

    public ArrayList<String> getProductPrice(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> ProductPrice= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            ProductPrice.add(res.getString(2));
            res.moveToNext();
        }
        return ProductPrice;
    }

    public ArrayList<String> getProductSize(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> ProductSize= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            ProductSize.add(res.getString(3));
            res.moveToNext();
        }
        return ProductSize;
    }
//PRODUCT TABLE GET ROWS OF A SPECIFIC ROW "END"

//CUSTOMER TABLE GET ROWS OF A SPECIFIC ROW "START"
public ArrayList<String> getCustomerId(String colindex, String orderBy){
    SQLiteDatabase db = this.getReadableDatabase();
    ArrayList<String> CustomerId= new ArrayList<String>();
    Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
    res.moveToFirst();
    while (res.isAfterLast() == false){
        int comindexnum= res.getColumnIndex(colindex);
        CustomerId.add(res.getString(0));
        res.moveToNext();
    }
    return CustomerId;
}

    public ArrayList<String> getCustomerF(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerF= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerF.add(res.getString(1));
            res.moveToNext();
        }
        return CustomerF;
    }

    public ArrayList<String> getCustomerL(String colindex, String orderBy){
    SQLiteDatabase db = this.getReadableDatabase();
    ArrayList<String> CustomerL= new ArrayList<String>();
    Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
    res.moveToFirst();
    while (res.isAfterLast() == false){
        int comindexnum= res.getColumnIndex(colindex);
        CustomerL.add(res.getString(2));
        res.moveToNext();
    }
    return CustomerL;
}

    public ArrayList<String> getCustomerC(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerC= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerC.add(res.getString(3));
            res.moveToNext();
        }
        return CustomerC;
    }

    public ArrayList<String> getCustomerE(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerE= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerE.add(res.getString(4));
            res.moveToNext();
        }
        return CustomerE;
    }

    public ArrayList<String> getCustomerP(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerP= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerP.add(res.getString(5));
            res.moveToNext();
        }
        return CustomerP;
    }
//CUSTOMER GET ROWS OF A SPECIFIC COLUMN "END


//ADMIN TABLE GET ROWS OF A SPECIFIC ROW "START"
    public ArrayList<String> getAdminId(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> AdminId= new ArrayList<String>();
        Cursor res=db.query("qadmin", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            AdminId.add(res.getString(0));
            res.moveToNext();
        }
        return AdminId;
    }

    public ArrayList<String> getAdminF(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> AdminF= new ArrayList<String>();
        Cursor res=db.query("qadmin", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            AdminF.add(res.getString(1));
            res.moveToNext();
        }
        return AdminF;
    }
//TO BE CONTINUED hehehuhu
    public ArrayList<String> getAdminL(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerL= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerL.add(res.getString(2));
            res.moveToNext();
        }
        return CustomerL;
    }

    public ArrayList<String> getAdminE(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerE= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerE.add(res.getString(4));
            res.moveToNext();
        }
        return CustomerE;
    }

    public ArrayList<String> getAdminP(String colindex, String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> CustomerP= new ArrayList<String>();
        Cursor res=db.query("qcustomer", null, null, null, null,null,orderBy);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            int comindexnum= res.getColumnIndex(colindex);
            CustomerP.add(res.getString(5));
            res.moveToNext();
        }
        return CustomerP;
    }
//CUSTOMER GET ROWS OF A SPECIFIC COLUMN "END
}
