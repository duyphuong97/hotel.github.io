package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ViewActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private ImageView imageViewNen, imageViewViTri, imageViewPhone, imageViewGioiThieu, imageViewDienTich;
    TextView textViewTenTro, textViewGiaPhong, textViewVitri, textViewPhone, textViewGioithieu, textViewTenGia, textViewDienTich, textViewTrangThaiPhong, textViewThongKe_TenKh1, textViewThongKe_TenKh2, textViewThongKe_TenKh3, textViewThongKe_TenKh4,
            textViewThongKe_SDTKh1, textViewThongKe_SDTKh2, textViewThongKe_SDTKh3, textViewThongKe_SDTKh4, textViewThongKe_MSSV_CMNN_Kh1, textViewThongKe_MSSV_CMNN_Kh2, textViewThongKe_MSSV_CMNN_Kh3, textViewThongKe_MSSV_CMNN_Kh4,
            textViewThongKe_Quequan_Kh1, textViewThongKe_Quequan_Kh2, textViewThongKe_Quequan_Kh3, textViewThongKe_Quequan_Kh4;
    CardView carView_ThongKe1, carView_ThongKe2, carView_ThongKe3, carView_ThongKe4;
    Button btnDelete, btnDatphong, btnLienHe, btnEdit, btnThem, btnXacNhan;
    EditText editText_HoTenKH, editText_SDTKH, editText_MSSV_CMNNKH, editText_QuequanKH;

    DatabaseReference ref, dataRef;
    StorageReference storageReference;
    Toolbar toolbar;
    String ID_Phong, PhongTroName;
    String imageUrl;
    String PhongTrokey, auth;
    String idChuTro,ChuTroID;
    FirebaseAuth firebaseAuth;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        anhXa();
        Bundle bd = getIntent().getExtras();

        idChuTro = bd.getString("idChuTro");
        PhongTrokey = bd.getString("PhongTroKey");

        SharedPreferences sharedPreferences = getSharedPreferences("AuthID", Context.MODE_PRIVATE);
        auth = sharedPreferences.getString("AUTH_ID", "aaaaaa");
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        getSupportActionBar().setTitle("TenPhong");
//        imageView = findViewById(R.id.image_single_view_activity);
//        textView = findViewById(R.id.textView_single_view_activity);
//        btnDelete = findViewById(R.id.btnDelete);

//        xuLyAnNut();

        ref = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA");
        Log.d("aaa", ref.getKey());

//        PhongTrokey = getIntent().getStringExtra("PhongTroKey");
        dataRef = FirebaseDatabase.getInstance().getReference().child("TenPhongTroHA").child(PhongTrokey);
        storageReference = FirebaseStorage.getInstance().getReference().child("CarImage").child(PhongTrokey + ".jpg");
        ref.child(PhongTrokey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    PhongTroName = dataSnapshot.child("TenPhong").getValue().toString();
                    String PhongTroViTri = dataSnapshot.child("VitriPhong").getValue().toString();
                    String PhongTroGia = dataSnapshot.child("GiaPhong").getValue().toString();
                    String PhongTroSdt = dataSnapshot.child("SDTPhong").getValue().toString();
                    String PhongTroGioiThieu = dataSnapshot.child("GioiThieuPhong").getValue().toString();
                    String PhongTroDienTich = dataSnapshot.child("GioiThieuDienTich").getValue().toString();
                    String TrangThaiPhong = dataSnapshot.child("TrangThai").getValue().toString();
                    imageUrl = dataSnapshot.child("ImageUrl").getValue().toString();
                    ChuTroID = dataSnapshot.child("idChuTro").getValue().toString();


                    // dia chi ID phong:
                    ID_Phong = dataSnapshot.getKey();
                    Picasso.with(getApplicationContext()).load(imageUrl).into(imageViewNen);
                    textViewTenTro.setText(PhongTroName);
                    textViewVitri.setText(PhongTroViTri);
                    textViewPhone.setText(PhongTroSdt);
                    textViewGioithieu.setText(PhongTroGioiThieu);
                    textViewGiaPhong.setText(PhongTroGia);
                    textViewDienTich.setText(PhongTroDienTich);

                    getSupportActionBar().setTitle(PhongTroName);


                    if (TrangThaiPhong.equals("conphong")) {

                        textViewTrangThaiPhong.setText("CÒN PHÒNG");
                        btnDatphong.setClickable(true);
                        btnThem.setVisibility(View.INVISIBLE);

                    } else if (TrangThaiPhong.equals("daduocdat")) {

                        textViewTrangThaiPhong.setText("ĐÃ ĐƯỢC ĐẶT");
                        btnDatphong.setClickable(false);
                        btnDatphong.setTextColor(Color.parseColor("#FFFFFF"));
                        btnDatphong.setBackgroundColor(Color.RED);
                        btnDatphong.setText("ĐÃ ĐƯỢC ĐẶT");
                        btnThem.setVisibility(View.INVISIBLE);
                        Toast.makeText(ViewActivity.this,"Phòng đã được đặt",Toast.LENGTH_SHORT).show();

                    } else {
                        textViewTrangThaiPhong.setText("HẾT PHÒNG");
//                        btnDatphong.setVisibility(View.INVISIBLE);

                        btnDatphong.setClickable(false);
                        btnDatphong.setTextColor(Color.TRANSPARENT);
                        btnDatphong.setBackgroundColor(Color.TRANSPARENT);
                        Toast.makeText(ViewActivity.this,"Phòng đã hết",Toast.LENGTH_SHORT).show();

                    }

                    textViewVitri.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MyGoogleMap.class);
                            intent.putExtra("key", PhongTrokey);
                            startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanXoa();

            }

        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(getApplicationContext(), Edit_Data_PhongTro.class);
                Bundle bd = new Bundle();
                bd.putString("id", PhongTrokey);
                bd.putString("TenNhaTro", textViewTenTro.getText().toString());
                bd.putString("GiaNhaTro", textViewGiaPhong.getText().toString());
                bd.putString("DienTichNhaTro", textViewDienTich.getText().toString());
                bd.putString("ViTriNhaTro", textViewVitri.getText().toString());
                bd.putString("PhoneNhaTro", textViewPhone.getText().toString());
                bd.putString("GioiThieuNhaTro", textViewGioithieu.getText().toString());
                bd.putString("HinhNhaTro", imageUrl);
                intentEdit.putExtras(bd);
                startActivity(intentEdit);
            }
        });
        btnDatphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertData_DatPhong.class);
                intent.putExtra("idPhong", ID_Phong);
                intent.putExtra("TenPhong", PhongTroName);
                intent.putExtra("idChuTro",ChuTroID);

                startActivity(intent);
                finish();
                Log.d("aaa", ref.getKey());
            }
        });
        btnLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_HoTenKH.setVisibility(View.VISIBLE);
                editText_SDTKH.setVisibility(View.VISIBLE);
                editText_MSSV_CMNNKH.setVisibility(View.VISIBLE);
                editText_QuequanKH.setVisibility(View.VISIBLE);
                btnXacNhan.setVisibility(View.VISIBLE);
                btnThem.setVisibility(View.GONE);
//                carView_ThongKe2.setVisibility(View.GONE);
                editText_HoTenKH.setText("");
                editText_SDTKH.setText("");
                editText_MSSV_CMNNKH.setText("");
                editText_QuequanKH.setText("");
//                carView_ThongKe3.setVisibility(View.GONE);
//                carView_ThongKe4.setVisibility(View.GONE);

            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HotenKH = editText_HoTenKH.getText().toString();
                String SDTKH = editText_SDTKH.getText().toString();
                String MSSV_CMNNKH = editText_MSSV_CMNNKH.getText().toString();
                String QuequanKH = editText_QuequanKH.getText().toString();

                if(HotenKH.isEmpty()){
                    editText_HoTenKH.setError("Vui lòng nhập họ tên!");
                    editText_HoTenKH.requestFocus();
                }
                if(SDTKH.isEmpty()){
                    editText_SDTKH.setError("Vui lòng nhập số điện thoại!");
                    editText_SDTKH.requestFocus();
                }
                if(MSSV_CMNNKH.isEmpty()){
                    editText_MSSV_CMNNKH.setError("Vui lòng nhập MSSV / CMNN!");
                    editText_MSSV_CMNNKH.requestFocus();
                }
                if(QuequanKH.isEmpty()){
                    editText_QuequanKH.setError("Vui lòng nhập quê quán!");
                    editText_QuequanKH.requestFocus();
                }
                if (HotenKH.isEmpty()||SDTKH.isEmpty()||MSSV_CMNNKH.isEmpty()||QuequanKH.isEmpty()){

                }else {
                    switch (i){
                        case 1: khachHang1(HotenKH,SDTKH,MSSV_CMNNKH,QuequanKH); break;
                        case 2: khachHang2(HotenKH,SDTKH,MSSV_CMNNKH,QuequanKH); break;
                        case 3: khachHang3(HotenKH,SDTKH,MSSV_CMNNKH,QuequanKH); break;
                        case 4: khachHang4(HotenKH,SDTKH,MSSV_CMNNKH,QuequanKH); break;
                    }
                }

            }
        });

        carView_ThongKe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewActivity.this);
                alertDialog.setTitle("Yêu cầu xóa KHÁCH HÀNG");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn có muốn xóa không!!!");


                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
                        dataRef.child("hoten").removeValue();
                        dataRef.child("sdt").removeValue();
                        dataRef.child("SoCMNN").removeValue();
                        dataRef.child("quequan").removeValue();
                        Toast.makeText(ViewActivity.this, "Da Xoa Xong", Toast.LENGTH_SHORT).show();
                        carView_ThongKe1.setVisibility(View.GONE);

                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            }
        });

        carView_ThongKe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewActivity.this);
                alertDialog.setTitle("Yêu cầu xóa KHÁCH HÀNG");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn có muốn xóa không!!!");


                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
                        dataRef.child("hoten1").removeValue();
                        dataRef.child("sdt1").removeValue();
                        dataRef.child("SoCMNN1").removeValue();
                        dataRef.child("quequan1").removeValue();
                        Toast.makeText(ViewActivity.this, "Da Xoa Xong", Toast.LENGTH_SHORT).show();
                        carView_ThongKe2.setVisibility(View.GONE);
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            }
        });
        carView_ThongKe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewActivity.this);
                alertDialog.setTitle("Yêu cầu xóa KHÁCH HÀNG");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn có muốn xóa không!!!");


                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
                        dataRef.child("hoten2").removeValue();
                        dataRef.child("sdt2").removeValue();
                        dataRef.child("SoCMNN2").removeValue();
                        dataRef.child("quequan2").removeValue();
                        Toast.makeText(ViewActivity.this, "Da Xoa Xong", Toast.LENGTH_SHORT).show();
                        carView_ThongKe3.setVisibility(View.GONE);
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            }
        });
        carView_ThongKe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewActivity.this);
                alertDialog.setTitle("Yêu cầu xóa KHÁCH HÀNG");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn có muốn xóa không!!!");


                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
                        dataRef.child("hoten3").removeValue();
                        dataRef.child("sdt3").removeValue();
                        dataRef.child("SoCMNN3").removeValue();
                        dataRef.child("quequan3").removeValue();
                        Toast.makeText(ViewActivity.this, "Da Xoa Xong", Toast.LENGTH_SHORT).show();
                        carView_ThongKe4.setVisibility(View.GONE);
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            }
        });
    }


    public void khachHang1(String HotenKH, String SDTKH, String MSSV_CMNNKH, String QuequanKH){
        // khách hàng 1
        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.child("hoten").setValue(HotenKH);
        dataRef.child("sdt").setValue(SDTKH);
        dataRef.child("SoCMNN").setValue(MSSV_CMNNKH);
        dataRef.child("quequan").setValue(QuequanKH);

        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("hoten").exists()) {
                    if (!dataSnapshot.child("hoten").getValue().toString().isEmpty()){
                        String TenKh = dataSnapshot.child("hoten").getValue().toString();
                        String SDTKh = dataSnapshot.child("sdt").getValue().toString();
                        String MSSV_CMNNKh = dataSnapshot.child("SoCMNN").getValue().toString();
                        String QueQuanKh = dataSnapshot.child("quequan").getValue().toString();

                        textViewThongKe_TenKh1.setText("Tên khách hàng: " + TenKh);
                        textViewThongKe_SDTKh1.setText("Số điện thoại: " + SDTKh);
                        textViewThongKe_MSSV_CMNN_Kh1.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                        textViewThongKe_Quequan_Kh1.setText("Quê quán khách hàng: " + QueQuanKh);
                    }else {
                        carView_ThongKe1.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        editText_HoTenKH.setVisibility(View.GONE);
        editText_SDTKH.setVisibility(View.GONE);
        editText_MSSV_CMNNKH.setVisibility(View.GONE);
        editText_QuequanKH.setVisibility(View.GONE);
        btnXacNhan.setVisibility(View.GONE);
        btnThem.setVisibility(View.VISIBLE);
        carView_ThongKe1.setVisibility(View.VISIBLE);
    }

    public void khachHang2(String HotenKH, String SDTKH, String MSSV_CMNNKH, String QuequanKH){
        // khách hàng 2
            dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
            dataRef.child("hoten1").setValue(HotenKH);
            dataRef.child("sdt1").setValue(SDTKH);
            dataRef.child("SoCMNN1").setValue(MSSV_CMNNKH);
            dataRef.child("quequan1").setValue(QuequanKH);

            dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("hoten1").exists()) {
                        if (!dataSnapshot.child("hoten1").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten1").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt1").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN1").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan1").getValue().toString();

                            textViewThongKe_TenKh2.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh2.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh2.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh2.setText("Quê quán khách hàng: " + QueQuanKh);
                        }else {
                            carView_ThongKe2.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            editText_HoTenKH.setVisibility(View.GONE);
            editText_SDTKH.setVisibility(View.GONE);
            editText_MSSV_CMNNKH.setVisibility(View.GONE);
            editText_QuequanKH.setVisibility(View.GONE);
            btnXacNhan.setVisibility(View.GONE);
            btnThem.setVisibility(View.VISIBLE);
            carView_ThongKe2.setVisibility(View.VISIBLE);
    }

    public void khachHang3(String HotenKH, String SDTKH, String MSSV_CMNNKH, String QuequanKH){
        //khách hàng 3
            dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
            dataRef.child("hoten2").setValue(HotenKH);
            dataRef.child("sdt2").setValue(SDTKH);
            dataRef.child("SoCMNN2").setValue(MSSV_CMNNKH);
            dataRef.child("quequan2").setValue(QuequanKH);

            dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("hoten2").exists()) {
                        if (!dataSnapshot.child("hoten2").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten2").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt2").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN2").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan2").getValue().toString();

                            textViewThongKe_TenKh3.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh3.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh3.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh3.setText("Quê quán khách hàng: " + QueQuanKh);
                        }else {
                            carView_ThongKe3.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            editText_HoTenKH.setVisibility(View.GONE);
            editText_SDTKH.setVisibility(View.GONE);
            editText_MSSV_CMNNKH.setVisibility(View.GONE);
            editText_QuequanKH.setVisibility(View.GONE);
            btnXacNhan.setVisibility(View.GONE);
            btnThem.setVisibility(View.VISIBLE);
            carView_ThongKe3.setVisibility(View.VISIBLE);
    }

    public void khachHang4(String HotenKH, String SDTKH, String MSSV_CMNNKH, String QuequanKH){
        // khach hàng 4
            dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
            dataRef.child("hoten3").setValue(HotenKH);
            dataRef.child("sdt3").setValue(SDTKH);
            dataRef.child("SoCMNN3").setValue(MSSV_CMNNKH);
            dataRef.child("quequan3").setValue(QuequanKH);

            dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("hoten3").exists()) {
                        if (!dataSnapshot.child("hoten3").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten3").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt3").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN3").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan3").getValue().toString();

                            textViewThongKe_TenKh4.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh4.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh4.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh4.setText("Quê quán khách hàng: " + QueQuanKh);
                        }else {
                            carView_ThongKe4.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            editText_HoTenKH.setVisibility(View.GONE);
            editText_SDTKH.setVisibility(View.GONE);
            editText_MSSV_CMNNKH.setVisibility(View.GONE);
            editText_QuequanKH.setVisibility(View.GONE);
            btnXacNhan.setVisibility(View.GONE);
            btnThem.setVisibility(View.VISIBLE);
            carView_ThongKe4.setVisibility(View.VISIBLE);
    }


    public void XuLyThongKeKhachHang1(String PhongTrokey){
        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("hoten").exists() && dataSnapshot.child("sdt").exists()
                            && dataSnapshot.child("SoCMNN").exists() && dataSnapshot.child("quequan").exists()){
                        if(!dataSnapshot.child("hoten").getValue().toString().isEmpty() && !dataSnapshot.child("sdt").getValue().toString().isEmpty()
                                && !dataSnapshot.child("SoCMNN").getValue().toString().isEmpty() && !dataSnapshot.child("quequan").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan").getValue().toString();

                            textViewThongKe_TenKh1.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh1.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh1.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh1.setText("Quê quán khách hàng: " + QueQuanKh);
                        }
                    }
                }else {
                    carView_ThongKe1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void XuLyThongKeKhachHang2(String PhongTrokey){
        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("hoten1").exists() && dataSnapshot.child("sdt1").exists()
                            && dataSnapshot.child("SoCMNN1").exists() && dataSnapshot.child("quequan1").exists()){
                        if(!dataSnapshot.child("hoten1").getValue().toString().isEmpty() && !dataSnapshot.child("sdt1").getValue().toString().isEmpty()
                                && !dataSnapshot.child("SoCMNN1").getValue().toString().isEmpty() && !dataSnapshot.child("quequan1").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten1").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt1").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN1").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan1").getValue().toString();

                            textViewThongKe_TenKh2.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh2.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh2.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh2.setText("Quê quán khách hàng: " + QueQuanKh);
                        }
                    }

                }else {
                    carView_ThongKe2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void XuLyThongKeKhachHang3(String PhongTrokey){
        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("hoten2").exists() && dataSnapshot.child("sdt2").exists()
                            && dataSnapshot.child("SoCMNN2").exists() && dataSnapshot.child("quequan2").exists()){
                        if(!dataSnapshot.child("hoten2").getValue().toString().isEmpty() && !dataSnapshot.child("sdt2").getValue().toString().isEmpty()
                                && !dataSnapshot.child("SoCMNN2").getValue().toString().isEmpty() && !dataSnapshot.child("quequan2").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten2").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt2").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN2").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan2").getValue().toString();

                            textViewThongKe_TenKh3.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh3.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh3.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh3.setText("Quê quán khách hàng: " + QueQuanKh);
                        }
                    }
                }else {
                    carView_ThongKe3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void XuLyThongKeKhachHang4(String PhongTrokey){
        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("hoten3").exists() && dataSnapshot.child("sdt3").exists()
                            && dataSnapshot.child("SoCMNN3").exists() && dataSnapshot.child("quequan3").exists()){
                        if(!dataSnapshot.child("hoten3").getValue().toString().isEmpty() && !dataSnapshot.child("sdt3").getValue().toString().isEmpty()
                                && !dataSnapshot.child("SoCMNN3").getValue().toString().isEmpty() && !dataSnapshot.child("quequan3").getValue().toString().isEmpty()){
                            String TenKh = dataSnapshot.child("hoten3").getValue().toString();
                            String SDTKh = dataSnapshot.child("sdt3").getValue().toString();
                            String MSSV_CMNNKh = dataSnapshot.child("SoCMNN3").getValue().toString();
                            String QueQuanKh = dataSnapshot.child("quequan3").getValue().toString();

                            textViewThongKe_TenKh4.setText("Tên khách hàng: " + TenKh);
                            textViewThongKe_SDTKh4.setText("Số điện thoại: " + SDTKh);
                            textViewThongKe_MSSV_CMNN_Kh4.setText("MSSV/số CMNN: " + MSSV_CMNNKh);
                            textViewThongKe_Quequan_Kh4.setText("Quê quán khách hàng: " + QueQuanKh);
                        }
                    }

                }else {
                    carView_ThongKe4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void checkKhachHang(){
        dataRef = FirebaseDatabase.getInstance().getReference().child("BangDuyetHA").child(PhongTrokey);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("hoten").exists()){
                    if(!dataSnapshot.child("hoten").getValue().toString().isEmpty()){
                        carView_ThongKe1.setVisibility(View.VISIBLE);
                        XuLyThongKeKhachHang1(PhongTrokey);
                    }else {
                        carView_ThongKe1.setVisibility(View.INVISIBLE);
                    }

                }
                if(dataSnapshot.child("hoten1").exists()){
                    if(!dataSnapshot.child("hoten1").getValue().toString().isEmpty()){
                        carView_ThongKe2.setVisibility(View.VISIBLE);
                        XuLyThongKeKhachHang2(PhongTrokey);
                    }else {
                        carView_ThongKe2.setVisibility(View.INVISIBLE);
                    }
                }
                if(dataSnapshot.child("hoten2").exists()){
                    if(!dataSnapshot.child("hoten2").getValue().toString().isEmpty()){
                        carView_ThongKe3.setVisibility(View.VISIBLE);
                        XuLyThongKeKhachHang3(PhongTrokey);
                    }else {
                        carView_ThongKe3.setVisibility(View.INVISIBLE);
                    }
                }
                if(dataSnapshot.child("hoten3").exists()){
                    if(!dataSnapshot.child("hoten3").getValue().toString().isEmpty()){
                        carView_ThongKe4.setVisibility(View.VISIBLE);
                        XuLyThongKeKhachHang4(PhongTrokey);

                    }else {
                        carView_ThongKe4.setVisibility(View.INVISIBLE);
                    }
                }
                // Xoa 1 Khach Hang

                if (dataSnapshot.child("hoten").exists() && dataSnapshot.child("hoten1").exists()
                        && dataSnapshot.child("hoten2").exists() && dataSnapshot.child("hoten3").exists()){
                    btnThem.setClickable(false);
                    btnThem.setText("Đã hết chỗ");
                }
                if (dataSnapshot.child("hoten").exists() && dataSnapshot.child("hoten1").exists()
                        && dataSnapshot.child("hoten2").exists()){
                    i = 4;
                }
                if (dataSnapshot.child("hoten").exists() && dataSnapshot.child("hoten1").exists()
                        && dataSnapshot.child("hoten3").exists()){
                    i = 3;
                }
                if (dataSnapshot.child("hoten").exists() && dataSnapshot.child("hoten2").exists()
                        && dataSnapshot.child("hoten3").exists()){
                    i = 2;
                }
                if (dataSnapshot.child("hoten1").exists() && dataSnapshot.child("hoten2").exists()
                        && dataSnapshot.child("hoten3").exists()){
                    i = 1;
                }
                // Xoa 2 khach hang
                if (dataSnapshot.child("hoten").exists()){

                }else {
                    i = 1;
                }

                if (dataSnapshot.child("hoten1").exists() && dataSnapshot.child("hoten2").exists()){

                }else {
                    i = 2;
                }

                if (dataSnapshot.child("hoten1").exists() && dataSnapshot.child("hoten3").exists()){

                }else {
                    i = 2;
                }

                if (dataSnapshot.child("hoten2").exists() && dataSnapshot.child("hoten3").exists()){

                }else {
                    if (dataSnapshot.child("hoten2").exists()){
                        i = 4;
                    }else {
                        i = 3;
                    }
                }

                //Xoa 3 Khach hang
                if (dataSnapshot.child("hoten1").exists()){

                }else {
                    if (dataSnapshot.child("hoten").exists()){
                        i = 2;
                    }else {
                        i = 1;
                    }
                }

                // Truong hop hien Button Them len khi đủ 4 mà xóa 1,2,3
                if (!dataSnapshot.child("hoten").exists() || !dataSnapshot.child("hoten1").exists()
                        || !dataSnapshot.child("hoten2").exists() || !dataSnapshot.child("hoten3").exists()){
//                    btnXacNhan.setVisibility(View.VISIBLE);
                    btnThem.setClickable(true);
                    btnThem.setText("Thêm");
                }else {
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void xuLyAnNut() {
//        if (auth.equals(idChuTro)) {
//            btnLienHe.setVisibility(View.GONE);
//            btnDatphong.setVisibility(View.GONE);
//
//            checkKhachHang();
//        } else {
//            btnDelete.setVisibility(View.GONE);
//            btnEdit.setVisibility(View.GONE);
//
//            btnXacNhan.setVisibility(View.GONE);
//            btnThem.setVisibility(View.GONE);
//
//
//        }
//
//    }

    private void anhXa() {
        imageViewNen = findViewById(R.id.ImageNen);
        imageViewGioiThieu = findViewById(R.id.Imgeview_GioiThieu);
        imageViewDienTich = findViewById(R.id.Imgeview_DienTich);
        imageViewPhone = findViewById(R.id.Imgeview_Phone);
        imageViewViTri = findViewById(R.id.Imgeview_ViTri);
        textViewGiaPhong = findViewById(R.id.GiaPhongTro);
        textViewTenGia = findViewById(R.id.TenGia);
        textViewGioithieu = findViewById(R.id.Text_GioiThieu);
        textViewTenTro = findViewById(R.id.tenNhaTro);
        textViewVitri = findViewById(R.id.Text_ViTri);
        textViewPhone = findViewById(R.id.Text_Phone);
        textViewDienTich = findViewById(R.id.Text_DienTich);
        textViewTrangThaiPhong = findViewById(R.id.TrangThaiPhongTro);
        btnDatphong = findViewById(R.id.butnDatPhong);
        btnLienHe = findViewById(R.id.butnLienHe);
        btnDelete = findViewById(R.id.buttonXoa);
        btnEdit = findViewById(R.id.buttonSua);
        textViewThongKe_TenKh1 = findViewById(R.id.texView_ThongKe_TenKH1);
        textViewThongKe_TenKh2 = findViewById(R.id.texView_ThongKe_TenKH2);
        textViewThongKe_TenKh3 = findViewById(R.id.texView_ThongKe_TenKH3);
        textViewThongKe_TenKh4 = findViewById(R.id.texView_ThongKe_TenKH4);
        textViewThongKe_SDTKh1 = findViewById(R.id.texView_ThongKe_SDTKH1);
        textViewThongKe_SDTKh2 = findViewById(R.id.texView_ThongKe_SDTKH2);
        textViewThongKe_SDTKh3 = findViewById(R.id.texView_ThongKe_SDTKH3);
        textViewThongKe_SDTKh4 = findViewById(R.id.texView_ThongKe_SDTKH4);
        textViewThongKe_MSSV_CMNN_Kh1 = findViewById(R.id.texView_ThongKe_MSSV_CMNN_KH1);
        textViewThongKe_MSSV_CMNN_Kh2 = findViewById(R.id.texView_ThongKe_MSSV_CMNN_KH2);
        textViewThongKe_MSSV_CMNN_Kh3 = findViewById(R.id.texView_ThongKe_MSSV_CMNN_KH3);
        textViewThongKe_MSSV_CMNN_Kh4 = findViewById(R.id.texView_ThongKe_MSSV_CMNN_KH4);
        textViewThongKe_Quequan_Kh1 = findViewById(R.id.texView_ThongKe_QueQuanKH1);
        textViewThongKe_Quequan_Kh2 = findViewById(R.id.texView_ThongKe_QueQuanKH2);
        textViewThongKe_Quequan_Kh3 = findViewById(R.id.texView_ThongKe_QueQuanKH3);
        textViewThongKe_Quequan_Kh4 = findViewById(R.id.texView_ThongKe_QueQuanKH4);
        carView_ThongKe1 = findViewById(R.id.carView_ThongKe1);
        carView_ThongKe2 = findViewById(R.id.carView_ThongKe2);
        carView_ThongKe3 = findViewById(R.id.carView_ThongKe3);
        carView_ThongKe4 = findViewById(R.id.carView_ThongKe4);
        btnThem = findViewById(R.id.btnClickThemKH);
        btnXacNhan = findViewById(R.id.btnClickXacNhanKH);
        editText_HoTenKH = findViewById(R.id.edit_TenKh);
        editText_SDTKH = findViewById(R.id.edit_SDTKh);
        editText_MSSV_CMNNKH = findViewById(R.id.edit_MSSV_CMNNKh);
        editText_QuequanKH = findViewById(R.id.edit_QuequanKh);

        //        if (userType.equals("nguoithuetro")) {
        //            btnDelete.setVisibility(View.GONE);
        //            edit
        //        }
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ViewActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        } else {
            String dialog = "tel:" + textViewPhone.getText().toString();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dialog)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {

            btnDelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);

            btnLienHe.setVisibility(View.VISIBLE);
            btnDatphong.setVisibility(View.VISIBLE);

            btnXacNhan.setVisibility(View.GONE);
            btnThem.setVisibility(View.GONE);

        } else {
            btnLienHe.setVisibility(View.GONE);
            btnDatphong.setVisibility(View.GONE);

            btnDelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);

            checkKhachHang();

        }
    }

    private void xacNhanXoa() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Yêu cầu xóa");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xóa hay không!!!");


        alertDialog.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
                Toast.makeText(ViewActivity.this, "Da Xoa Xong", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ViewActivity.this, QuanLy_ChuTro.class);
//                startActivity(intent);
//                finish();
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
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
