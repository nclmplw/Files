package com.example.bisubusinessaffairsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class customerinfo extends AppCompatActivity {
    TextView idtext, ftext, ltext, ctext, etext, ptext, courseerror, idc;
    EditText firstnamec, lastnamec, email, password;
    String course="";
    ImageButton insert, delete, update, save, clear;
    ListView listView;
    ArrayList<String> getAllCustomer;
    ArrayAdapter arrayAdapter;
    ArrayList<String> customerid;
    ArrayList<String> customerf;
    ArrayList<String> customerl;
    ArrayList<String> customerc;
    ArrayList<String> customere;
    ArrayList<String> customerp;
    String[] pos;
    String orderColumn = "customer_id",orderSort = "ASC";
    String orderBy = orderColumn +" "+orderSort;
    DatabaseOperations dbo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinfo);
        setContentView(R.layout.activity_productinfo);
        idc=(TextView) findViewById(R.id.idc);
        idtext=(TextView) findViewById(R.id.idtext);
        ftext=(TextView) findViewById(R.id.ftext);
        ltext=(TextView) findViewById(R.id.ltext);
        ctext=(TextView) findViewById(R.id.ctext);
        etext=(TextView) findViewById(R.id.etext);
        ptext=(TextView) findViewById(R.id.ptext);
        idc= (TextView) findViewById(R.id.idc);
        courseerror=(TextView) findViewById(R.id.courseerror);
        firstnamec=(EditText) findViewById(R.id.firstnamec);
        lastnamec=(EditText) findViewById(R.id.lastnamec);
        email=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        insert=(ImageButton) findViewById(R.id.insert);
        delete=(ImageButton) findViewById(R.id.delete);
        update=(ImageButton) findViewById(R.id.update);
        save=(ImageButton) findViewById(R.id.save);
        clear=(ImageButton) findViewById(R.id.clear);
        listView=(ListView) findViewById(R.id.listview);

        customerid= new ArrayList<>();
        customerf=new ArrayList<>();
        customerl=new ArrayList<>();
        customerc=new ArrayList<>();
        customere=new ArrayList<>();
        customerp=new ArrayList<>();

        dbo=new DatabaseOperations(this);
        getAllCustomer=dbo.getAllCustomer();
        customerid= dbo.getCustomerId("customer_id",orderBy);
        customerf=dbo.getCustomerF("c_firstname",orderBy);
        customerl= dbo.getCustomerL("c_lastnamw",orderBy);
        customerc= dbo.getCustomerC("c_course",orderBy);
        customere=dbo.getCustomerE("c_email",orderBy);
        customerp=dbo.getCustomerP("c_password",orderBy);

        customerinfo.CustomAdapter adapter= new customerinfo.CustomAdapter(this, customerid, customerf, customerl, customerc, customere, customerp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(customerinfo.this, "The Selected Value"+position, Toast.LENGTH_SHORT).show();
                pos=getAllCustomer.get(position).split(" _ ");
                idc.setText(""+pos[0]);
                firstnamec.setText(""+pos[1]);
                lastnamec.setText(""+pos[2]);
                course=pos[3];
                email.setText(""+pos[4]);
                password.setText(""+pos[5]);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstnamec.getText().toString())){
                    firstnamec.setError("Input Firstname");
                }else if(TextUtils.isEmpty(lastnamec.getText().toString())){
                    lastnamec.setError("Input Lastname");
                }else if(TextUtils.isEmpty(course)){
                    courseerror.setError("Choose Course");
                }else if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Input BISU E-mail");
                }else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Input Password");
                }else{
                    dbo.insertCustomer(firstnamec.getText().toString(),lastnamec.getText().toString(),course, email.getText().toString(),password.getText().toString());
                    Toast.makeText(customerinfo.this, "PRODUCT INSERTED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                    idc.setText("");
                    firstnamec.setText("");
                    lastnamec.setText("");
                    course="";
                    email.setText("");
                    password.setText("");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbo.deleteCustomer(Integer.valueOf(idc.getText().toString()))){
                    Toast.makeText(customerinfo.this, "CUSTOMER DELETED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(customerinfo.this, "DELETE FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert.setVisibility(View.INVISIBLE);
                delete.setVisibility(View.INVISIBLE);
                update.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
                clear.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstnamec.getText().toString())){
                    firstnamec.setError("Input Firstname");
                }else if(TextUtils.isEmpty(lastnamec.getText().toString())){
                    lastnamec.setError("Input Lastname");
                }else if(TextUtils.isEmpty(course)){
                    courseerror.setError("Choose Course");
                }else if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Input BISU E-mail");
                }else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Input Password");
                }else{
                    dbo.updateCustomer(Integer.valueOf(idc.getText().toString()),firstnamec.getText().toString(),lastnamec.getText().toString(),course, email.getText().toString(),password.getText().toString());
                    Toast.makeText(customerinfo.this, "PRODUCT UPDATED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                    idc.setText("");
                    firstnamec.setText("");
                    lastnamec.setText("");
                    course="";
                    email.setText("");
                    password.setText("");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idc.setText("");
                firstnamec.setText("");
                lastnamec.setText("");
                course="";
                email.setText("");
                password.setText("");
                insert.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);
                save.setVisibility(View.INVISIBLE);
                clear.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void course (View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.bscs:
                if (checked)
                    course="BSCS";
                break;
            case R.id.bsf:
                if (checked)
                    course="BSF";
                break;
            case R.id.bsit:
                if (checked)
                    course="BSIT";
                break;
            case R.id.bsede:
                if (checked)
                    course="BSED-Eng";
                break;
            case R.id.bsedm:
                if (checked)
                    course="BSED-Math";
                break;
            case R.id.fpst:
                if (checked)
                    course="FPST";
                break;
        }
    }

    class CustomAdapter extends ArrayAdapter{
        ArrayList<String> arraycustomerid;
        ArrayList<String> arraycustomerf;
        ArrayList<String> arraycustomerl;
        ArrayList<String> arraycustomerc;
        ArrayList<String> arraycustomere;
        ArrayList<String> arraycustomerp;
        public CustomAdapter(@NonNull Context context,
                             ArrayList<String> arraycustomerid,
                             ArrayList<String> arraycustomerf,
                             ArrayList<String> arraycustomerl,
                             ArrayList<String> arraycustomerc,
                             ArrayList<String> arraycustomere,
                             ArrayList<String> arraycustomerp) {
            super(context, R.layout.custom,R.id.cidtext,arraycustomerid);
            this.arraycustomerid=arraycustomerid;
            this.arraycustomerf=arraycustomerf;
            this.arraycustomerl=arraycustomerl;
            this.arraycustomerc=arraycustomerc;
            this.arraycustomere=arraycustomere;
            this.arraycustomerp=arraycustomerp;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.custom,parent,false);

            TextView cidtext=(TextView) row.findViewById(R.id.cidtext);
            TextView ftext=(TextView) row.findViewById(R.id.ftext);
            TextView ltext=(TextView) row.findViewById(R.id.ltext);
            TextView ctext=(TextView) row.findViewById(R.id.ctext);
            TextView etext=(TextView) row.findViewById(R.id.etext);
            TextView ptext=(TextView) row.findViewById(R.id.ptext);

            cidtext.setText(arraycustomerid.get(position));
            ftext.setText(arraycustomerf.get(position));
            ltext.setText(arraycustomerl.get(position));
            ctext.setText(arraycustomerc.get(position));
            etext.setText(arraycustomere.get(position));
            ptext.setText(arraycustomerp.get(position));


            return super.getView(position, convertView, parent);
        }
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
                Intent home= new Intent(customerinfo.this, home.class);
                startActivity(home);
                break;

            case R.id.product:
                Intent product=new Intent(customerinfo.this, product.class);
                startActivity(product);
                break;

            case R.id.shop:
                Intent shop= new Intent(customerinfo.this, shop.class);
                startActivity(shop);
                break;

            case R.id.admin:
                break;

            case R.id.customerinfo:
                Intent customerinfo=new Intent(customerinfo.this, admin.class);
                startActivity(customerinfo);
                break;

            case R.id.admininfo:
                Intent admininfo=new Intent(customerinfo.this, admin1.class);
                startActivity(admininfo);
                break;

            case R.id.productinfo:
                Intent productinfo=new Intent(customerinfo.this, admin2.class);
                startActivity(productinfo);
                break;

            case R.id.purchasedinfo:
                Intent purchasedinfo=new Intent(customerinfo.this,admin3.class);
                startActivity(purchasedinfo);
                break;

            case R.id.aboutus:
                Intent aboutus=new Intent(customerinfo.this,aboutus.class);
                startActivity(aboutus);
                break;

            case R.id.logout:
                Intent logout=new Intent(customerinfo.this,login.class);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }
}