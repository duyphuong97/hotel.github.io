package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.phuongthao.app_nhatro.Models.PhongTro;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SoPhongTroActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    FirebaseRecyclerOptions<PhongTro> options;
    DatabaseReference dataRef;
    FirebaseAuth firebaseAuth;
    FirebaseRecyclerAdapter<PhongTro, MyViewHolder> adapter;
//    FloatingActionButton floatingActionButton_Add_NhaTro;
    String idChuTro, auth;
    int countConPhong=0;
    int countHetPhong=0;
    int countDaDatPhong=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_phong_tro);

        anhXa();
        getSupportActionBar().setTitle("DANH SÁCH PHÒNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();


//        Bundle bd = getIntent().getExtras();
//        idChuTro = bd.getString("idChuTro");
//
//        SharedPreferences sharedPreferences = getSharedPreferences("AuthID", Context.MODE_PRIVATE);
//        auth = sharedPreferences.getString("AUTH_ID", "aaaaaa");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertDta_Activity.class);
                startActivity(intent);
            }
        });


        String tenNT = getIntent().getStringExtra("TenPhongTroHA");
        dataRef = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        recyclerView = findViewById(R.id.recyclerSoPhong);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData(tenNT);
//        xuLyNutAn();


    }
    private void LoadData(String data) {
//        fetch dữ liệu tù firebase realtime và firebase storage về
        //Tạo biến truy vần dữ liệu query dùng để search live data
        Query query = dataRef.orderByChild("TenPhong").startAt(data).endAt(data + "\uf8ff");
//        Firebase option như tut hướng dẫn thay Car.class thành new SnapshotParser<Car>
        options = new FirebaseRecyclerOptions.Builder<PhongTro>().setQuery(query, new SnapshotParser<PhongTro>() {
            @NonNull
            @Override

            public PhongTro parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new PhongTro(snapshot.child("TenPhong").getValue().toString(), snapshot.child("ImageUrl").getValue().toString(),
                        snapshot.child("GiaPhong").getValue().toString(),
                        snapshot.getKey(),snapshot.child("VitriPhong").getValue().toString(), snapshot.child("idChuTro").getValue().toString(), snapshot.child("TrangThai").getValue().toString());
            }
        }).build();
        adapter = new FirebaseRecyclerAdapter<PhongTro, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final PhongTro model) {
                    idChuTro = model.getIdChuTro();
//                Toast.makeText(SoPhongTroActivity.this, "" + idChuTro, Toast.LENGTH_SHORT).show();
                //Hàm đổ dữ liệu về recyclerview
                holder.textView.setText(model.getGia_Phong());
                holder.textView1.setText(model.getTenPhong());
                holder.textView2.setText(model.getVitri_Phong());
                String tragthai = model.getTrangThai();



                holder.textView4.setText(tragthai);
                if (tragthai.equals("conphong")){

                    holder.textView4.setText("CÒN PHÒNG");
                    countConPhong++;

                }
                if (tragthai.equals("daduocdat")){
                    holder.textView4.setText("ĐÃ ĐƯỢC ĐẶT");
                    countDaDatPhong++;

                }
                if(tragthai.equals("")){
                    holder.textView4.setText("HẾT PHÒNG");
                    countHetPhong++;

                }

                //dùng thư viện picasso load ảnh ra
                Picasso.with(getApplicationContext()).load(model.getImage_Phong()).into(holder.imageViewNen);
                //Click vào mỗi phần tử sẽ chuyển sang ViewActivity
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SoPhongTroActivity.this, ViewActivity.class);
                        Bundle bd = new Bundle();
                        bd.putString("idChuTro", model.getIdChuTro());
                        bd.putString("PhongTroKey", getRef(position).getKey());
                        intent.putExtras(bd);
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_view_holder, parent, false);
                return new MyViewHolder(v);
            }

        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
//    private void xuLyNutAn(){
//        if (auth.equals(idChuTro)){
//            floatingActionButton.setVisibility(View.VISIBLE);
//        }else {
//            floatingActionButton.setVisibility(View.GONE);
//        }
//    }
    private void anhXa(){
        floatingActionButton = findViewById(R.id.floating_add_phongtro);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!= null){
            getMenuInflater().inflate(R.menu.menuthongke, menu);
        }


        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
                case R.id.thongKe:
                    Intent intent = new Intent(getApplicationContext(), ThongKe_NhaTro.class);
                    intent.putExtra("ConPhong",countConPhong);
                    intent.putExtra("HetPhong",countHetPhong);
                    intent.putExtra("DaDuocDat",countDaDatPhong);

                    startActivity(intent);
                    overridePendingTransition(0, 0);
//                Log.d("ConPhong", String.valueOf(countConPhong));
//                Log.d("HetPhong", String.valueOf(countHetPhong));
//                Log.d("DaDuocDat", String.valueOf(countDaDatPhong));

                    break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!= null){
            floatingActionButton.setVisibility(View.VISIBLE);


        }else {

            floatingActionButton.setVisibility(View.GONE);
        }
    }
}
