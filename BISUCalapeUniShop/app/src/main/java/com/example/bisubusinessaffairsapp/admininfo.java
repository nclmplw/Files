package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class admininfo extends AppCompatActivity {
    TextView ida;
    EditText firstnamea, lastnamea, username, password;
    ImageButton update, delete, insert, save;
    DatabaseOperations dbo;
    ListView listview;
    ArrayAdapter adapter;
    ArrayList<String> getAdmins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admininfo);
        ida=(TextView) findViewById(R.id.ida);
        firstnamea=(EditText) findViewById(R.id.firstnamea);
        lastnamea=(EditText) findViewById(R.id.lastnamea);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        update=(ImageButton) findViewById(R.id.update);
        delete= (ImageButton) findViewById(R.id.delete);
        insert=(ImageButton) findViewById(R.id.insert);
        save= (ImageButton) findViewById(R.id.save);
        listview=(ListView) findViewById(R.id.listview);
        dbo= new DatabaseOperations(this);
        getAdmins=dbo.getAllAdmin();

        adapter= new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,getAdmins);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(admininfo.this, "The Selected Value "+ position, Toast.LENGTH_LONG).show();
                ida.setText(""+getAdmins.get(position));
                String text[] = getAdmins.get(position).split(" | ");
                firstnamea.setText(""+text[1]);
                lastnamea.setText(""+text[2]);
                username.setText(""+text[4]);
                password.setText(""+text[5]);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstnamea.getText().toString().equals("")&lastnamea.getText().toString().equals("")&username.getText().toString().equals("")&password.getText().toString().equals("")){
                    showError1(firstnamea,"Enter firstname");
                    showError2(lastnamea,"Enter lastname");
                    showError3(username,"Enter username");
                    showError4(password,"Enter password");
                }else{
                    dbo.updateAdmin(Integer.valueOf(ida.getText().toString()),firstnamea.getText().toString(),lastnamea.getText().toString(),username.getText().toString(),password.getText().toString());
                    firstnamea.setText("");
                    lastnamea.setText("");
                    username.setText("");
                    password.setText("");
                }
            }

            private void showError4(EditText password, String enter_password) {
                password.setError(enter_password);
            }

            private void showError3(EditText username, String enter_username) {
                username.setError(enter_username);
            }

            private void showError2(EditText lastnamea, String enter_lastname) {
                lastnamea.setError(enter_lastname);
            }

            private void showError1(EditText firstnamea, String enter_firstname) {
                firstnamea.setError(enter_firstname);
            }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbo.deleteAdmin(Integer.valueOf(ida.getText().toString()))){
                    Toast.makeText(admininfo.this, "DELETED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(admininfo.this, "DELETE FAILED!", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                Intent home= new Intent(admininfo.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product=new Intent(admininfo.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop= new Intent(admininfo.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo=new Intent(admininfo.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo=new Intent(admininfo.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo=new Intent(admininfo.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo=new Intent(admininfo.this,admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus=new Intent(admininfo.this,aboutus.class);
                startActivity(aboutus);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}