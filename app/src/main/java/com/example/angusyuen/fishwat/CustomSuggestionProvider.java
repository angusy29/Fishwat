package com.example.angusyuen.fishwat;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomSuggestionProvider extends ContentProvider {

    SQLiteHelper helper;
    SQLiteQueryBuilder builder;
    SQLiteDatabase db;

    public CustomSuggestionProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        System.out.println("CustomSuggestionProvider created");
        helper = new SQLiteHelper(getContext());
        builder = new SQLiteQueryBuilder();
        db  = helper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        String query = uri.getLastPathSegment().toLowerCase();
        Cursor c = null;
        if (SearchManager.SUGGEST_URI_PATH_QUERY.equals(query)) {
            // user hasn’t entered anything
            // thus return a default cursor
        }
        else {
            // query contains the users search
            // return a cursor with appropriate data
            System.out.println(query);

            /*Map<String, String> projectionMap = new HashMap<String, String>();
            projectionMap.put(BaseColumns._ID, helper.COLUMN_ID + " AS " + BaseColumns._ID);
            projectionMap.put(SearchManager.SUGGEST_COLUMN_TEXT_1, "id AS " + SearchManager.SUGGEST_COLUMN_TEXT_1);
            projectionMap.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA, helper.COLUMN_ID + " AS " + SearchManager.SUGGEST_COLUMN_INTENT_DATA);
            builder.setProjectionMap(projectionMap);*/

            String str[] = new String[] { "ID as _id", "scientific_name"};
            String str1 = "id LIKE \'%" + query + "%\' OR scientific_name LIKE \'%" + query + "%\'";
            //String str1 = "id LIKE \'%" + query + "%\'";
            c = helper.getReadableDatabase().query("FISHES", str, str1, null, null, null, null);

            //c = builder.query(db, projection,selection, selectionArgs, null, null, sortOrder, null);
            return c;
        }
        return c;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
