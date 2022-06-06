package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyGoogleMap extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;
    double lat,lon;
    String vitri, PhongTrokey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_google_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);
        Bundle bd = getIntent().getExtras();

         PhongTrokey = bd.getString("key");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapAPI = googleMap;
//        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null){
//                    double lat = location.getAltitude();
//                    double longt = location.getLongitude();
//
//
//                }
//            }
//        });
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");

        ref.child(PhongTrokey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    lat = Double.parseDouble(dataSnapshot.child("lat").getValue().toString());
                    lon = Double.parseDouble(dataSnapshot.child("lon").getValue().toString());
                    Log.v("aaa", String.valueOf(lat));
                    Log.v("aaa", String.valueOf(lon));
                    vitri = dataSnapshot.child("VitriPhong").getValue().toString();
                    Log.v("aaa", vitri);
                    LatLng HoaAn = new LatLng(lat,lon);
                    mapAPI.addMarker(new MarkerOptions().position(HoaAn).title(vitri));
                    mapAPI.moveCamera(CameraUpdateFactory.newLatLng((HoaAn)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
