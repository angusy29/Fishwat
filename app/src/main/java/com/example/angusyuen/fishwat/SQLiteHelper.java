package com.example.angusyuen.fishwat;

 import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;


/**
 * Created by emma on 23/04/16.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fishes.db";

    public static final String TABLE_NAME = "FISHES";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SCIENTIFIC_NAME = "SCIENTIFIC_NAME";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_RECOMMENDATION = "RECOMMENDATION";
    public static final Integer COLUMN_IN_SEASON = "IN_SEASON";
    public static final String COLUMN_IMAGES = "IMAGES";
    public static final String COLUMN_PRIMARY_IMAGE = "PRIMARY_IMAGE";

    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( "
                + COLUMN_ID + " VARCHAR PRIMARY KEY,"
                + COLUMN_SCIENTIFIC_NAME + " VARCHAR, "
                + COLUMN_DESCRIPTION+ " VARCHAR, "
                + COLUMN_RECOMMENDATION + " VARCHAR, "
                + COLUMN_IN_SEASON + " INTEGER, "
                + COLUMN_IMAGES + " VARCHAR, "
                + COLUMN_PRIMARY_IMAGE + " VARCHAR);");

        db.execSQL("insert into "+ TABLE_NAME + " values ("
        + "\"Pink brotula\", \"Brotula clarkae\", \"Fish....\", \"Acceptable\", 1, \"BRO1.JPG,BRO2.JPG,BRO3.JPG,BRO4.JPG,BRO5.JPG,BRO6.JPG\",  \"BRO2.JPG\")");

        db.execSQL("insert into "+ TABLE_NAME + " values ("
        + "\"Pacific crevalle jack\", \"Caranx caninus\", \"Fish...\", \"Acceptable\", 1, \"CAR1.JPG,CAR2.JPG\", \"CAR2.JPG\")");


        db.execSQL("insert into "+ TABLE_NAME + " values ("
                + "\"Yellowfin snook\", \"Centropomus robalito\", \"Fish...\", \"Recommended\", 0, \"CEN1.JPG,CEN2.JPG\", \"CEN1.JPG\")");

        db.execSQL("insert into "+ TABLE_NAME + " values ("
                + "\"Mahi-mahi (Dolphinfish)\", \"Coryphaena hippurus\", \"Fish...\", \"Recommended\", 1, \"COR1.JPG,COR2.JPG\", \"COR2.JPG\")");

        db.execSQL("insert into "+ TABLE_NAME + " values ("
                + "\"Whitefin weakfish\", \"Cynoscion albus\", \"Fish...\", \"Acceptable\", 0, \"CYNA1.JPG,CYNA2.JPG,CYNA3.JPG,CYNA4.JPG,CYNA5.JPG,CYNA6.JPG\", \"CYNA1.JPG\")");

        db.execSQL("insert into "+ TABLE_NAME + " values ("
                + "\"Weakfish\", \"Cynoscion squamipinnis\", \"Fish...\", \"Recommended\", 1, \"CYNS1.JPG\", \"CYNS1.JPG\")");

//        db.execSQL("insert into "+ TABLE_NAME + " values ("
//                + "\"Indo-Pacific sailfish\", \"Istiophorus platypterus\", \"Fish...\", \"Not\", \"\", \"\", \"\")");
//
//        db.execSQL("insert into "+ TABLE_NAME + " values ("
//                + "\"Striped marlin\", \"Kajikia audax\", \"Fish...\", \"Not\", \"\", \"\", \"\")");
//
//        db.execSQL("insert into "+ TABLE_NAME + " values ("
//                + "\"Yellow snapper\", \"Lutjanus argentiventris\", \"Fish...\", \"Acceptable\", \"\", \"\", \"\")");
//
//        db.execSQL("insert into "+ TABLE_NAME + " values ("
//                + "\"Blue marlin\", \"Makaira nigricans\", \"Fish...\", \"Not\", \"\", \"\", \"\")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    public void insertRecord(ContactModel contact) {
//        database = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
//        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
//        database.insert(TABLE_NAME, null, contentValues);
//        database.close();
//    }

    public ArrayList<Fish> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Fish> fishes = new ArrayList<Fish>();
        Fish fish;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                /*fish = new Fish(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5));
                fishes.add(fish);*/
            }
        }
        cursor.close();
        database.close();

        return fishes;
    }

//    public void updateRecord(ContactModel contact) {
//        database = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
//        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
//        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getID()});
//        database.close();
//    }


    public void deleteAllRecords() {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

//    public void deleteRecord(ContactModel contact) {
//        database = this.getReadableDatabase();
//        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{contact.getID()});
//        database.close();
//    }


    public ArrayList<String> getAllTableName()
    {
        database = this.getReadableDatabase();
        ArrayList<String> allTableNames=new ArrayList<String>();
        Cursor cursor=database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        if(cursor.getCount()>0)
        {
            for(int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                allTableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        }
        cursor.close();
        database.close();
        return allTableNames;
    }

}