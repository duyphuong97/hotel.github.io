package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.ListPhongTro_Adapter;
import com.phuongthao.app_nhatro.Adapter.SearchAdapter;
import com.phuongthao.app_nhatro.Adapter.UserChuTroAdapter;
import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;
import com.phuongthao.app_nhatro.Models.UserChuTro;

import java.util.ArrayList;

public class HienThiUserChuTro extends AppCompatActivity {
    private ArrayList<UserChuTro> chuTroArrayList = new ArrayList<>();
    DatabaseReference dataRef;
    private RecyclerView recyclerView;
    private UserChuTroAdapter userChuTroAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_user_chu_tro);

        getSupportActionBar().setTitle("DANH SÁCH EMAIL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dataRef = FirebaseDatabase.getInstance().getReference().child("UsersChuTro");

        recyclerView = findViewById(R.id.recyclerHienThiUser);
        // thiếu 2 dòng dưới thì ăn cám!!!! haha! fix lâu quá cmt cho nhớ. Thiếu nó không thể hiển thị lên.
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chuTroArrayList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    UserChuTro model = ds.getValue(UserChuTro.class);
                    chuTroArrayList.add(model);

                }
                userChuTroAdapter = new UserChuTroAdapter(HienThiUserChuTro.this,chuTroArrayList);

                recyclerView.setAdapter(userChuTroAdapter);
                userChuTroAdapter.notifyDataSetChanged();
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
