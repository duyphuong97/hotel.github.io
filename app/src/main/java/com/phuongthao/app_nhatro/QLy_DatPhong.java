package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.QuanLyDatPhongAdapter;
import com.phuongthao.app_nhatro.Models.DatPhongTro;
import com.phuongthao.app_nhatro.Models.DatPhongTro1;
import com.phuongthao.app_nhatro.Models.PhongTro;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.phuongthao.app_nhatro.QuanLy_ChuTro.redirectActivity;

public class QLy_DatPhong extends AppCompatActivity {

//    TextView textView, textView2;
//    DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    FirebaseRecyclerOptions<DatPhongTro> options;
    DatabaseReference dataRef;
    FirebaseRecyclerAdapter<DatPhongTro, MyViewDatPhong> adapter;
    private QuanLyDatPhongAdapter quanLyDatPhongAdapter;
    private ArrayList<DatPhongTro1> datPhongTro = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    String idChuTro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_ly__dat_phong);
        getSupportActionBar().setTitle("QUẢN LÝ ĐẶT PHÒNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        drawerLayout = findViewById(R.id.drawerLayout);
//        textView = findViewById(R.id.idTenChuTro);
//        textView2 = findViewById(R.id.TenChuTro);
        firebaseAuth = FirebaseAuth.getInstance();
        idChuTro = firebaseAuth.getCurrentUser().getUid();

        dataRef = FirebaseDatabase.getInstance().getReference().child("Ten_KhachHang_HA");
        recyclerView = findViewById(R.id.recyclerDatPhong);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        //  LoadData("");
        LoadKH();
    }

//    public void ClickMenu(View view) {
//        //Open drawer
//        QuanLy_ChuTro.openDrawer(drawerLayout);
//    }
//
//    public void ClickLogo(View view) {
//        //Close drawer
//        QuanLy_ChuTro.closeDrawer(drawerLayout);
//    }
//    public void ClickHome(View view) {
//        //Redirect activity to Home
//       // redirectActivity(getApplicationContext(), QuanLy_ChuTro.class);
//        Intent intent = new Intent(getApplicationContext(),QuanLy_ChuTro.class);
//        startActivity(intent);
//    }
//
//    public void ClickQuanLyDatPhong(View view) {
//        //Recreate activity
//        recreate();
//    }
//    public void ClickDanhSachDuyet(View view) {
//        //Redirect activity to DanhSachDuyet
//        redirectActivity(this, HienThiBangDuyet.class);
//    }
//    public void ClickThongKe(View view) {
//        //Redirect activity to ThongKe
//        redirectActivity(this, QuanLy_ChuTro.class);
//    }
//    public void ClickDangXuat(View view){
//        xacNhanDangXuat();
//    }


    private void LoadKH() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ten_KhachHang_HA");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datPhongTro.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DatPhongTro1 mode = ds.getValue(DatPhongTro1.class);

//                    textView.setText(mode.getTenPhong());
//                    textView2.setText(mode.getTenPhong());
                    if (mode.getIdChuTro().equals(idChuTro)){
//                        textView2.setText("DANH SÁCH ĐẶT PHÒNG");
                        datPhongTro.add(mode);
                    }


                }


                quanLyDatPhongAdapter = new QuanLyDatPhongAdapter(QLy_DatPhong.this, datPhongTro);
                recyclerView.setAdapter(quanLyDatPhongAdapter);
                quanLyDatPhongAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
//    private void xacNhanDangXuat(){
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle("Yêu cầu đăng xuất");
//        alertDialog.setIcon(R.mipmap.ic_launcher);
//        alertDialog.setMessage("Bạn có muốn đăng xuất hay không!!!");
//
//
//        alertDialog.setPositiveButton("ĐĂNG XUẤT", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(QLy_DatPhong.this, Account.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//            }
//        });
//        alertDialog.setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        alertDialog.show();
//    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
//        Intent intent = new Intent(getApplicationContext(), Admin.class);
//        startActivity(intent);
        onBackPressed();
        return true;
    }

}
