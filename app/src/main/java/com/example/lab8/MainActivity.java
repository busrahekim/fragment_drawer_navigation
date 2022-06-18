package com.example.lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lab8.Fragment.HomeFragment;
import com.example.lab8.Fragment.MoviesFragment;
import com.example.lab8.Fragment.NotificationFragment;
import com.example.lab8.Fragment.PhotoFragment;
import com.example.lab8.Fragment.SettingsFragment;
import com.example.lab8.Other.AboutUs;
import com.example.lab8.Other.PrivacyPolicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("", null).show());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        TextView name = findViewById(R.id.pageName);

        switch(id) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_photo:{
                fragment = new PhotoFragment();
                name.setText("Photo");
                break;}
            case R.id.nav_movie:
                fragment = new MoviesFragment();
                name.setText("Movies");
                break;
            case R.id.nav_notification:
                fragment = new NotificationFragment();
                name.setText("Notifications");
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                name.setText("Settings");
                break;
            case R.id.nav_about_us:
                intent = new Intent(this, AboutUs.class);
                name.setText("About Us");
                break;
            case R.id.nav_privacy_policy:
                intent = new Intent(this, PrivacyPolicy.class);
                name.setText("Privacy Policy");
                break;
            default:
                fragment = new HomeFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}