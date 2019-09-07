package com.example.personal.newstart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.LOCATION_SERVICE;

public class MessageFragment extends Fragment {

   // ImageView sos;
    Button sos;
    SmsManager sms;
    Button o_f_d;
    int delay = SettingsFragment.set_time*1000; //milliseconds
    String message ;
    String finalMessage = "";
    String defaultMessage ="SAVE ME. I might be in distress. If I don't pick your call then I might be in a real trouble.";
    int j=0 ;
    TextView tv ;
    public static Location LOC;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FusedLocationProviderClient mFusedLocationClient;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        j=0;
        sms = SmsManager.getDefault();
        o_f_d = view.findViewById(R.id.out);

        sos = view.findViewById(R.id.sos_button);
        tv = view.findViewById(R.id.textView);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);

        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d("tag" , "Goes Inside The onLocationChanged");
                LOC = location;
      //          Toast.makeText(getContext() , "INSIDE onLocChanged" , Toast.LENGTH_SHORT ).show();
                message ="http://maps.google.com/maps?daddr=" + location.getLatitude() + "," + location.getLongitude() + "&amp;ll=";
                tv.setText("\n " + "http://maps.googleapis.com/maps/api/geocode/json?latlng="+location.getLatitude()+"," +location.getLongitude()+"&sensor=true" );
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




        sos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                j=0;
                sos.setVisibility(View.INVISIBLE);
                o_f_d.setVisibility(View.VISIBLE);
                Date c = Calendar.getInstance().getTime();
              //  System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                final String formattedDate = df.format(c);
                if (CustomFragment.Message!=null)
                    defaultMessage = CustomFragment.Message;

               // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
               // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0 , 0,locationListener );
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0 , 0,locationListener );
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                finalMessage = message+CustomFragment.Message+formattedDate;
               handler.postDelayed(new Runnable() {
                    public void run() {
                        //do something
                        if (j==0){
                           // finalMessage = message+defaultMessage+formattedDate;
                      //      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0 , 0,locationListener );
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                            updateMessage();
                            Log.d("OnCLick" , "The Value Of J is" +j);
                            sms.sendTextMessage("9428457422", null, finalMessage, null, null);
                            handler.postDelayed(this, (SettingsFragment.set_time*1000));

                        }

                        else if (j==1){

                            sos.setVisibility(View.VISIBLE);
                            o_f_d.setVisibility(View.INVISIBLE);
                            return;

                        }
                     //   sms.sendTextMessage("9428457422", null, message, null, null);
                     //   handler.postDelayed(this, delay);
                    }
                }, (SettingsFragment.set_time*1000));

                Toast.makeText(getContext() , "Will Be Sending An SMS" , Toast.LENGTH_SHORT).show();
               // sms.sendTextMessage("9428457422", null, finalMessage, null, null);
            }
        });

        o_f_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j=1;
                o_f_d.setVisibility(View.INVISIBLE);
                sos.setVisibility(View.VISIBLE);
            }

        });

        return view;
    }

    private void updateMessage() {

        Toast.makeText(getContext(),"Inside update Text",Toast.LENGTH_SHORT).show();
        finalMessage = CustomFragment.Message + message ;
        //return;
    }

    @Override
    public void onPause() {
        super.onPause();

        Toast.makeText(getContext() , "INSIDE onPause" , Toast.LENGTH_SHORT).show();
        Log.d("Pause" , "Inside OnPause Method");
        if (sos.getVisibility()==View.VISIBLE){

        }
    }
}

