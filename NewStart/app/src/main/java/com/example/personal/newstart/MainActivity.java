package com.example.personal.newstart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    int open_first_time = 0;
    String[] permissions = new String[]{Manifest.permission.READ_CONTACTS , Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.CALL_PHONE ,
    Manifest.permission.SEND_SMS};
    public static NavigationView navigationView ;
    String[] num;
    private DrawerLayout drawer;
    LocationManager locationManager;
    LocationListener locationListener;
    public static String message;
    Cursor c;
    int choice ;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ContactViewFragment.justToTrigger();
        askForAllPermisiions();
        startFindingLocation();
        myDb = new DatabaseHelper(this);
        choice = myDb.tellCount();
  //      myDb = new DatabaseHelper(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer , toolbar ,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);

            drawer.addDrawerListener(toggle);
            toggle.syncState();


       if (choice < 1){
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                   new ContactFragment()).commit();
           navigationView.setCheckedItem(R.id.nav_contacts);
       }
       else {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                   new MessageFragment()).commit();
           navigationView.setCheckedItem(R.id.nav_message);
       }

      /*  if (open_first_time==0){
            open_first_time = 1;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                    new ContactFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_contacts);
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                    new MessageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);

        } */

    }

    private void startFindingLocation() {

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                message = "http://maps.google.com/maps?daddr="+location.getLatitude()+","+location.getLongitude()+"&amp;ll=";
            //    tv.append("\n " + "http://maps.googleapis.com/maps/api/geocode/json?latlng="+location.getLatitude()+"," +location.getLongitude()+"&sensor=true" );
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

    }

    private void askForAllPermisiions() {

        if (ActivityCompat.checkSelfPermission(this  , Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this , Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this , Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this , new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.CALL_PHONE
            } , 10);
        }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case 10:
                Toast.makeText(this , "All Permiisions Granted" , Toast.LENGTH_SHORT);
                break;
        }

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new MessageFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_message);
                break;

            case R.id.nav_contacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new ContactFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_contacts);
                break;

            case R.id.nav_call_feature:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new CallingFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_call_feature);
                break;

            case R.id.nav_contacts_view:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactViewFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_contacts_view);

                break;

            case R.id.nav_custom_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CustomFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_custom_message);
                break;
            case R.id.nav_nearby_sos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new SOSNearbyFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_nearby_sos);
                break;

            case R.id.nav_cry_out_loud:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new FragmentCry()).commit();
                navigationView.setCheckedItem(R.id.nav_cry_out_loud);
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new SettingsFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_settings);
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fragment_aboutus()).commit();
                navigationView.setCheckedItem(R.id.nav_about_us);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
//        setNavigationViewCheckedItem();
        return true;
    }


    private void setNavigationViewCheckedItem() {
        if (this.getClass().equals(ContactFragment.class)) {
            navigationView.setCheckedItem(R.id.nav_contacts);
        } else if (this.getClass().equals(CallingFragment.class)) {
            navigationView.setCheckedItem(R.id.nav_call_feature);
        }
        else if (this.getClass().equals(MessageFragment.class)) {
            navigationView.setCheckedItem(R.id.nav_message);
        }
        else if (this.getClass().equals(SOSNearbyFragment.class)) {
            navigationView.setCheckedItem(R.id.nav_nearby_sos);
        }
    }
}
