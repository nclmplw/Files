package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class aboutus extends AppCompatActivity {
    Button vision, mission, goal, core;
    LinearLayout linear, linearsv;
    ScrollView svm, svg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        vision=(Button) findViewById(R.id.vision);
        mission=(Button) findViewById(R.id.mission);
        goal=(Button) findViewById(R.id.goal);
        core=(Button) findViewById(R.id.core);
        linear=(LinearLayout) findViewById(R.id.linear);
        linearsv=(LinearLayout) findViewById(R.id.linearsv);
        svm=(ScrollView) findViewById(R.id.svm);
        svg=(ScrollView) findViewById(R.id.svg);

        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearsv.setVisibility(View.VISIBLE);
                svm.setVisibility(View.INVISIBLE);
                svg.setVisibility(View.INVISIBLE);
            }
        });

        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearsv.setVisibility(View.INVISIBLE);
                svm.setVisibility(View.VISIBLE);
                svg.setVisibility(View.INVISIBLE);
            }
        });

        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearsv.setVisibility(View.INVISIBLE);
                svm.setVisibility(View.INVISIBLE);
                svg.setVisibility(View.VISIBLE);
            }
        });

        core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(aboutus.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product = new Intent(aboutus.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop = new Intent(aboutus.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo = new Intent(aboutus.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo = new Intent(aboutus.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo = new Intent(aboutus.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo = new Intent(aboutus.this, admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus = new Intent(aboutus.this, aboutus.class);
                startActivity(aboutus);
                break;

            case R.id.logout:
                Intent logout=new Intent(aboutus.this,login.class);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }
}