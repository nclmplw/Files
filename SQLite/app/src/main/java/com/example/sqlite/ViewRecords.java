package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewRecords extends AppCompatActivity {
    EditText id,totalpayablev, petsav, nganv, mnganv, apilyedov, itemnamev, presyov, pilav;
    Button update, delete;
    DatabaseOperations dbo;
    ListView listview;
    ArrayAdapter adapter;
    ArrayList<String> getOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        totalpayablev= (EditText) findViewById(R.id.totalpayablev);
        id=(EditText) findViewById(R.id.id);
        petsav=(EditText) findViewById(R.id.petsav);
        nganv=(EditText) findViewById(R.id.nganv);
        mnganv=(EditText) findViewById(R.id.mnganv);
        apilyedov=(EditText) findViewById(R.id.apilyedov);
        itemnamev=(EditText) findViewById(R.id.itemnamev);
        presyov=(EditText) findViewById(R.id.presyov);
        pilav=(EditText) findViewById(R.id.pilav);
        delete=(Button) findViewById(R.id.delete);
        update=(Button) findViewById(R.id.update);
        listview=(ListView) findViewById(R.id.listv);
        dbo= new DatabaseOperations(this);
        getOrders=dbo.getAllOrders();

        adapter= new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,getOrders);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewRecords.this, "The Selected Value "+ position, Toast.LENGTH_LONG).show();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((petsav.getText().toString()).equals("") & (nganv.getText().toString()).equals("") & (mnganv.getText().toString()).equals("") & (apilyedov.getText().toString()).equals("") & (itemnamev.getText().toString()).equals("") & (presyov.getText().toString()).equals("") & (pilav.getText().toString()).equals("") & (totalpayablev.getText().toString()).equals("")) {
                    Toast.makeText(ViewRecords.this, "INPUT DATA!!!", Toast.LENGTH_SHORT).show();
                    petsav.setText("");
                    nganv.setText("");
                    mnganv.setText("");
                    apilyedov.setText("");
                    itemnamev.setText("");
                    presyov.setText("");
                    pilav.setText("");
                    totalpayablev.setText("");
                } else {
                    dbo.updateOrders(Integer.valueOf(id.getText().toString()), nganv.getText().toString(), mnganv.getText().toString(), apilyedov.getText().toString(), itemnamev.getText().toString(), Integer.valueOf(pilav.getText().toString()), Double.valueOf(presyov.getText().toString()), petsav.getText().toString(), Double.parseDouble(totalpayablev.getText().toString()));
                    Toast.makeText(ViewRecords.this, "DATA HAS BEEN UPDATED!", Toast.LENGTH_SHORT).show();
                    petsav.setText("");
                    nganv.setText("");
                    mnganv.setText("");
                    apilyedov.setText("");
                    itemnamev.setText("");
                    presyov.setText("");
                    pilav.setText("");
                    totalpayablev.setText("");
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbo.deleteOrders(Integer.valueOf(id.getText().toString()))){
                Toast.makeText(ViewRecords.this, "DELETED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                }else{
                Toast.makeText(ViewRecords.this, "DELETE FAILED!", Toast.LENGTH_SHORT).show();
            }
            }
        });
        }
    }


    //

package com.example.androiddatabase;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class ViewRecords extends AppCompatActivity {

    ListView list;
    EditText bookId,bookName,bookAuthor,bookPages;
    Button update, delete;
    DatabaseOperations dbop;
    ArrayAdapter adapter;
    ArrayList<String> getBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        list = (ListView) findViewById(R.id.list);
        bookId = (EditText) findViewById(R.id.bookId);
        bookName = (EditText) findViewById(R.id.bookName);
        bookAuthor = (EditText) findViewById(R.id.bookAuthor);
        bookPages = (EditText) findViewById(R.id.bookPages);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        dbop = new DatabaseOperations(this);
        getBooks = dbop.getAllBooks();

        adapter = new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,getBooks);
        list.setAdapter(adapter);


        //add event to listview
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewRecords.this,"The Selected Value is: "+position,Toast.LENGTH_LONG).show();
                bookId.setText(""+getBooks.get(position));
               /* bookName.setText(""+getBooks.get(position));
                bookAuthor.setText(""+getBooks.get(position));
                bookPages.setText(""+getBooks.get(position));*/
                String text[] = getBooks.get(position).split("-");
                bookId.setText(""+text[0]);
                bookName.setText(""+text[1]);
                bookAuthor.setText(""+text[2]);
                bookPages.setText(""+text[3]);
                /*Cursor res = dbop.getData(Integer.valueOf(text[0]));
                bookId.setText(""+res.getString(0));
                bookName.setText(""+res.getString(1));
                bookAuthor.setText(""+res.getString(2));
                bookPages.setText(""+res.getString(3));*/
            }
        });
        //delete
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbop.updateBook(Integer.valueOf(bookId.getText().toString()),bookName.getText().toString(),bookAuthor.getText().toString(),Integer.valueOf(bookPages.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"Successfully Updated!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ViewRecords.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed to Update!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        //delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbop.deleteBook(Integer.valueOf(bookId.getText().toString()))){
                    Toast.makeText(ViewRecords.this, "Successfully Deleted!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewRecords.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed to Delete!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}