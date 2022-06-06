package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {

    ImageView imageViewDangXuat, imageViewQuanLyDSNT, imageViewEmail, imageViewDangKy;
    TextView textViewEmail, textViewDangKy, textViewDangXuat, textViewQuanLyDSNT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        anhXa();
        getSupportActionBar().setTitle("ADMIN");

    imageViewDangKy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentTK = new Intent(getApplicationContext(), DangKi_ChuTro.class);
            startActivity(intentTK);
        }
    });
    imageViewEmail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentCHUTRO = new Intent(getApplicationContext(), HienThiUserChuTro.class);
            startActivity(intentCHUTRO);
        }
    });
    imageViewQuanLyDSNT.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentNhaTro = new Intent(getApplicationContext(), HienThiDanhSachNhaTro.class);
            startActivity(intentNhaTro);
        }
    });
    imageViewDangXuat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            xacNhanDangXuat();
        }
    });
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
                Intent intent = new Intent(Admin.this, Account.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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


    private void anhXa(){
        imageViewEmail = findViewById(R.id.imageEmail);
        imageViewDangKy = findViewById(R.id.imageDangKy);
        textViewDangKy = findViewById(R.id.textdangky);
        textViewEmail = findViewById(R.id.textEmail);
        imageViewQuanLyDSNT = findViewById(R.id.imageQuanLyDanhSach);
        textViewQuanLyDSNT = findViewById(R.id.textQuanlyDanhSachNT);
        imageViewDangXuat = findViewById(R.id.imageDangXuat);
        textViewDangXuat = findViewById(R.id.textDangXuat);
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_admin, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.Dangxuat:
//                xacNhanDangXuat();
//                Toast.makeText(this, "Bạn đã chọn đăng xuất!", Toast.LENGTH_SHORT).show();
//                break;
//            case  R.id.qlChuTro:
//                Intent intent2 = new Intent(getApplicationContext(), HienThiUserChuTro.class);
//                startActivity(intent2);
//                overridePendingTransition(0, 0);
//                Toast.makeText(this, "Bạn đã chọn xam danh sách Email!", Toast.LENGTH_SHORT).show();
//                break;
//            case  R.id.qlTaoTK:
//                Intent intent3 = new Intent(getApplicationContext(), DangKi_ChuTro.class);
//                startActivity(intent3);
//                overridePendingTransition(0, 0);
//                Toast.makeText(this, "Bạn đã chọn tạo tài khoản chủ trọ!", Toast.LENGTH_SHORT).show();
//                break;
//            case  R.id.qlDanhSachNhaTro:
//                Intent intent5 = new Intent(getApplicationContext(), HienThiDanhSachNhaTro.class);
//                startActivity(intent5);
//                overridePendingTransition(0, 0);
//                Toast.makeText(this, "Bạn đã chọn quản lý danh sách nhà trọ!", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
