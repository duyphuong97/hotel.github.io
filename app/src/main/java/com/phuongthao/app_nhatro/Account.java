package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phuongthao.app_nhatro.Login_Logout.Login_Logout;

import java.util.HashMap;

public class Account extends AppCompatActivity {

    Button btnDangNhap, btnDangki, btnClickHere;
    EditText edtEmail, edtPass;
    ImageView imgDangNhap;
    TextView txtDangNhap;
    FirebaseAuth mAuth;
    CheckBox checkBox;
    TextView textViewForgetPassword, textViewDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("ĐĂNG NHẬP");
        anhXa();
        bottomNaviation();
//        xacNhanDangNhap_DangKy();

//        btnDangki.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DangKy();
//            }
//        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();

            }
        });

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
    private void DangKy(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Lỗi đăng ký, gmail phải đúng dạng và mật khẩu ít nhất 6 kí tự hoặc đã được tạo!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    private void DangNhap(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        if(email.isEmpty()){
            edtEmail.setError("Vui lòng nhập Email!");
            edtEmail.requestFocus();
        }
        if(password.isEmpty()){
            edtPass.setError("Vui lòng nhập Password!");
            edtPass.requestFocus();
        }
        if (email.isEmpty()||password.isEmpty()){

        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final String userr = task.getResult().getUser().getUid();
                                SharedPreferences sharedPreferences = getSharedPreferences("AuthID", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("AUTH_ID", userr);
                                editor.commit();
                                final Intent intent = new Intent(getApplicationContext(), QuanLy_ChuTro.class);

                                intent.putExtra("KeyChuTro", userr);
                                if (mAuth.getCurrentUser().getUid().equals("xnu9hBSWfKWEokTpQoURCym1iIk1")){

                                    startActivity(new Intent(Account.this,Admin.class));
                                    finish();

                                }else {

                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UsersChuTro");
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("idChuTro",mAuth.getUid());
                                    hashMap.put("email",""+mAuth.getCurrentUser().getEmail().replace(",", "."));
                                    reference.child(mAuth.getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                        @Override
                                        public void onSuccess(Object o) {
                                            startActivity(intent);

                                            Toast.makeText(getApplicationContext(),  "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });

                                }
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            } else {
                                Toast.makeText(getApplicationContext(), "Lỗi đăng nhập, gmail không đúng hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    }


    private void anhXa(){
        btnDangki = (Button) findViewById(R.id.buttonDangKi);
        btnDangNhap = (Button) findViewById(R.id.buttonDangNhap);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPass = (EditText) findViewById(R.id.editTextMatKhau);
        imgDangNhap = (ImageView) findViewById(R.id.imageViewDangNhap);
        txtDangNhap = (TextView) findViewById(R.id.textViewDangNhap);
        textViewDangKy = (TextView) findViewById(R.id.textViewDangKy);
        textViewForgetPassword = (TextView) findViewById(R.id.texviewFogetPassword);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btnClickHere = (Button) findViewById(R.id.btnClickHere);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser !=null){
            startActivity(new Intent(Account.this, Admin.class));
            finish();
        }
//        if (mAuth.getCurrentUser().getUid() == "ovBZF80o1HhTK0bxhHhHa9Ko1Mu2"){
//            startActivity(new Intent(Account.this,Admin.class));
//
//        }else {
//
//            startActivity(new Intent(Account.this,QuanLy_ChuTro.class));
//
//        }
    }
}
