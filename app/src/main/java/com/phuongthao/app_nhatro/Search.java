package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.ListMotelAdapter;
import com.phuongthao.app_nhatro.Adapter.ListPhongTro_Adapter;
import com.phuongthao.app_nhatro.Adapter.SearchAdapter;
import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private ArrayList<ListPhongTro> listPhongTros;
    private RecyclerView recyclerView;
    private ListPhongTro_Adapter listPhongTro_adapter ;
    FloatingActionButton floatingActionButton_Add_NhaTro;

    private ArrayList<ListPhongTroSearch> listPhongTroSearch = new ArrayList<>();
    private ListMotelAdapter listMotelAdapter;

    SearchView inputSearch;

    FirebaseRecyclerOptions<ListPhongTro> options;
    DatabaseReference dataRef;
    FirebaseRecyclerAdapter<ListPhongTro, MyListPhong> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setTitle("LIST NHÀ TRỌ");
        checConnettion();
        dataRef = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        bottomNaviation();
        recyclerView = findViewById(R.id.recyclerSearch);
        listPhongTros = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        inputSearch = findViewById(R.id.inputSearch);

        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!TextUtils.isEmpty(query.trim())){
                    Search(query.toLowerCase());
                }else{
                    Load();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText.trim())){

                    Search(newText.toLowerCase());
                }else{
                    Load();
                }
                return false;
            }
        });
     //   LoadData("");
        Load();
    }

    private void Load() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPhongTroSearch.clear();

                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    ListPhongTroSearch model = ds.getValue(ListPhongTroSearch.class);
                    listPhongTroSearch.add(model);

                }
                listMotelAdapter = new ListMotelAdapter(Search.this,listPhongTroSearch);
                recyclerView.setAdapter(listMotelAdapter);
                listMotelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Search(final String s) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPhongTroSearch.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    ListPhongTroSearch model = ds.getValue(ListPhongTroSearch.class);
                    if (model.getTenPhong().toLowerCase().contains(s.toLowerCase())){

                        listPhongTroSearch.add(model);
                    }



                }
                listMotelAdapter = new ListMotelAdapter(Search.this,listPhongTroSearch);
                recyclerView.setAdapter(listMotelAdapter);
                listMotelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void LoadData() {
//        fetch dữ liệu tù firebase realtime và firebase storage về
        //Tạo biến truy vần dữ liệu query dùng để search live data
        Query query = dataRef.orderByChild("TenPhong");
//        Firebase option như tut hướng dẫn thay Car.class thành new SnapshotParser<Car>
        options = new FirebaseRecyclerOptions.Builder<ListPhongTro>().setQuery(query, new SnapshotParser<ListPhongTro>() {
            @NonNull
            @Override
            public ListPhongTro parseSnapshot(@NonNull DataSnapshot snapshot) {
                Log.d("aaa", snapshot.child("TenPhong").getValue().toString());
                Log.d("aaa", snapshot.child("ImageUrl").getValue().toString());
                return new ListPhongTro(snapshot.child("TenPhong").getValue().toString(), snapshot.child("ImageUrl").getValue().toString(), snapshot.getKey());
            }
        }).build();
        adapter = new FirebaseRecyclerAdapter<ListPhongTro, MyListPhong>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyListPhong holder, final int position, @NonNull final ListPhongTro model) {
                //Hàm đổ dữ liệu về recyclerview
                holder.textView1List.setText(model.getTenPhong());
                //dùng thư viện picasso load ảnh ra
                Picasso.with(getApplicationContext()).load(model.getImage_Phong()).into(holder.imageViewNenList);
                //Click vào mỗi phần tử sẽ chuyển sang ViewActivity
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Search.this, SoPhongTroActivity.class);
                        intent.putExtra("TenPhongTroHA", model.getTenPhong());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyListPhong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_list_phong, parent, false);
                return new MyListPhong(v);
            }

        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void LoadDataSearch(String data) {
//        fetch dữ liệu tù firebase realtime và firebase storage về
        //Tạo biến truy vần dữ liệu query dùng để search live data
        Query query = dataRef.orderByChild("TenPhong").startAt(data).endAt(data + "\uf8ff");
//        Firebase option như tut hướng dẫn thay Car.class thành new SnapshotParser<Car>
        options = new FirebaseRecyclerOptions.Builder<ListPhongTro>().setQuery(query, new SnapshotParser<ListPhongTro>() {
            @NonNull
            @Override
            public ListPhongTro parseSnapshot(@NonNull DataSnapshot snapshot) {
                Log.d("aaa", snapshot.child("TenPhong").getValue().toString());
                Log.d("aaa", snapshot.child("ImageUrl").getValue().toString());
                return new ListPhongTro(snapshot.child("TenPhong").getValue().toString(), snapshot.child("ImageUrl").getValue().toString(), snapshot.getKey());
            }
        }).build();
        adapter = new FirebaseRecyclerAdapter<ListPhongTro, MyListPhong>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyListPhong holder, final int position, @NonNull final ListPhongTro model) {
                //Hàm đổ dữ liệu về recyclerview
                holder.textView1List.setText(model.getTenPhong());
                //dùng thư viện picasso load ảnh ra
                Picasso.with(getApplicationContext()).load(model.getImage_Phong()).into(holder.imageViewNenList);
                //Click vào mỗi phần tử sẽ chuyển sang ViewActivity
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Search.this, SoPhongTroActivity.class);
                        intent.putExtra("TenPhongTroHA", model.getTenPhong());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyListPhong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_list_phong, parent, false);
                return new MyListPhong(v);
            }

        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean flag1 = false;
        switch (item.getItemId()){
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
            case  R.id.nav_account:
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
    public void checConnettion(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (null!=activeNetwork){
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_SHORT).show();
            }
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Data Network Enable", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

}
