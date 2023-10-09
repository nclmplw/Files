package com.example.queennestoystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView error, shopnow;
    EditText username, password;
    Button login, registertxt;
    ImageButton registerbttn;
    DatabaseOperations dbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shopnow = (TextView) findViewById(R.id.shopnow);
        registerForContextMenu(shopnow);
        error = (TextView) findViewById(R.id.texterror);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        registertxt = (Button) findViewById(R.id.registertxt);
        registerbttn = (ImageButton) findViewById(R.id.registerbttn);
        dbo = new DatabaseOperations(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getusername = getIntent().getExtras().getString("QUsername");
                String getpassword = getIntent().getExtras().getString("QPassword");
                if(username.getText().toString().equals("")&&password.getText().toString().equals("")){
                    error.setText("Please enter your login information.");
                    error.setTextColor(Color.rgb(255,0,0));
                }else if(username.getText().toString().equals(getusername) && password.getText().toString().equals(getpassword)){
                    error.setText("Please enter your login information.");
                    error.setTextColor(Color.rgb(0,0,0));
                        Intent travel= new Intent(MainActivity.this,mainmenu.class);
                        travel.putExtra("Username",username.getText().toString());
                        startActivity(travel);
                    }else {
                        error.setText("You've entered an incorrect username and password.");
                        error.setTextColor(Color.rgb(255,0,0));
                        username.setText("");
                        password.setText("");
                    }
                }
        });
        registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registert= new Intent(MainActivity.this, register.class);
                startActivity(registert);
            }
        });
        registerbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerb= new Intent(MainActivity.this, register.class);
                startActivity(registerb);
            }
        });
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent main = new Intent(this,mainmenu.class);
                startActivity(main);
                break;
            case R.id.purchase:
                Intent note = new Intent(this,note.class);
                startActivity(note);
                break;
            case R.id.product:
                Intent notes = new Intent(this, note.class);
                startActivity(notes);
                break;
            case R.id.category:
                Intent notess = new Intent(this, note.class);
                startActivity(notess);
                break;
            case R.id.aboutus:
                Intent aboutus = new Intent(this, aboutus.class);
                startActivity(aboutus);
                break;
        }
        return super.onContextItemSelected(item);
    }
}