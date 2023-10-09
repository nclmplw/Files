package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView totalpayable;
    EditText petsa, ngan, mngan, apilyedo, itemname, presyo, pila;
    Button totalpay, insert, viewr;
    double total,price;
    int quan;
    DatabaseOperations dbo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalpayable= (TextView) findViewById(R.id.totalpayable);
        petsa=(EditText) findViewById(R.id.petsav);
        ngan=(EditText) findViewById(R.id.nganv);
        mngan=(EditText) findViewById(R.id.mnganv);
        apilyedo=(EditText) findViewById(R.id.apilyedov);
        itemname=(EditText) findViewById(R.id.itemnamev);
        presyo=(EditText) findViewById(R.id.presyov);
        pila=(EditText) findViewById(R.id.pilav);
        totalpay=(Button) findViewById(R.id.totalpay);
        insert=(Button) findViewById(R.id.update);

        dbo= new DatabaseOperations(this);

        totalpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price=Double.parseDouble(presyo.getText().toString());
                quan=Integer.parseInt(pila.getText().toString());
                total=price*quan;
                 totalpayable.setText(""+total);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((petsa.getText().toString()).equals("") & (ngan.getText().toString()).equals("") & (mngan.getText().toString()).equals("") & (apilyedo.getText().toString()).equals("") & (itemname.getText().toString()).equals("") & (presyo.getText().toString()).equals("") & (pila.getText().toString()).equals("") & (totalpayable.getText().toString()).equals("")){
                    Toast.makeText(MainActivity.this, "INPUT DATA!!!", Toast.LENGTH_SHORT).show();
                    petsa.setText("");
                    ngan.setText("");
                    mngan.setText("");
                    apilyedo.setText("");
                    itemname.setText("");
                    presyo.setText("");
                    pila.setText("");
                    totalpayable.setText("");
                    }else{
                    dbo.insertOrders(ngan.getText().toString(), mngan.getText().toString(), apilyedo.getText().toString(), itemname.getText().toString(), quan, price, petsa.getText().toString(),total);
                    petsa.setText("");
                    ngan.setText("");
                    mngan.setText("");
                    apilyedo.setText("");
                    itemname.setText("");
                    presyo.setText("");
                    pila.setText("");
                    totalpayable.setText("");
                }
            }
        });
        viewr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iview= new Intent(MainActivity.this, ViewRecords.class);
                startActivity(iview);
            }
        });
    }
}