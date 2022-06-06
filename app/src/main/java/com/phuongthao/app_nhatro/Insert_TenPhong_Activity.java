package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.List;

public class Insert_TenPhong_Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;

    private static final int PERMISSION_TO_SELECT_IMAGE_FROM_GALLERY = 100;
    private static final int PICK_IMAGE_MULTIPLE = 200;

    boolean isImageAdd = false;
    Uri imageUri;

    EditText EditTextTen;
    TextView textViewTenTro;
    Button btnBrowse;

    ImageView imageViewTenTro;
    TextView textViewProgressbar_TTro;
    ProgressBar progressBar_TTro;

    DatabaseReference nhaTro;
    StorageReference storageReference;

    String imageEncoded;
    List<String> imagesEncodedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__ten_phong_);

        anhXa();
        getSupportActionBar().setTitle("THÊM NHÀ TRỌ");

        textViewProgressbar_TTro.setVisibility(View.GONE);
        progressBar_TTro.setVisibility(View.GONE);

        FirebaseDatabase firebaseDatabase;
        nhaTro = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        //Tham chiếu đến firebase stored
        storageReference = FirebaseStorage.getInstance().getReference().child("CarImage_TenTro");
        listener();
    }
    private void listener(){
        //Dọc ảnh khi ấn vào ImageView
        imageViewTenTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
        //Nhấn vào nút upload sẽ post dữ liệu lên firebase
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName = EditTextTen.getText().toString();
//                if (isImageAdd != false && imageName != null ) {
//                    uploadData(imageName);
//                }
                if(imageName.isEmpty()){
                    EditTextTen.setError("Vui lòng nhập tên!");
                    EditTextTen.requestFocus();
                }
                else {
                    uploadData(imageName);
                }
            }
        });
    }

//    private void upLoadData(final String imageName){
//        final String phongTroKey = nhaTro.push().getKey();
//        HashMap hashMap = new HashMap();
//        hashMap.put("TenPhong", imageName);
//        nhaTro.child(phongTroKey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(Insert_TenPhong_Activity.this, "Tao Thanh Cong!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
    private void uploadData(final String imageName) {
        //Hiển thị thanh progress và textview progress
        textViewProgressbar_TTro.setVisibility(View.VISIBLE);
        progressBar_TTro.setVisibility(View.VISIBLE);
        //Tạo key
        final String NhaTrokey = nhaTro.push().getKey();
        //Lưu ảnh vào storage firebase
        storageReference.child(NhaTrokey+"jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(NhaTrokey + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Nếu lưu thành công sẽ chạy hàm này
                        //Dùng HashMap để đẩy dữ liệu lên firebase realtime
                        final String uID = getIntent().getStringExtra("uID");
                        HashMap hashMap = new HashMap();
                        hashMap.put("TenPhong", imageName);
                        hashMap.put("Idchutro", uID);
                        hashMap.put("idNhatro", NhaTrokey);
                        nhaTro.child(NhaTrokey).setValue(hashMap);
                        hashMap.put("ImageUrl", uri.toString());
                        //data reference nếu post dữ liệu thành công sẽ chạy dòng bên dưới và chuyển sang HomeActivity
                        nhaTro.child(NhaTrokey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UsersChuTro");
                                HashMap hashMap1 = new HashMap();
                                hashMap1.put("TenChuTro",imageName);
                                reference.child(uID).updateChildren(hashMap1).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
//                                        startActivity(new Intent(getApplicationContext(), Admin.class));
                                        finish();
                                        Toast.makeText(Insert_TenPhong_Activity.this, "Tạo nhà trọ thành công!", Toast.LENGTH_SHORT).show();
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
                progressBar_TTro.setProgress((int)progress);
                textViewProgressbar_TTro.setText(progress + "");
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && data != null) {
            imageUri = data.getData();
            isImageAdd = true;
            imageViewTenTro.setImageURI(imageUri);
        }
    }
    private void anhXa(){
        EditTextTen = (EditText) findViewById(R.id.Edit_TenNhaTro);
        btnBrowse = (Button) findViewById(R.id.button_Brower);
        textViewTenTro = (TextView) findViewById(R.id.TextView_ThemNTro);
        imageViewTenTro = (ImageView) findViewById(R.id.imageViewAdd_Ten);
        textViewProgressbar_TTro = (TextView) findViewById(R.id.textViewProgress_Ten);
        progressBar_TTro = (ProgressBar) findViewById(R.id.progressBar_Ten);
    }
    public void onBackPressed() {

    }


}
