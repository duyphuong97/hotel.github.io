package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class InsertDta_Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;

    private static final int PERMISSION_TO_SELECT_IMAGE_FROM_GALLERY = 100;
    private static final int PICK_IMAGE_MULTIPLE = 200;

    TextView textViewDangTin;
    EditText EditTextTen, EditTextGia, EditTextviTri, EditTextSDT, EditTextGioiThieu, EditTextDienTich;
    List<Uri> imageList = new ArrayList<Uri>();
    ImageView imageViewAdd;
    TextView textViewProgressbar;
    ProgressBar progressBar;
    Button btnBrowse;
    public double lat, lon;
    String LAT,LON;
    FusedLocationProviderClient fusedLocationProviderClient;

    boolean isImageAdd = false;
    Uri imageUri;

    String auth;
    DatabaseReference databaseReference, nhaTro, thongKeRef;
    StorageReference storageReference;





    String imageEncoded;
    List<String> imagesEncodedList;
//    StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dta_);


        anhXa();
        SharedPreferences sharedPreferences = getSharedPreferences("AuthID", Context.MODE_PRIVATE);
        auth = sharedPreferences.getString("AUTH_ID", "aaaaaa");

        Toast.makeText(this, "" + auth, Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("TH??M PH??NG");

        textViewProgressbar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        FirebaseDatabase firebaseDatabase;
        //Tham chi???u ?????n firebase realtime
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
//        thongKeRef = FirebaseDatabase.getInstance().getReference().child("ThongKe");
        nhaTro = FirebaseDatabase.getInstance().getReference().child("PhongTro");
        //Tham chi???u ?????n firebase stored
        storageReference = FirebaseStorage.getInstance().getReference().child("CarImage");
        listener();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }

    private void getLocation() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location =task.getResult();
                if(location != null){
                    try {
                        Geocoder geocoder = new Geocoder(InsertDta_Activity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        lat = addresses.get(0).getLatitude();
                        lon = addresses.get(0).getLongitude();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void listener(){
        //D???c ???nh khi ???n v??o ImageView
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
        //Nh???n v??o n??t upload s??? post d??? li???u l??n firebase
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String imageName = EditTextTen.getText().toString();
                final String imageName1 = EditTextGia.getText().toString();
                final String imageName2 = EditTextviTri.getText().toString();
                final String imageName3 = EditTextSDT.getText().toString();
                final String imageName4 = EditTextGioiThieu.getText().toString();
                final String imageName5 = EditTextDienTich.getText().toString();
//                if (isImageAdd != false && imageName != null && imageName1 != null && imageName2 != null && imageName3 != null && imageName4 != null && imageName5 != null) {
//                    uploadData(imageName, imageName1, imageName2, imageName3, imageName4, imageName5);
//                }
                if(imageName.isEmpty()){
                    EditTextTen.setError("Vui l??ng nh???p t??n!");
                    EditTextTen.requestFocus();
                }
                if(imageName1.isEmpty()){
                    EditTextGia.setError("Vui l??ng nh???p gi??!");
                    EditTextGia.requestFocus();
                }
                if(imageName2.isEmpty()){
                    EditTextviTri.setError("Vui l??ng nh???p v??? tr??!");
                    EditTextviTri.requestFocus();
                }
                if(imageName3.isEmpty()){
                    EditTextSDT.setError("Vui l??ng nh???p s??? ??i???n tho???i!");
                    EditTextSDT.requestFocus();
                }
                if(imageName4.isEmpty()){
                    EditTextGioiThieu.setError("Vui l??ng nh???p gi???i thi???u!");
                    EditTextGioiThieu.requestFocus();
                }
                if(imageName5.isEmpty()){
                    EditTextDienTich.setError("Vui l??ng nh???p di???n t??ch!");
                    EditTextDienTich.requestFocus();
                }
                if (imageName.isEmpty()||imageName1.isEmpty()||imageName2.isEmpty()||imageName3.isEmpty()||imageName4.isEmpty()||imageName5.isEmpty()){

                }
                else{
                    uploadData(imageName,imageName1, imageName2, imageName3, imageName4, imageName5);
                }
            }
        });
    }

    private void uploadData(final String imageName, final String imageName1, final String imageName2, final String imageName3, final String imageName4, final String imageName5) {
        //Hi???n th??? thanh progress v?? textview progress
        textViewProgressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        //T???o key
        final String key = databaseReference.push().getKey();

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.getAddress(imageName2, getApplicationContext(), new GeoHandler());
        //L??u ???nh v??o storage firebase
        storageReference.child(key+"jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //N???u l??u th??nh c??ng s??? ch???y h??m n??y
                        //D??ng HashMap ????? ?????y d??? li???u l??n firebase realtime
                        HashMap hashMap = new HashMap();
                        hashMap.put("TenPhong", imageName);
                        hashMap.put("GiaPhong", imageName1);
                        hashMap.put("VitriPhong", imageName2);
                        hashMap.put("SDTPhong", imageName3);
                        hashMap.put("GioiThieuPhong", imageName4);
                        hashMap.put("GioiThieuDienTich", imageName5);
                        hashMap.put("ImageUrl", uri.toString());
                        hashMap.put("idChuTro",auth);
                        hashMap.put("TrangThai", "conphong");
                        hashMap.put("id",key);
                        hashMap.put("lat", Double.parseDouble(LAT));
                        hashMap.put("lon", Double.parseDouble(LON));
                        //data reference n???u post d??? li???u th??nh c??ng s??? ch???y d??ng b??n d?????i v?? chuy???n sang HomeActivity
                        databaseReference.child(key).setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
//                                startActivity(new Intent(getApplicationContext(), SoPhongTroActivity.class));
                                        finish();
                                        Toast.makeText(InsertDta_Activity.this, "Th??m ph??ng tr??? th??nh c??ng!", Toast.LENGTH_SHORT).show();


                                        //ThongKe con phong
//                                        thongKeRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                long value = (long) dataSnapshot.child("ConPhong").getValue();
//                                                value +=1;
//                                                dataSnapshot.getRef().setValue(value);
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
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                //T??nh to??n s??? % upload gi???ng nh?? c???p nh???t li??n qu??n v???y
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
            imageViewAdd.setImageURI(imageUri);
        }
    }



    private void anhXa(){
        EditTextTen = (EditText) findViewById(R.id.Edit_TenNhaTro);
        EditTextviTri = (EditText) findViewById(R.id.Edit_ViTri);
        EditTextGia = (EditText) findViewById(R.id.Edit_Gia);
        EditTextSDT = (EditText) findViewById(R.id.Edit_SDT);
        EditTextGioiThieu = (EditText) findViewById(R.id.Edit_GioiThieu);
        EditTextDienTich = (EditText) findViewById(R.id.Edit_DienTich);
        imageViewAdd = (ImageView) findViewById(R.id.imageViewAdd);
        btnBrowse = (Button) findViewById(R.id.button_Brower);
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
