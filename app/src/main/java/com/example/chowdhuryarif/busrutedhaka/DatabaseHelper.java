package com.example.chowdhuryarif.busrutedhaka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    UserDetails userDetails = new UserDetails();


    private static final String DATABASE_NAME = "bus_route";
    private static final int VERSION_NUMBER = 1;
    //bus table create
    private static final String TABLE_NAME1 = "bus";
    private static final String BUS_ID = "bus_id";
    private static final String BUS_NAME = "bus_name";
    private static final String CREATE_BUS_TABLE = "CREATE TABLE "+TABLE_NAME1+" ("+BUS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BUS_NAME+" varchar(255) NOT NULL);";
    private static final String DROP_BUS_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME1;
    //private static final String SELECT_ALL = "SELECT * FROM "+TABLE_NAME;

    //Place table create
    private static final String TABLE_NAME2 = "place";
    private static final String PLACE_ID = "place_id";
    private static final String PLACE_NAME = "place_name";
    private static final String CREATE_PLACE_TABLE = "CREATE TABLE "+TABLE_NAME2+" ("+PLACE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PLACE_NAME+" varchar(255) NOT NULL);";
    private static final String DROP_PLACE_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME2;

    //cover table create
    private static final String TABLE_NAME3 = "cover";
    //for column
    private static final String COVER_ID = "cover_id";
    private static final String CREATE_COVER_TABLE = "CREATE TABLE "+TABLE_NAME3+" ("+COVER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BUS_ID+" INTEGER, "+PLACE_ID+" INTEGER, FOREIGN KEY ("+BUS_ID+") REFERENCES "+TABLE_NAME1+" ("+BUS_ID+"), FOREIGN KEY ("+PLACE_ID+") REFERENCES "+TABLE_NAME2+" ("+PLACE_ID+"));";
    private static final String DROP_COVER_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME3;

    String searchBusItem = "Turag";

//    public void setSearchBusItem(String searchBusItem) {
//        this.searchBusItem = searchBusItem;
//    }

    private Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context, "onCreate is called : ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_BUS_TABLE);
            sqLiteDatabase.execSQL(CREATE_PLACE_TABLE);
            sqLiteDatabase.execSQL(CREATE_COVER_TABLE);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "onUpgrade is called : ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_BUS_TABLE);
            sqLiteDatabase.execSQL(CREATE_PLACE_TABLE);
            sqLiteDatabase.execSQL(CREATE_COVER_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_LONG).show();
        }

    }


    public long insertBusData(UserDetails userDetails){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUS_NAME, userDetails.getBusName());

        long rowId = sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);
        return rowId;
    }
    public long insertPlaceData(UserDetails userDetails){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLACE_NAME, userDetails.getPlaceName());

        long rowId = sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);
        return rowId;
    }
    public long insertCoverData(UserDetails userDetails){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUS_ID, userDetails.getFkBusId());
        contentValues.put(PLACE_ID, userDetails.getFkPlaceId());

        long rowId = sqLiteDatabase.insert(TABLE_NAME3, null, contentValues);
        return rowId;
    }

    //listview display
    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Toast.makeText(context, "onUpgrade is called : "+searchBusItem, Toast.LENGTH_LONG).show();
        Cursor cursor = null;


        cursor = sqLiteDatabase.rawQuery("SELECT "+BUS_NAME+" FROM "+TABLE_NAME1+" WHERE "+BUS_NAME+" in "+searchBusItem ,null);
        return cursor;
    }
}
