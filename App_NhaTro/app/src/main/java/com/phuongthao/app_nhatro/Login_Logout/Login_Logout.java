package com.phuongthao.app_nhatro.Login_Logout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.phuongthao.app_nhatro.R;

public class Login_Logout extends AppCompatActivity {
    Button btnDangNhap, btnDangki;
    EditText edtEmail, edtPass;
    ImageView imgDangNhap;
    TextView txtDangNhap;
    FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_logout);

        mAuth = FirebaseAuth.getInstance();
        anhXa();
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
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
                            Toast.makeText(Login_Logout.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_Logout.this, "Lỗi đăng ký, gmail phải đúng dạng và mật khẩu ít nhất 6 kí tự!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void DangNhap(){
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Logout.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_Logout.this, "Lỗi đăng nhập, gmail không đúng hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private void anhXa(){
        btnDangki = (Button) findViewById(R.id.buttonDangKi);
        btnDangNhap = (Button) findViewById(R.id.buttonDangNhap);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPass = (EditText) findViewById(R.id.editTextMatKhau);
        imgDangNhap = (ImageView) findViewById(R.id.imageViewDangNhap);
        txtDangNhap = (TextView) findViewById(R.id.textViewDangNhap);
    }
}
