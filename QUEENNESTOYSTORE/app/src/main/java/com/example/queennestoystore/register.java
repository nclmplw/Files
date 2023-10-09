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
import android.widget.TextView;

public class register extends AppCompatActivity {
    TextView txterror;
    EditText  cfirstname, clastname, cage, cphonenum, caddress, cemail, cusername, cpassword;
    Button rlogin;
    DatabaseOperations dbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txterror = (TextView) findViewById(R.id.txterror);
        cfirstname = (EditText) findViewById(R.id.fname);
        clastname = (EditText) findViewById(R.id.lname);
        cage = (EditText) findViewById(R.id.age);
        cphonenum = (EditText) findViewById(R.id.phonenum);
        caddress = (EditText) findViewById(R.id.address);
        cemail = (EditText) findViewById(R.id.email);
        cusername = (EditText) findViewById(R.id.username);
        cpassword = (EditText) findViewById(R.id.password);
        rlogin = (Button) findViewById(R.id.rlogin);
        dbo = new DatabaseOperations(this);

        rlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cfirstname.getText().toString().equals("")&clastname.getText().toString().equals("")&cage.getText().toString().equals("")&cphonenum.getText().toString().equals("")&caddress.getText().toString().equals("")&cemail.getText().toString().equals("")&cusername.getText().toString().equals("")&cpassword.getText().toString().equals("")){
                    txterror.setText("Please fill out the information below.");
                    txterror.setTextColor(Color.rgb(255,0,0));
                }else{
                    txterror.setText("Please fill out the information below.");
                    txterror.setTextColor(Color.rgb(0,0,0));
                    dbo.insertCustomer(cfirstname.getText().toString(), clastname.getText().toString(), cage.getText().toString(), Integer.valueOf(cphonenum.getText().toString()), caddress.getText().toString(), cemail.getText().toString(), cusername.getText().toString(), cpassword.getText().toString());
                    Intent login = new Intent(register.this,MainActivity.class);
                    startActivity(login);
                }
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