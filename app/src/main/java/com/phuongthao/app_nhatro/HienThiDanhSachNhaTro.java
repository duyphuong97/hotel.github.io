package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.BangDuyetAdapter;
import com.phuongthao.app_nhatro.Adapter.SearchAdapter;
import com.phuongthao.app_nhatro.Models.DanhSachDaDuyet;
import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;

import java.util.ArrayList;

public class HienThiDanhSachNhaTro extends AppCompatActivity {

    private ArrayList<ListPhongTroSearch> listPhongTroSearches = new ArrayList<>();
    DatabaseReference dataRef;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_danh_sach_nha_tro);
        getSupportActionBar().setTitle("DANH SÁCH NHÀ TRỌ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dataRef = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        recyclerView = findViewById(R.id.recyclerHienThiNhaTro);
        // thiếu 2 dòng dưới thì ăn cám!!!! haha! fix lâu quá cmt cho nhớ. Thiếu nó không thể hiển thị lên.
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPhongTroSearches.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ListPhongTroSearch model = ds.getValue(ListPhongTroSearch.class);
                    listPhongTroSearches.add(model);

                }
                searchAdapter = new SearchAdapter(HienThiDanhSachNhaTro.this,listPhongTroSearches);

                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
