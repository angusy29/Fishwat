package com.example.angusyuen.fishwat;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Browse extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Fish> allFish;

    private Context context = this;

    // front end stuff
    private Dialog dialog;   // dialog popup
    private Button closeButton;
    private Button sendButton;
    private EditText emailContent;
    WindowManager.LayoutParams lp;

    private RecyclerView fishRecyclerView;
    private RecyclerView.Adapter fishAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        initialiseGUI();
        initialiseBackend();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // when an item on the sidebar is pressed
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            // user is brought to page of fish to browse through
            Intent myIntent = new Intent(Browse.this, MainActivity.class);
            //myIntent.putExtra("key", 1); // this line is for if we want to send any information from this activity to the next
            Browse.this.startActivity(myIntent);
        } else if (id == R.id.nav_browse) {
            // do nothing because current page
        } else if (id == R.id.nav_report) {
            // modal popup to send email
            /*dialog.getWindow().setAttributes(lp);
            dialog.show();*/

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");

            emailIntent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.company_email));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Fishwat");
            //emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent.getText());

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();

                //Toast toast = new Toast(getApplicationContext());
                //toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT).show();
            } catch (android.content.ActivityNotFoundException ex) {

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // initialises all the front end, link it to the backend id's
    public void initialiseGUI() {
        fishRecyclerView = (RecyclerView) findViewById(R.id.fishRecyclerView);
        fishAdapter = new MyAdapter(allFish);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // reinitialising the sidebar drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // setting up recycler view cards
        mLayoutManager = new LinearLayoutManager(this);
        //dungeonRV.addItemDecoration(new SimpleDividerItemDecoration(this));
        fishRecyclerView.setAdapter(fishAdapter);
        fishRecyclerView.setLayoutManager(mLayoutManager);

        // popup dialog for contacting us
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.email_popup);

        emailContent = (EditText) dialog.findViewById(R.id.emailContent);

        // set the dialog Contact Us box to 90% of the window size
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        closeButton = (Button) dialog.findViewById(R.id.closeButton);
        sendButton = (Button) dialog.findViewById(R.id.sendButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.company_email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Fishwat");
                //emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent.getText());

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();

                    //Toast toast = new Toast(getApplicationContext());
                    //toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT).show();
                } catch (android.content.ActivityNotFoundException ex) {

                }
            }
        });
    }

    public void initialiseBackend() {
        allFish = new ArrayList<Fish>();
        SQLiteHelper helper = new SQLiteHelper(Browse.this);
        //allFish = helper.getAllRecords();


        // for each item in the database we want to create a new fish object
        for (int i = 0; i < 5; i++) {
            Fish newFish = new Fish("Salmon", "Salmonella", "Folklore has it that the fish return to the exact spot where " +
                    "they were born to spawn; tracking studies have shown this to be mostly true.",
                    "Recommended",1, null, null);
            allFish.add(newFish);
        }

        fishAdapter = new MyAdapter(allFish);
        fishRecyclerView.setAdapter(fishAdapter);
    }

}
