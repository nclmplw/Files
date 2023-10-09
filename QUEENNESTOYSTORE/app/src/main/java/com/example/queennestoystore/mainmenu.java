package com.example.queennestoystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class mainmenu extends AppCompatActivity {

    TextView welcome;
    ImageButton outdoor, deve, games, doll, babytoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        welcome= (TextView) findViewById(R.id.welcome);

        outdoor=(ImageButton) findViewById(R.id.od);
        deve=(ImageButton) findViewById(R.id.dt);
        games=(ImageButton) findViewById(R.id.g);
        doll=(ImageButton) findViewById(R.id.d);
        babytoy=(ImageButton) findViewById(R.id.bt);

        outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ot= new Intent(mainmenu.this,products.class);
                startActivity(ot);
            }
        });

        deve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ot= new Intent(mainmenu.this,products.class);
                startActivity(ot);
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ot= new Intent(mainmenu.this,products.class);
                startActivity(ot);
            }
        });

        doll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ot= new Intent(mainmenu.this,products.class);
                startActivity(ot);
            }
        });

        babytoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ot= new Intent(mainmenu.this,products.class);
                startActivity(ot);
            }
        });
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
        }
        return super.onOptionsItemSelected(item);
    }
}