package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.phuongthao.app_nhatro.Login_Logout.Login_Logout;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        bottomNaviation();
        xacNhanDangNhap_DangKy();

    }
    private void bottomNaviation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_account);
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
    private void xacNhanDangNhap_DangKy(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Yêu cầu đăng nhập");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn cần phải đăng nhập mới có thể sử dụng tính năng đăng tin này!!!");


        alertDialog.setPositiveButton("ĐĂNG NHẬP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Login_Logout.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }
}
