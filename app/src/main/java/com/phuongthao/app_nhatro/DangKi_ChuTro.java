package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DangKi_ChuTro extends AppCompatActivity {

    Button btnDangki;
    EditText edtEmail, edtPass;
    ImageView imgDangNhap;
    TextView txtDangNhap;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki__chu_tro);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("QUẢN LÝ TẠO TÀI KHOẢN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        anhXa();
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }
    private void DangKy(){
        final String email = edtEmail.getText().toString();
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
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Insert_TenPhong_Activity.class);
                                intent.putExtra("uID", mAuth.getUid());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Lỗi đăng ký, gmail phải đúng dạng và mật khẩu ít nhất 6 kí tự hoặc đã được tạo!", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }

    }
    private void anhXa(){
        btnDangki = (Button) findViewById(R.id.buttonDangKi);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPass = (EditText) findViewById(R.id.editTextMatKhau);
        imgDangNhap = (ImageView) findViewById(R.id.imageViewDangNhap);
        txtDangNhap = (TextView) findViewById(R.id.textViewDangNhap);
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
