package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.PhongTro_Adapter;
import com.phuongthao.app_nhatro.Models.PhongTro;
import com.phuongthao.app_nhatro.Models.PhongTro1;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    //    FloatingActionButton floatingActionButton;
    private ArrayList<PhongTro> phongTros;
    private RecyclerView recyclerView;
    private PhongTro_Adapter phongTro_adapter;
    SearchView inputSearch;


    FirebaseRecyclerOptions<PhongTro> options;
    DatabaseReference dataRef;
    FirebaseRecyclerAdapter<PhongTro, MyViewHolder> adapter;
    private PhongTro1_Adapter quanLyPhong1Adapter;
    private ArrayList<PhongTro1> datPhongTro1 = new ArrayList<>();

    //    String as;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("BẢNG TIN");
        dataRef = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        inputSearch = findViewById(R.id.inputSearch);


        bottomNaviation();
        recyclerView = findViewById(R.id.recyclerHome);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        phongTros = new ArrayList<>();



        checConnettion();
        LoadKH();
        //Tìm kiếm theo tên dùng afterTextChange


    }

    private void LoadKH() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datPhongTro1.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    PhongTro1 mode = ds.getValue(PhongTro1.class);
                    datPhongTro1.add(mode);
//                    Toast.makeText(MainActivity.this,ds.child("TenPhong").getValue().toString(),Toast.LENGTH_LONG).show();

                }

                quanLyPhong1Adapter = new PhongTro1_Adapter(Home.this, datPhongTro1);
                recyclerView.setAdapter(quanLyPhong1Adapter);
                quanLyPhong1Adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void Search(final String s) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datPhongTro1.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    PhongTro1 mode1 = ds.getValue(PhongTro1.class);

                    if (mode1.getTenPhong().toLowerCase().contains(s.toLowerCase())
                            || mode1.getGiaPhong().toLowerCase().contains(s.toLowerCase())) {

                        datPhongTro1.add(mode1);
                    }

                }

                quanLyPhong1Adapter = new PhongTro1_Adapter(Home.this, datPhongTro1);
                recyclerView.setAdapter(quanLyPhong1Adapter);
                quanLyPhong1Adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void bottomNaviation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
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

    protected void onStart() {
        super.onStart();
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query.trim())) {

                    Search(query.toLowerCase());


                } else {
                    LoadKH();

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText.trim())) {

                    Search(newText.toLowerCase());
                    //  Toast.makeText(MainActivity.this,newText,Toast.LENGTH_LONG).show();

                } else {
                    LoadKH();

                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
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

    public void checConnettion() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_SHORT).show();
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Data Network Enable", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
