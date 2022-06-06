package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.PhongTro_Adapter;
import com.phuongthao.app_nhatro.Adapter.SearchAdapter;
import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;
import com.phuongthao.app_nhatro.Models.PhongTro;
import com.phuongthao.app_nhatro.Models.PhongTro1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Search_Near extends AppCompatActivity {

    private ArrayList<ListPhongTroSearch> listPhongTroSearch = new ArrayList<>();
    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;

    SupportMapFragment mapFragment;
    ArrayList<LatLng> listPoints;
    FusedLocationProviderClient client;
    GoogleMap mMap;
    double lat,lon;
    ArrayList<String> arrayListToaDo;
    ArrayList<String> arrayListTitle;
    ArrayList<Double> arrayListKhoangCach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__near);

        getSupportActionBar().setTitle("TÌM KIẾM GẦN");
        recyclerView = findViewById(R.id.recyclerSearch_Near);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.Fm_maps_Near);

        bottomNaviation();
        LayTatCaToaDoNhaTro();


        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;
//
                            mMap.clear();
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Tôi đang ở đây!");
                            Log.d("ToadoHT", location.getLatitude() +  "," + location.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            mMap.addMarker(options);
                            Log.d("array", arrayListToaDo.size() + "");
                            int i = 0;
                            arrayListKhoangCach = new ArrayList<>();
                            for (String arr : arrayListToaDo){
                                String[] arrOfStr = arr.split(",", 2);
                                LatLng latLng1 = new LatLng(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[1]));
                                MarkerOptions options1 = new MarkerOptions().position(latLng1).title(arrayListTitle.get(i));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 10));
                                mMap.addMarker(options1);
                                float [] dist = new float[1];
                                Location.distanceBetween(location.getLatitude(), location.getLongitude(), Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[1]), dist );
//                                Log.d("dist", String.valueOf(dist[0]));
                                arrayListKhoangCach.add((double) dist[0]);
                                Collections.sort(arrayListKhoangCach);
                                Log.d("ListKC", String.valueOf(arrayListKhoangCach));
                                i++;

                            }


                        }
                    });
                }
            }
        });
    }

    private void bottomNaviation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_Search_QuanhDay);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean flag = false;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        flag = true;
                        break;
                    case R.id.nav_search:
                        Intent intent1 = new Intent(getApplicationContext(), Search.class);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0, 0);
                        flag = true;
                        break;
                    case R.id.nav_account:
                        Intent intent2 = new Intent(getApplicationContext(), Account.class);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0, 0);
                        flag = true;
                        break;
                    case  R.id.nav_Search_QuanhDay:
                        Intent intent3 = new Intent(getApplicationContext(), Search_Near.class);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0, 0);
                        flag = true;
                        Toast.makeText(getApplicationContext(), "Bạn đã chọn Search quanh đây", Toast.LENGTH_SHORT).show();
                        break;

                }
                return flag;
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean flag1 = false;
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                flag1 = true;
                Toast.makeText(this, "Bạn đã chọn HOME", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_search:
                Intent intent1 = new Intent(getApplicationContext(), Search.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(0, 0);
                flag1 = true;
                Toast.makeText(this, "Bạn đã chọn ListMotel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_account:
                Intent intent2 = new Intent(getApplicationContext(), Account.class);
                startActivity(intent2);
                finish();
                overridePendingTransition(0, 0);
                flag1 = true;
                Toast.makeText(this, "Bạn đã chọn Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Search_QuanhDay:
                Intent intent3 = new Intent(getApplicationContext(), Search_Near.class);
                startActivity(intent3);
                finish();
                overridePendingTransition(0, 0);
                flag1 = true;
                Toast.makeText(this, "Bạn đã chọn Search quanh đây", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void LayTatCaToaDoNhaTro(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPhongTroSearch.clear();

                arrayListToaDo = new ArrayList<>();
                arrayListTitle = new ArrayList<>();
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    ListPhongTroSearch model = ds.getValue(ListPhongTroSearch.class);
                    listPhongTroSearch.add(model);
//                    Log.d("Toado", model.getToaDo());
                    arrayListToaDo.add(model.getToaDo());
                    arrayListTitle.add(model.getTenPhong());
//                    Log.d("TenP", model.getTenPhong());


                }
                searchAdapter = new SearchAdapter(Search_Near.this,listPhongTroSearch);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
