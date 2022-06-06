package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class InsertData_DatPhong extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;

    private static final int PERMISSION_TO_SELECT_IMAGE_FROM_GALLERY = 100;
    private static final int PICK_IMAGE_MULTIPLE = 200;

    TextView TextView_DatPhong;
    EditText Edit_Ten, Edit_SDT, Edit_NamSinh, Edit_QueQuan, Edit_CMNN;
    Button btnDatPhong;
    TextView textViewProgressbar;
    ProgressBar progressBar;
    ImageView imageView_KH;


    DatabaseReference databaseReference;
    DatabaseReference dataThongKe;
    StorageReference storageReference;

    boolean isImageAdd = false;
    Uri imageUri;
    String auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data__dat_phong);

        SharedPreferences sharedPreferences = getSharedPreferences("AuthID", Context.MODE_PRIVATE);
        auth = sharedPreferences.getString("AUTH_ID", "aaaaaa");

        anhXa();

        getSupportActionBar().setTitle("ĐẶT PHÒNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewProgressbar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        FirebaseDatabase firebaseDatabase;
        //Tham chiếu đến firebase realtime
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Ten_KhachHang_HA");
//        dataThongKe = FirebaseDatabase.getInstance().getReference().child("ThongKe");
        storageReference = FirebaseStorage.getInstance().getReference().child("CarImage_KhachHang");
        lstenner();

    }
    private void lstenner(){
        //Dọc ảnh khi ấn vào ImageView
        imageView_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName1 = Edit_Ten.getText().toString();
                final String imageName2 = Edit_SDT.getText().toString();
                final String imageName3 = Edit_NamSinh.getText().toString();
                final String imageName4 = Edit_QueQuan.getText().toString();
                final String imageName5 = Edit_CMNN.getText().toString();
//                if(isImageAdd != false && imageName1 != null && imageName2 != null && imageName3 != null && imageName4 != null && imageName5 != null) {
//                    uploadData(imageName1, imageName2, imageName3, imageName4, imageName5);
//                }
                if(imageName1.isEmpty()){
                    Edit_Ten.setError("Vui lòng nhập tên!");
                    Edit_Ten.requestFocus();
                }
                if(imageName2.isEmpty()){
                    Edit_SDT.setError("Vui lòng nhập số điện thoại!");
                    Edit_SDT.requestFocus();
                }

                if(imageName3.isEmpty()){
                    Edit_NamSinh.setError("Vui lòng nhập năm sinh!");
                    Edit_NamSinh.requestFocus();
                }

                if(imageName4.isEmpty()){
                    Edit_QueQuan.setError("Vui lòng nhập quê quán!");
                    Edit_QueQuan.requestFocus();
                }

                if(imageName5.isEmpty()){
                    Edit_CMNN.setError("Vui lòng nhập số CMNN!");
                    Edit_CMNN.requestFocus();
                }
                if (imageName1.isEmpty()||imageName2.isEmpty()||imageName3.isEmpty()||imageName4.isEmpty()||imageName5.isEmpty()){

                }
                else{
                    uploadData(imageName1, imageName2, imageName3, imageName4, imageName5);
                }
            }
        });
    }
    private void uploadData(final String imageName1, final String imageName2, final String imageName3, final String imageName4, final String imageName5) {
        //Hiển thị thanh progress và textview progress
        textViewProgressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        //Tạo key
        final String key = databaseReference.push().getKey();
        storageReference.child(key+"jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Intent intent = getIntent();
                        final String idPhong = intent.getStringExtra("idPhong");
                        String tenPhong = intent.getStringExtra("TenPhong");
                        String idChuTro = intent.getStringExtra("idChuTro");
                        Log.d("aaa", "sss" + tenPhong);
                        //Nếu lưu thành công sẽ chạy hàm này
                        //Dùng HashMap để đẩy dữ liệu lên firebase realtime
                        final HashMap hashMap = new HashMap();
                        hashMap.put("Ten_KH", imageName1);
                        hashMap.put("SDT_KH", imageName2);
                        hashMap.put("NamSinh_KH", imageName3);
                        hashMap.put("Quequan_KH", imageName4);
                        hashMap.put("CMNN_KH", imageName5);
                        hashMap.put("idPhong",idPhong );
                        hashMap.put("idChuTro",idChuTro);
                        hashMap.put("idKhachHang", key);
                        hashMap.put("TrangThai", "daduocdat");
                        hashMap.put("TenPhong", tenPhong);
                        hashMap.put("ImageUrl", uri.toString());


                        //data reference nếu post dữ liệu thành công sẽ chạy dòng bên dưới và chuyển sang HomeActivity
                        databaseReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                DatabaseReference drf = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA").child(idPhong);
                                HashMap hashMap1 = new HashMap();
                                hashMap1.put("TrangThai","daduocdat");
                                drf.updateChildren(hashMap1).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        Toast.makeText(InsertData_DatPhong.this, "Đặt phòng thành công!", Toast.LENGTH_SHORT).show();
                                        finish();

                                        //ThongKe con phong
//                                        dataThongKe.addListenerForSingleValueEvent(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                long value = (long) dataSnapshot.child("ConPhong").getValue();
//                                                long value1 = (long) dataSnapshot.child("DaDuocDat").getValue();
//                                                if (value > 0){
//                                                    value -=1;
//                                                }
//                                                value1 +=1;
//                                                dataSnapshot.getRef().child("ConPhong").setValue(value);
//                                                dataSnapshot.getRef().child("DaDuocDat").setValue(value1);
//                                            }
//
//                                            @Override
//                                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                            }
//                                        });

                                    }
                                });




                            }
                        });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                //Tính toán số % upload giống như cập nhật liên quân vậy
                double progress = taskSnapshot.getBytesTransferred()*100/taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int)progress);
                textViewProgressbar.setText(progress + "");
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && data != null) {
            imageUri = data.getData();
            isImageAdd = true;
            imageView_KH.setImageURI(imageUri);
        }
    }
    private void anhXa(){
        TextView_DatPhong = (TextView) findViewById(R.id.TextView_DatPhong);
        Edit_Ten = (EditText) findViewById(R.id.Edit_TenNguoiO);
        Edit_SDT = (EditText) findViewById(R.id.Edit_SDT);
        Edit_NamSinh = (EditText) findViewById(R.id.Edit_NamSinh);
        Edit_QueQuan = (EditText) findViewById(R.id.Edit_QueQuan);
        Edit_CMNN = (EditText) findViewById(R.id.Edit_CMNN);
        btnDatPhong = (Button) findViewById(R.id.Button_DatPhong);
        textViewProgressbar = (TextView) findViewById(R.id.textViewProgressDatPhong);
        progressBar = (ProgressBar) findViewById(R.id.progressBarDatPhong);
        imageView_KH = (ImageView) findViewById(R.id.imageViewAdd_KH);
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
