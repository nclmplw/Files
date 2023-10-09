package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
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
                Intent home= new Intent(shop.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product=new Intent(shop.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop= new Intent(shop.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo=new Intent(shop.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo=new Intent(shop.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo=new Intent(shop.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo=new Intent(shop.this,admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus=new Intent(shop.this,aboutus.class);
                startActivity(aboutus);
                break;

            case R.id.logout:
                Intent logout=new Intent(shop.this,login.class);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }
}