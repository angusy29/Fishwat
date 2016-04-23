package com.example.angusyuen.fishwat;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FishDetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context = this;

    private TextView fishName;
    private TextView description;
    private TextView consumptionStatus;
    private TextView seasonStatus;
    private ImageView fishPhoto;

    // front end stuff
    private Dialog dialog;   // dialog popup
    private Button closeButton;
    private Button sendButton;
    private EditText emailContent;
    WindowManager.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_details);

        initialiseGUI();
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
        getMenuInflater().inflate(R.menu.fish_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            // user is brought to page of fish to browse through
            Intent myIntent = new Intent(FishDetails.this, MainActivity.class);
            //myIntent.putExtra("key", 1); // this line is for if we want to send any information from this activity to the next
            FishDetails.this.startActivity(myIntent);
        } else if (id == R.id.nav_browse) {
            // user is brought to page of fish to browse through
            Intent myIntent = new Intent(FishDetails.this, Browse.class);
            //myIntent.putExtra("key", 1); // this line is for if we want to send any information from this activity to the next
            FishDetails.this.startActivity(myIntent);
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

        // popup dialog for contacting us
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.email_popup);

        emailContent = (EditText) dialog.findViewById(R.id.emailContent);

        // set the dialog Contact Us box to 90% of the window size
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        fishName = (TextView) findViewById(R.id.fishName);
        description = (TextView) this.findViewById(R.id.descriptionCard);
        consumptionStatus = (TextView) findViewById(R.id.consumptionStatusCard);
        seasonStatus = (TextView) findViewById(R.id.seasonStatusCard);
        fishPhoto = (ImageView) findViewById(R.id.largeFishPhoto);

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

        fishName.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("description"));
        String imageString = getIntent().getStringExtra("image");
        //imageString = imageString.substring(0, imageString.length() - 4);

        System.out.println(imageString);
        int resID = getResources().getIdentifier(imageString , "drawable", getPackageName());
        System.out.println(resID);
        fishPhoto.setImageResource(resID);

        if (getIntent().getExtras().getBoolean("isConsumable")) {
            consumptionStatus.setText("Fish is not consumable!");
        } else {
            consumptionStatus.setText("Fish is consumable!");
        }

        if (getIntent().getExtras().getBoolean("isSeasonable")) {
            seasonStatus.setText("This fish is currently not in season.");
        } else {
            seasonStatus.setText("Fish is in season!");
        }

    }
}
