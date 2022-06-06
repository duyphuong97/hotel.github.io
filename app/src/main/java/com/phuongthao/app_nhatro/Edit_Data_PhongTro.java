package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Edit_Data_PhongTro extends AppCompatActivity {


    private static final int REQUEST_CODE_IMAGE = 101;

    private static final int PERMISSION_TO_SELECT_IMAGE_FROM_GALLERY = 100;
    private static final int PICK_IMAGE_MULTIPLE = 200;
    TextView textViewDangTin;
    EditText EditTen, EditGia, EditviTri, EditSDT, EditGioiThieu, EditDienTich;
    ImageView imageViewEdit;
    TextView textViewProgressbar;
    ProgressBar progressBar;
    Button btnUpload;

    boolean isImageAdd = false;
    Uri imageUri;
    String PhongTrokey;

    DatabaseReference databaseReference, nhaTro;
    StorageReference storageReference;


    public double lat, lon;
    String LAT,LON;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__data__phong_tro);
        anhXa();

        getSupportActionBar().setTitle("SỬA THÔNG TIN PHÒNG");

        textViewProgressbar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        //Tham chiếu đến firebase realtime
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        nhaTro = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        //Tham chiếu đến firebase stored
        storageReference = FirebaseStorage.getInstance().getReference().child("CarImage");
        banDle_Edit();
        listener();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


    }
    private void listener(){
        //Dọc ảnh khi ấn vào ImageView
        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
        //Nhấn vào nút upload sẽ post dữ liệu lên firebase
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName = EditTen.getText().toString();
                final String imageName1 = EditGia.getText().toString();
                final String imageName2 = EditviTri.getText().toString();
                final String imageName3 = EditSDT.getText().toString();
                final String imageName4 = EditGioiThieu.getText().toString();
                final String imageName5 = EditDienTich.getText().toString();
                uploadData(imageName, imageName1, imageName2, imageName3, imageName4, imageName5);
            }
        });
    }
    private void uploadData(final String imageName, final String imageName1, final String imageName2, final String imageName3, final String imageName4, final String imageName5) {
        //Hiển thị thanh progress và textview progress
        textViewProgressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        //Tạo key
        final String key = PhongTrokey;

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.getAddress(imageName2, getApplicationContext(), new GeoHandler());

        //Lưu ảnh vào storage firebase
        storageReference.child(key+"jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Nếu lưu thành công sẽ chạy hàm này
                        //Dùng HashMap để đẩy dữ liệu lên firebase realtime
                        HashMap hashMap = new HashMap();
                        hashMap.put("TenPhong", imageName);
                        hashMap.put("GiaPhong", imageName1);
                        hashMap.put("VitriPhong", imageName2);
                        hashMap.put("SDTPhong", imageName3);
                        hashMap.put("GioiThieuPhong", imageName4);
                        hashMap.put("GioiThieuDienTich", imageName5);
                        hashMap.put("ImageUrl", uri.toString());
                        hashMap.put("lat", Double.parseDouble(LAT));
                        hashMap.put("lon", Double.parseDouble(LON));

                        //data reference nếu post dữ liệu thành công sẽ chạy dòng bên dưới và chuyển sang HomeActivity
                        databaseReference.child(key).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                finish();
                                Toast.makeText(Edit_Data_PhongTro.this, "Update successlly", Toast.LENGTH_SHORT).show();

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
            imageViewEdit.setImageURI(imageUri);
        }
    }
    private void banDle_Edit(){
        Bundle bd = getIntent().getExtras();
        PhongTrokey = bd.getString("id");
        String TenNT = bd.getString("TenNhaTro");
        String GiaNT = bd.getString("GiaNhaTro");
        String ViTriNT = bd.getString("ViTriNhaTro");
        String PhoneNT = bd.getString("PhoneNhaTro");
        String GioiThieuNT = bd.getString("GioiThieuNhaTro");
        String DienTichNT = bd.getString("DienTichNhaTro");
        String HinhNT = bd.getString("HinhNhaTro");
//        Toast.makeText(this, HinhNT, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, tennt, Toast.LENGTH_SHORT).show();
        EditTen.setText(TenNT);
        EditGia.setText(GiaNT);
        EditviTri.setText(ViTriNT);
        EditSDT.setText(PhoneNT);
        EditGioiThieu.setText(GioiThieuNT);
        EditDienTich.setText(DienTichNT);
        Picasso.with(getApplicationContext()).load(HinhNT).into(imageViewEdit);
    }
    private void anhXa(){
        EditTen = (EditText) findViewById(R.id.EditTenNhaTro);
        EditviTri = (EditText) findViewById(R.id.EditViTri);
        EditGia = (EditText) findViewById(R.id.EditGia);
        EditSDT = (EditText) findViewById(R.id.EditSDT);
        EditGioiThieu = (EditText) findViewById(R.id.EditGioiThieu);
        EditDienTich = (EditText) findViewById(R.id.EditDienTich);
        imageViewEdit = (ImageView) findViewById(R.id.imageViewEdit);
        btnUpload = (Button) findViewById(R.id.button_Upload);
        textViewProgressbar = (TextView) findViewById(R.id.textViewProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textViewDangTin = (TextView) findViewById(R.id.TextView_DangTin);
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {

            switch (msg.what){
                case 1:
                    Bundle bundle =msg.getData();
                    LAT = bundle.getString("LAT");
                    LON = bundle.getString("LON");

                    break;
                default:
                    LAT = null;
                    LON = null;
            }
            Log.d("LAT", LAT);
            Log.d("LON", LON);

        }
    }
}
