package com.example.chowdhuryarif.busrutedhaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText busNameET, placeNameET, fkBusIdET, fkPlaceIdET;
    private Button insertBusId, insertPlaceId, insertCoverId;

    UserDetails userDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        busNameET = findViewById(R.id.busNameId);
        insertBusId = findViewById(R.id.insertBusId);

        placeNameET = findViewById(R.id.placeNameId);
        insertPlaceId = findViewById(R.id.insertPlaceId);

        fkBusIdET = findViewById(R.id.fkBusId);
        fkPlaceIdET = findViewById(R.id.fkPlaceId);
        insertCoverId = findViewById(R.id.insertCoverId);

        userDetails = new UserDetails();
        databaseHelper = new DatabaseHelper(this);


        insertBusId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String busName = busNameET.getText().toString();

                userDetails.setBusName(busName);

                long rowid = databaseHelper.insertBusData(userDetails);

                //checking data inserted or not
                if(rowid>0){
                    Toast.makeText(getApplicationContext(),"Row " + rowid + " is succesfully inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Row " + rowid + " is insertion failled", Toast.LENGTH_LONG).show();
                }
            }
        });
        insertPlaceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String placeName = placeNameET.getText().toString();

                userDetails.setPlaceName(placeName);

                long rowid = databaseHelper.insertPlaceData(userDetails);

                //checking data inserted or not
                if(rowid>0){
                    Toast.makeText(getApplicationContext(),"Row " + rowid + " is succesfully inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Row " + rowid + " is insertion failled", Toast.LENGTH_LONG).show();
                }
            }
        });
        insertCoverId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fkBusId = fkBusIdET.getText().toString();
                int busId = Integer.parseInt(fkBusId);
                String fkPlaceId = fkPlaceIdET.getText().toString();
                int placeId = Integer.parseInt(fkPlaceId);

                userDetails.setFkBusId(busId);
                userDetails.setFkPlaceId(placeId);

                long rowid = databaseHelper.insertCoverData(userDetails);

                //checking data inserted or not
                if(rowid>0){
                    Toast.makeText(getApplicationContext(),"Row " + rowid + " is succesfully inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Row " + rowid + " is insertion failled", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
