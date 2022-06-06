package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ViewDatPhong extends AppCompatActivity {

    ImageView imageViewKHang;
    CardView cardViewKH;
    String  idKhachHang,idChuTro;
    RelativeLayout relativeLayout_HOMEKH, relativeLayout_CMNN, relativeLayout_NamSinhKH, relativeLayout_Quequan, relativeLayout_TenPhong;
    Button btn_Duyet, btn_NO_Duyet;
    TextView textView_TenKH, textView_SDT, textView_HienSDT_KH, textView_CMNN, textView_HienCMNN, textView_NamSinh,
            textView_HienNamSinh, textView_Quequan, textView_HienQuequan, textView_TenPhong, textView_HienThiTenPhong;
    DatabaseReference Ten_KhachHang_HA, TenPhongTroHA, BangDuyetHA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dat_phong);


        anhXa();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Ten_KhachHang_HA = FirebaseDatabase.getInstance().getReference().child("Ten_KhachHang_HA");
        TenPhongTroHA = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        BangDuyetHA = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA");



        final String hoTen = getIntent().getExtras().get("hoten").toString();
        textView_TenKH.setText(hoTen);
        final String soCMNN = getIntent().getExtras().get("SoCMNN").toString();
        textView_HienCMNN.setText(soCMNN);
        final String sDT = getIntent().getExtras().get("sdt").toString();
        textView_HienSDT_KH.setText(sDT);
        final String namSinh = getIntent().getExtras().get("namsinh").toString();
        textView_HienNamSinh.setText(namSinh);
        final String queQuan = getIntent().getExtras().get("quequan").toString();
        textView_HienQuequan.setText(queQuan);
        final String hinhCMNN = getIntent().getExtras().get("hinhCMNN").toString();
        Picasso.with(ViewDatPhong.this).load(hinhCMNN).into(imageViewKHang);
        getSupportActionBar().setTitle(hoTen);
        idKhachHang = getIntent().getExtras().get("idKhachHang").toString();
        final String idPhong = getIntent().getExtras().get("idPhong").toString();
        final String tenPhong = getIntent().getExtras().get("TenPhong").toString();
        textView_HienThiTenPhong.setText(tenPhong);



        Ten_KhachHang_HA.child(idKhachHang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.hasChild("idChuTro")){
                        idChuTro = dataSnapshot.child("idChuTro").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Log.d("aaa", tenPhong +"jjjjjj");
//        Toast.makeText(this, "ddddddd" + tenPhong, Toast.LENGTH_SHORT).show();
        btn_Duyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDuLieu(hoTen, soCMNN, sDT, namSinh, queQuan, hinhCMNN, idKhachHang, idPhong, tenPhong);
                finish();

            }
        });
        btn_NO_Duyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ten_KhachHang_HA.child(idKhachHang).removeValue();
                HashMap hashMap2 = new HashMap();
                hashMap2.put("TrangThai", "conphong");
                TenPhongTroHA.child(idPhong).updateChildren(hashMap2);
                finish();
            }
        });
    }
    private void xuLyDuLieu(String hoTen, String soCMNN, String sDT, String namSinh, String queQuan, String hinh, String idKh, String idPhong, String tenPhong){
        HashMap hashMap = new HashMap();
        hashMap.put("hoten", hoTen);
        hashMap.put("SoCMNN", soCMNN);
        hashMap.put("sdt", sDT);
        hashMap.put("namsinh", namSinh);
        hashMap.put("quequan", queQuan);
        hashMap.put("hinhCMNN", hinh);
        hashMap.put("idKhachHang", idKh);
        hashMap.put("idPhong", idPhong);
        hashMap.put("idChuTro",idChuTro);
        hashMap.put("TenPhong", tenPhong);

        BangDuyetHA.child(idPhong).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ViewDatPhong.this, "Duyet thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        HashMap hashMap2 = new HashMap();
        hashMap2.put("TrangThai", "");
        TenPhongTroHA.child(idPhong).updateChildren(hashMap2);
        Ten_KhachHang_HA.child(idKh).removeValue();
   //     TenPhongTroHA.child(idPhong).removeValue();



    }
    private void anhXa(){


        cardViewKH = findViewById(R.id.cardView_KH);
        imageViewKHang = findViewById(R.id.ImageCMNN);
        relativeLayout_CMNN = findViewById(R.id.Layout_CMNN);
        relativeLayout_HOMEKH = findViewById(R.id.Home_KH);
        relativeLayout_NamSinhKH = findViewById(R.id.Layout_NamSinh);
        relativeLayout_Quequan = findViewById(R.id.Layout_QueQuan);
        relativeLayout_TenPhong = findViewById(R.id.Layout_TenPhong);
        btn_Duyet = findViewById(R.id.butDuyet);
        btn_NO_Duyet = findViewById(R.id.butKhongDuyet);
        textView_TenKH = findViewById(R.id.tenKHang);
        textView_SDT = findViewById(R.id.SDT_KH);
        textView_HienSDT_KH = findViewById(R.id.Hien_SDT_KH);
        textView_CMNN = findViewById(R.id.SO_CMNN);
        textView_HienCMNN = findViewById(R.id.Text_Hien_CMNN);
        textView_NamSinh = findViewById(R.id.NamSinh_KH);
        textView_HienNamSinh = findViewById(R.id.Text__Hien_NSinh);
        textView_Quequan = findViewById(R.id.Quequan_KH);
        textView_HienQuequan = findViewById(R.id.Text_Hien_Quequan);
        textView_TenPhong = findViewById(R.id.TenPhongTro);
        textView_HienThiTenPhong = findViewById(R.id.Text_Hien_TenPhong);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
