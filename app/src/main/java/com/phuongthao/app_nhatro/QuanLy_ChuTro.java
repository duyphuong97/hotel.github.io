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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.phuongthao.app_nhatro.Adapter.ListChuTroAdapter;
import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuanLy_ChuTro extends AppCompatActivity {

    TextView textView, textView2;
    DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    DatabaseReference dataRef;
    FirebaseAuth firebaseAuth;
    ArrayList<ListPhongTro> listPhongTroArrayList = new ArrayList<>();
    ListChuTroAdapter listChuTroAdapter;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly__chu_tro);

        //Assign variable
        drawerLayout = findViewById(R.id.drawerLayout);
        textView = findViewById(R.id.idTenChuTro);
        textView2 = findViewById(R.id.TenChuTro);

        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();

        getSupportActionBar().setTitle("CHỦ NHÀ TRỌ");
        dataRef = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        recyclerView = findViewById(R.id.recyclerQuanLy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        // lay id chu tro
        String UId = getIntent().getStringExtra("KeyChuTro");


        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPhongTroArrayList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ListPhongTro model = ds.getValue(ListPhongTro.class);
                    if (model.getIdchutro().equals(uid)){

                        listPhongTroArrayList.add(model);
                        textView.setText(model.getTenPhong());
                        textView2.setText(model.getTenPhong());

                    }
                    listChuTroAdapter = new ListChuTroAdapter(QuanLy_ChuTro.this,listPhongTroArrayList);
                    recyclerView.setAdapter(listChuTroAdapter);
                    listChuTroAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Close drawer layout
        //Ckeck condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        //Recreate activity
        recreate();
    }
    public void ClickQuanLyDatPhong(View view){
        //Redirect activity to QuanLyDatPhong
        redirectActivity(this, QLy_DatPhong.class);
    }
    public void ClickDanhSachDuyet(View view) {
        //Redirect activity to DanhSachDuyet
        redirectActivity(this, HienThiBangDuyet.class);
    }
    public void ClickThongKe(View view){
        //Redirect activity to ThongKe
        redirectActivity(this, ThongKe_NhaTro.class);
    }
    public void ClickDangXuat(View view){
        xacNhanDangXuat();

    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity, aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }


    private void xacNhanDangXuat(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Yêu cầu đăng xuất");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn đăng xuất hay không!!!");


        alertDialog.setPositiveButton("ĐĂNG XUẤT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(QuanLy_ChuTro.this, Account.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
        alertDialog.setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {

    }
}
