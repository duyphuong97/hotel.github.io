package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyViewDatPhong extends RecyclerView.ViewHolder {
    View v;
    RelativeLayout relativeLayout_DatPhong;
    CardView cardView_KH;
    ImageView imageViewKh;
    TextView textViewTenKH, textViewSDT, textViewHienSDT;

    public MyViewDatPhong(@NonNull View itemView) {
        super(itemView);
        relativeLayout_DatPhong = itemView.findViewById(R.id.Home_DatPhong);
        cardView_KH = itemView.findViewById(R.id.cardView_KH);
        imageViewKh = itemView.findViewById(R.id.ImageKH);
        textViewSDT = itemView.findViewById(R.id.SDT);
        textViewHienSDT = itemView.findViewById(R.id.SDT_KhachHang);
        v = itemView;
    }
}
