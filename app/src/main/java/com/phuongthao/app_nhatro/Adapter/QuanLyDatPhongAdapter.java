package com.phuongthao.app_nhatro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Models.DatPhongTro;
import com.phuongthao.app_nhatro.Models.DatPhongTro1;
import com.phuongthao.app_nhatro.R;
import com.phuongthao.app_nhatro.ViewDatPhong;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuanLyDatPhongAdapter extends RecyclerView.Adapter<QuanLyDatPhongAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DatPhongTro1> uslistDatPhongTro;

    public QuanLyDatPhongAdapter(Context context, ArrayList<DatPhongTro1> uslistDatPhongTro) {
        this.context = context;
        this.uslistDatPhongTro = uslistDatPhongTro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_my_view_dat_phong,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DatPhongTro1 datPhongTro = uslistDatPhongTro.get(position);
        String image = datPhongTro.getImageUrl();
        String hoten = datPhongTro.getTen_KH();
        String sdt = String.valueOf(datPhongTro.getSDT_KH());
        Picasso.with(context).load(image).into(holder.image);
        holder.hoten.setText(hoten);
        holder.HienSDT.setText(sdt);
        Log.d("aaa", datPhongTro.getTenPhong() +"kkkkkkkkkkk");
        Log.d("aaa", datPhongTro.getCMNN_KH() +"kkkkkkkkkkk");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewDatPhong.class);
                intent.putExtra("hoten",datPhongTro.getTen_KH());
                intent.putExtra("sdt", datPhongTro.getSDT_KH());
                intent.putExtra("namsinh", datPhongTro.getNamSinh_KH());
                intent.putExtra("quequan", datPhongTro.getQuequan_KH());
                intent.putExtra("hinhCMNN", datPhongTro.getImageUrl());
                intent.putExtra("SoCMNN", datPhongTro.getCMNN_KH());
                intent.putExtra("idKhachHang", datPhongTro.getIdKhachHang());
                intent.putExtra("idPhong", datPhongTro.getIdPhong());
                intent.putExtra("TenPhong", datPhongTro.getTenPhong());
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return uslistDatPhongTro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView hoten,HienSDT;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
             image= itemView.findViewById(R.id.ImageKH);

            hoten = itemView.findViewById(R.id.ten_KhachHang);
            HienSDT = itemView.findViewById(R.id.SDT_KhachHang);
        }
    }


}

