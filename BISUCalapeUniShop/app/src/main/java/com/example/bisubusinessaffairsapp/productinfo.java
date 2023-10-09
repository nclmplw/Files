package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class productinfo extends AppCompatActivity {
    TextView idtext, ftext, ltext, ptext, idp, sizet;
    EditText productname, price;
    String size="";
    ImageButton insert, delete, update, save, clear;
    ListView listView;
    ArrayList<String> getAllProduct;
    ArrayAdapter arrayAdapter;
    ArrayList<String> productid;
    ArrayList<String> prdctname;
    ArrayList<String> prdctprice;
    ArrayList<String> prdctsize;
    String[] pos;
    String orderColumn = "product_id",orderSort = "ASC";
    String orderBy = orderColumn +" "+orderSort;
    DatabaseOperations dbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo);
        idtext=(TextView) findViewById(R.id.idtext);
        ftext=(TextView) findViewById(R.id.prtext);
        ltext=(TextView) findViewById(R.id.pricetext);
        ptext=(TextView) findViewById(R.id.sizetext);
        idp= (TextView) findViewById(R.id.idp);
        sizet=(TextView) findViewById(R.id.sizeerror);
        productname=(EditText) findViewById(R.id.productname);
        price=(EditText) findViewById(R.id.price);
        insert=(ImageButton) findViewById(R.id.insert);
        delete=(ImageButton) findViewById(R.id.delete);
        update=(ImageButton) findViewById(R.id.update);
        save=(ImageButton) findViewById(R.id.save);
        clear=(ImageButton) findViewById(R.id.clear);
        listView=(ListView) findViewById(R.id.listview);

        productid= new ArrayList<>();
        prdctname=new ArrayList<>();
        prdctprice=new ArrayList<>();
        prdctsize=new ArrayList<>();

        dbo=new DatabaseOperations(this);
        getAllProduct=dbo.getAllProduct();
        productid= dbo.getProductId("product_id",orderBy);
        prdctname=dbo.getProductName("qproductname",orderBy);
        prdctprice= dbo.getProductPrice("qprice",orderBy);
        prdctsize= dbo.getProductSize("qsize",orderBy);

        CustomAdapter adapter= new CustomAdapter(this, productid, prdctname, prdctprice, prdctsize);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(productinfo.this, "The Selected Value"+position, Toast.LENGTH_SHORT).show();
                pos=getAllProduct.get(position).split(" _ ");
                idp.setText(""+pos[0]);
                productname.setText(""+pos[1]);
                price.setText(""+pos[2]);
                size=pos[3];

            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(TextUtils.isEmpty(productname.getText().toString())){
                   productname.setError("Input Product Name");
               }else if(TextUtils.isEmpty(price.getText().toString())){
                   price.setError("Input Price");
               }else if(TextUtils.isEmpty(size)){
                   sizet.setError("Choose Size");
               }else{
                   dbo.insertProduct(productname.getText().toString(),Double.valueOf(price.getText().toString()),size);
                   Toast.makeText(productinfo.this, "PRODUCT INSERTED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                   idp.setText("");
                   productname.setText("");
                   price.setText("");
                   size="";
               }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbo.deleteProduct(Integer.valueOf(idp.getText().toString()))){
                    Toast.makeText(productinfo.this, "PRODUCT DELETED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(productinfo.this, "DELETE FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert.setVisibility(View.INVISIBLE);
                delete.setVisibility(View.INVISIBLE);
                update.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
                clear.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(productname.getText().toString())){
                    productname.setError("Input Product Name");
                }else if(TextUtils.isEmpty(price.getText().toString())){
                    price.setError("Input Price");
                }else if(TextUtils.isEmpty(size)){
                    sizet.setError("Input Size");
                }else{
                dbo.updateProduct(Integer.valueOf(idp.getText().toString()),productname.getText().toString(),Double.valueOf(price.getText().toString()),size);
                Toast.makeText(productinfo.this, "PRODUCT UPDATED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                idp.setText("");
                productname.setText("");
                price.setText("");
                size="";
            }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idp.setText("");
                productname.setText("");
                price.setText("");
                insert.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
                save.setVisibility(View.INVISIBLE);
                clear.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void size (View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.s:
                if (checked)
                    size="Small";
                    break;
            case R.id.xs:
                if (checked)
                    size="XSmall";
                    break;
            case R.id.m:
                if (checked)
                    size="Medium";
                    break;
            case R.id.l:
                if (checked)
                    size="Large";
                    break;
            case R.id.xl:
                if (checked)
                    size="XLarge";
                    break;
            case R.id.xxl:
                if (checked)
                    size="XXLarge";
                    break;
        }
    }

    class CustomAdapter extends ArrayAdapter{
        ArrayList<String> arrayproductid;
        ArrayList<String> arrayprdctname;
        ArrayList<String> arrayprdctprice;
        ArrayList<String> arrayprdctsize;
        public CustomAdapter(@NonNull Context context,
                ArrayList<String> arrayproductid,
                ArrayList<String> arrayprdctname,
                ArrayList<String> arrayprdctprice,
                ArrayList<String> arrayprdctsize) {
            super(context, R.layout.customproduct,R.id.idtext,arrayproductid);
         this.arrayproductid=arrayproductid;
         this.arrayprdctname=arrayprdctname;
         this.arrayprdctprice=arrayprdctprice;
         this.arrayprdctsize=arrayprdctsize;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.customproduct,parent,false);

            TextView idt=(TextView) row.findViewById(R.id.idtext);
            TextView productnamet=(TextView) row.findViewById(R.id.prtext);
            TextView pricet=(TextView) row.findViewById(R.id.pricetext);
            TextView sizet=(TextView) row.findViewById(R.id.sizetext);

            idt.setText(arrayproductid.get(position));
            productnamet.setText(arrayprdctname.get(position));
            pricet.setText(arrayprdctprice.get(position));
            sizet.setText(arrayprdctsize.get(position));
            return super.getView(position, convertView, parent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent home= new Intent(productinfo.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product=new Intent(productinfo.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop= new Intent(productinfo.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo=new Intent(productinfo.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo=new Intent(productinfo.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo=new Intent(productinfo.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo=new Intent(productinfo.this,admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus=new Intent(productinfo.this,aboutus.class);
                startActivity(aboutus);
                break;

            case R.id.logout:
                Intent logout=new Intent(productinfo.this,login.class);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }
}