package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.phuongthao.app_nhatro.Adapter.PhongTro_Adapter;
import com.phuongthao.app_nhatro.Models.PhongTro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PhongTro> phongTros;
    private RecyclerView recyclerView;
    private PhongTro_Adapter phongTro_adapter ;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertDta_Activity.class);
                startActivity(intent);
            }
        });

        bottomNaviation();

        recyclerView = findViewById(R.id.recyclerHome);
        phongTros = new ArrayList<>();
        createHeroList();


        phongTro_adapter = new PhongTro_Adapter(this,phongTros);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(phongTro_adapter);

    }
    private void bottomNaviation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
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
        phongTros.add(new PhongTro("Sơn",R.drawable.anhtro2,300, "Nguyen Tri Phuong"));
        phongTros.add(new PhongTro("g",R.drawable.anhtro2,300, "Nguyen Tri Phuong"));
        phongTros.add(new PhongTro("j",R.drawable.anhtro2,300, "Nguyen Tri Phuong"));
        phongTros.add(new PhongTro("k",R.drawable.anhtro2,300, "Nguyen Tri Phuong"));
        phongTros.add(new PhongTro("l",R.drawable.anhtro2,300, "Nguyen Tri Phuong"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                phongTro_adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
