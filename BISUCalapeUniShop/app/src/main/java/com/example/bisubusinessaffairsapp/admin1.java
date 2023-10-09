package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin1 extends AppCompatActivity {
    EditText password;
    Button confirm;
    boolean passwordVisible = false;
    DatabaseOperations dbo;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean checkp = dbo.chckpassword(password.getText().toString());

                if(TextUtils.isEmpty(password.getText().toString())){

                    password.setText("");
                }
                else if(checkp == true) {
                    loadingBar.setTitle("Administrator's Information");
                    loadingBar.setMessage("Please wait, while we are checking password.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();
                    Intent intent = new Intent(admin1.this, admininfo.class);
                    startActivity(intent);
                    password.setText("");
                }else{

                }
            }
            private void showError(EditText password, String invalid_password) {
                password.setError(invalid_password);
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
                Intent home= new Intent(admin1.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product=new Intent(admin1.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop= new Intent(admin1.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo=new Intent(admin1.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo=new Intent(admin1.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo=new Intent(admin1.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo=new Intent(admin1.this,admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus=new Intent(admin1.this,aboutus.class);
                startActivity(aboutus);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}