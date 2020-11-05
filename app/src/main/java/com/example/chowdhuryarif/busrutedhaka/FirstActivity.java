package com.example.chowdhuryarif.busrutedhaka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    TextView testTextView;

    DatabaseHelper databaseHelper;
    public ListView listView;
    public Fragment fragment;
    UserDetails userDetails;

//    private Button insertButton;
    private Button allBusButton;
    private AutoCompleteTextView autoCompleteTextViewBusName, autoCompleteTextViewSourceName, autoCompleteTextViewDestinationName;
    private Button searchByBusNameButton, searchBySourceDestinationButton;

    ///for expandableListView
    private ExpandableListView expandableListView;
    private CustomAdapter customAdapter;


    public List<String> listDataBusName;
    public HashMap<String, List<String>> listDataBusFullRoute;

    String[] busNames, routeNames;
    String[] busFullRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        busNames = getResources().getStringArray(R.array.bus_names); //in this variable all bus name stored
        routeNames = getResources().getStringArray(R.array.route_Names);//in this variable all bus RouteName stored
        busFullRoute = getResources().getStringArray(R.array.full_routes);//full routes assignend

        findViewByIdHere();

        prepareData();

        userDetails = new UserDetails();

        //loadData();

        customAdapter = new CustomAdapter(this, listDataBusName, listDataBusFullRoute);
        expandableListView.setAdapter(customAdapter);




//        insertButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FirstActivity.this, InsertActivity.class);
//                startActivity(intent);
//            }
//        });

        allBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, AllBusActivity.class);
                startActivity(intent);
            }
        });


        //All bus search
        searchByBusNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedBusName = autoCompleteTextViewBusName.getText().toString();
                //Boolean result = false;

                testTextView.setText(autoCompleteTextViewBusName.getText().toString()); //this one is selected busName
                for (int i = 0; i < busNames.length; i++) {
                    //adding busName

                    if(busNames[i].equals(autoCompleteTextViewBusName.getText())){
                        listDataBusName.add(busNames[i]);

                        List<String> child = new ArrayList<>();
                        child.add(busFullRoute[i]) ;

                        listDataBusFullRoute.put(listDataBusName.get(i),child);

                    }

                }
                // Intent intent = new Intent(FirstActivity.this, ListDataActivity.class);
                //  startActivity(intent);
            }
        });

        customAdapter = new CustomAdapter(this, listDataBusName, listDataBusFullRoute);
        expandableListView.setAdapter(customAdapter);
    }

    void findViewByIdHere() {
//        insertButton = findViewById(R.id.insertButtonId);
        allBusButton = findViewById(R.id.allBusButtonId);

        expandableListView = findViewById(R.id.searchResultExpandableListViewId);

        autoCompleteTextViewBusName = findViewById(R.id.autoCompleteTextViewBusNameId);
        autoCompleteTextViewSourceName = findViewById(R.id.autoCompleteTextViewSourceNameId);
        autoCompleteTextViewDestinationName = findViewById(R.id.autoCompleteTextViewDestinationNameId);
        searchByBusNameButton = findViewById(R.id.searchByBusNameButtonId);
        searchBySourceDestinationButton = findViewById(R.id.searchBySourceDestinationButtonId);


        listView = findViewById(R.id.listViewId);
        testTextView = findViewById(R.id.textViewId);
    }

    void prepareData() {

        listDataBusName = new ArrayList<>();
        listDataBusFullRoute = new HashMap<>();


        for (int i = 0; i < busNames.length; i++) {
            //adding busName
            listDataBusName.add(busNames[i]);

            List<String> child = new ArrayList<>();
            child.add(busFullRoute[i]) ;

            listDataBusFullRoute.put(listDataBusName.get(i),child);
        }



        //for autoCompleteTextView
        ArrayAdapter<String> adapterForBusNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, busNames);  //for buses
        ArrayAdapter<String> adapterForRouteNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, routeNames); //for routes

        //for bus
        autoCompleteTextViewBusName.setThreshold(1);
        autoCompleteTextViewBusName.setAdapter(adapterForBusNames);
        //Toast.makeText(FirstActivity.this,autoCompleteTextViewBusName.getText().toString(),Toast.LENGTH_LONG).show();

        //for source
        autoCompleteTextViewSourceName.setThreshold(1);
        autoCompleteTextViewSourceName.setAdapter(adapterForRouteNames);

        //for destination
        autoCompleteTextViewDestinationName.setThreshold(1);
        autoCompleteTextViewDestinationName.setAdapter(adapterForRouteNames);
    }


}
