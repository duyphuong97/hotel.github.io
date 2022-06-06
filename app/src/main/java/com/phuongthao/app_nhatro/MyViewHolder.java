package com.phuongthao.app_nhatro;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {
    View v;
     CardView cardView;
     RelativeLayout relativeLayout, layout_ViTri;
     TextView textView;
     TextView textView1;
     TextView textView2;
     TextView textView3;
     TextView textView4;
     ImageView imageViewNen, imageViewViTri;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        relativeLayout = itemView.findViewById(R.id.Home_Phong);
        layout_ViTri = itemView.findViewById(R.id.Layout_ViTri);
        cardView = itemView.findViewById(R.id.cardView_PhongTro);
        textView = itemView.findViewById(R.id.GiaPhongTro);
        textView1 = itemView.findViewById(R.id.tenNhaTro);
        textView2 = itemView.findViewById(R.id.Text_ViTri);
        textView3 = itemView.findViewById(R.id.TenGia);
        textView4 = itemView.findViewById(R.id.TrangThaiPhongTro);
        imageViewNen = itemView.findViewById(R.id.ImageNen);
        imageViewViTri = itemView.findViewById(R.id.Imgeview_ViTri);
        v = itemView;
    }
}
