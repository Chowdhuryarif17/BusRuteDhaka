package com.example.chowdhuryarif.busrutedhaka;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    public ListView listView;
    public DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView = findViewById(R.id.listViewId);
        databaseHelper = new DatabaseHelper(this);

        View inflatedView = getLayoutInflater().inflate(R.layout.activity_first, null);
        AutoCompleteTextView searchBus =  inflatedView.findViewById(R.id.autoCompleteTextViewBusNameId);

        loadData();
    }

    public void loadData(){

        ArrayList<String> listData = new ArrayList<>();
        //String searchItem = searchId.getText().toString();
        Cursor cursor = databaseHelper.showAllData();

        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No data is found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0));

            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textViewId,listData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectValue = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected value : "+selectValue, Toast.LENGTH_LONG).show();
            }
        });
    }
}