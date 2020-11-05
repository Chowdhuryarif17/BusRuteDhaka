package com.example.chowdhuryarif.busrutedhaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllBusActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CustomAdapter customAdapter;

    public List<String> listDataBusName;
    public HashMap<String,List<String>> listDataBusFullRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bus);



        expandableListView = findViewById(R.id.expandableListViewId);

        prepareListData();

        customAdapter = new CustomAdapter(this,listDataBusName,listDataBusFullRoute);
        expandableListView.setAdapter(customAdapter);

    }
    void prepareListData(){

        String[] busNames = getResources().getStringArray(R.array.bus_names); //in this variable all bus name stored
        //routeNames = getResources().getStringArray(R.array.route_Names);//in this variable all bus RouteName stored
        String[]    busFullRoute = getResources().getStringArray(R.array.full_routes);//full route

        listDataBusName = new ArrayList<>();
        listDataBusFullRoute = new HashMap<>();

        for (int i = 0; i < busNames.length; i++) {
            //adding busName
            listDataBusName.add(busNames[i]);

            List<String> child = new ArrayList<>();
            child.add(busFullRoute[i]) ;

            listDataBusFullRoute.put(listDataBusName.get(i),child);
        }
    }

    public List<String> getListDataBusName() {
        return listDataBusName;
    }

    public HashMap<String, List<String>> getListDataBusFullRoute() {
        return listDataBusFullRoute;
    }
}
