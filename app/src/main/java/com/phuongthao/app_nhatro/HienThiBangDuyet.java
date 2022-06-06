package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.BangDuyetAdapter;
import com.phuongthao.app_nhatro.Adapter.UserChuTroAdapter;
import com.phuongthao.app_nhatro.Models.DanhSachDaDuyet;
import com.phuongthao.app_nhatro.Models.UserChuTro;

import java.util.ArrayList;

public class HienThiBangDuyet extends AppCompatActivity {

//    TextView textView, textView2;
//    DrawerLayout drawerLayout;
    private ArrayList<DanhSachDaDuyet> danhSachDaDuyetArrayList = new ArrayList<>();
    DatabaseReference dataRef;
    private RecyclerView recyclerView;
    private BangDuyetAdapter bangDuyetAdapter ;
    String idChuTro;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_bang_duyet);

        getSupportActionBar().setTitle("DANH SÁCH ĐÃ DUYỆT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA");
        recyclerView = findViewById(R.id.recyclerHienThiBangDUyet);

//        textView = findViewById(R.id.idTenChuTro);
//        textView2 = findViewById(R.id.TenChuTro);
//
//        drawerLayout = findViewById(R.id.drawerLayout);
        firebaseAuth = FirebaseAuth.getInstance();
        idChuTro = firebaseAuth.getCurrentUser().getUid();

        // thiếu 2 dòng dưới thì ăn cám!!!! haha! fix lâu quá cmt cho nhớ. Thiếu nó không thể hiển thị lên.
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //  chuTroArrayList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    DanhSachDaDuyet model = ds.getValue(DanhSachDaDuyet.class);
                    if (model.getIdChuTro().equals(idChuTro)){
                        danhSachDaDuyetArrayList.add(model);
//                        textView2.setText("DANH SÁCH ĐÃ DUYỆT");
                    }


                }
                bangDuyetAdapter = new BangDuyetAdapter(HienThiBangDuyet.this,danhSachDaDuyetArrayList);

                recyclerView.setAdapter(bangDuyetAdapter);
                bangDuyetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    public void ClickMenu(View view) {
//        //Open drawer
//        QuanLy_ChuTro.openDrawer(drawerLayout);
//    }
//    public void ClickLogo(View view){
//        //Close drawer
//        QuanLy_ChuTro.closeDrawer(drawerLayout);
//    }
//
//    public static void closeDrawer(DrawerLayout drawerLayout) {
//        //Close drawer layout
//        //Ckeck condition
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            //When drawer is open
//            //Close
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//    }
//    public void ClickHome(View view) {
//        //Redirect activity to dashboard
//        // redirectActivity(getApplicationContext(), QuanLy_ChuTro.class);
//        Intent intent = new Intent(getApplicationContext(),QuanLy_ChuTro.class);
//        startActivity(intent);
//    }
//    public void ClickQuanLyDatPhong(View view){
//        //Redirect activity to dashboard
//        redirectActivity(this, QLy_DatPhong.class);
//    }
//    public void ClickDanhSachDuyet(View view){
//        //Recreate activity
//        recreate();
//
//    }
//    public void ClickThongKe(View view){
//        //Redirect activity to dashboard
//        redirectActivity(this, QLy_DatPhong.class);
//    }
//    public void ClickDangXuat(View view){
//        xacNhanDangXuat();
//    }
//
//    public static void redirectActivity(Activity activity, Class aClass) {
//        //Initialize intent
//        Intent intent = new Intent(activity, aClass);
//        //Set flag
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //Start activity
//        activity.startActivity(intent);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //Close drawer
//        closeDrawer(drawerLayout);
//    }


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
//                Intent intent = new Intent(HienThiBangDuyet.this, Account.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//
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
        onBackPressed();
        return true;
    }
}
