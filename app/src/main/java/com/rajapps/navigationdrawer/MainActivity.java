package com.rajapps.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //.....................toolbar
       // setSupportActionBar(toolbar);
            //getSupportActionBar().hide();

        //.....................navigation drawer
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


    }//.............................

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.create_note:
                Intent intent = new Intent(MainActivity.this, create_note.class);
                startActivity(intent);
                break;

            case R.id.nav_rate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                            getPackageName())));
                       // google play

                } catch (Exception ex) {
                    startActivity(new
                            Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id=" +
                            getPackageName())));
                          // website google play

                }

                break;


            case R.id.nav_share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Share Quick Notes app with your friends and colleagues! With Quick Notes , you can quickly \ncapture and organize your notes, to-do lists, and ideas on the go. Whether you're a student,\n professional, or just someone who likes to stay organized, Quick Notes app has got you covered.\n Download now and start taking notes like a pro!\n" + "https://www.amazon.com/gp/product/B0BVWF2HL3";

                String shareSub = "Best App for organizing your Notes";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}







