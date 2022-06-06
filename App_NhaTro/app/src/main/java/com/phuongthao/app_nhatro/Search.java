package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.phuongthao.app_nhatro.Adapter.ListPhongTro_Adapter;
import com.phuongthao.app_nhatro.Adapter.PhongTro_Adapter;
import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.Models.PhongTro;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private ArrayList<ListPhongTro> listPhongTros;
    private RecyclerView recyclerView;
    private ListPhongTro_Adapter listPhongTro_adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bottomNaviation();
        recyclerView = findViewById(R.id.recyclerSearch);
        listPhongTros = new ArrayList<>();
        createHeroList();

        listPhongTro_adapter = new ListPhongTro_Adapter(this,listPhongTros);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(listPhongTro_adapter);
    }
    private void bottomNaviation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean flag = false;
                switch (item.getItemId()){
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


                }
                return flag;
            }
        });
    }
    private void createHeroList() {
        listPhongTros.add(new ListPhongTro("Sơn",R.drawable.anhtro2, "","Nguyen Tri Phuong"));
        listPhongTros.add(new ListPhongTro("g",R.drawable.anhtro2, "","Nguyen Tri Phuong"));
        listPhongTros.add(new ListPhongTro("j",R.drawable.anhtro2, "","Nguyen Tri Phuong"));
        listPhongTros.add(new ListPhongTro("k",R.drawable.anhtro2, "","Nguyen Tri Phuong"));
        listPhongTros.add(new ListPhongTro("l",R.drawable.anhtro2, "","Nguyen Tri Phuong"));

    }

}
