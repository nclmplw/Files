package com.example.queennestoystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent main = new Intent(this,mainmenu.class);
                startActivity(main);
                break;
            case R.id.purchase:
                Intent purchase = new Intent(this, order.class);
                startActivity(purchase);
                break;
            case R.id.product:
                Intent product = new Intent(this, products.class);
                startActivity(product);
                break;
            case R.id.category:
                Intent category = new Intent(this, category.class);
                startActivity(category);
                break;
            case R.id.aboutus:
                Intent aboutus = new Intent(this, aboutus.class);
                startActivity(aboutus);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}