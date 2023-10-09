package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class home extends AppCompatActivity {
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //calendar
        Calendar now = Calendar.getInstance();
        String nowDate = DateFormat.getDateInstance(DateFormat.FULL).format(now.getTime());
        date = (TextView) findViewById(R.id.date);
        date.setText(nowDate);
        //                        intent.putExtra("Petsa", nowDate);
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
                Intent home= new Intent(home.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product=new Intent(home.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop= new Intent(home.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo=new Intent(home.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo=new Intent(home.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo=new Intent(home.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo=new Intent(home.this,admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus=new Intent(home.this,aboutus.class);
                startActivity(aboutus);
                break;

            case R.id.logout:
                Intent logout=new Intent(home.this,login.class);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }
}