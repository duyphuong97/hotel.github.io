package com.phuongthao.app_nhatro;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyListPhong extends RecyclerView.ViewHolder {

    View v;
    CardView cardViewList;
    RelativeLayout relativeLayoutList;
    TextView textView1List;
    ImageView imageViewNenList;

    public MyListPhong(@NonNull View itemView) {
        super(itemView);
        cardViewList = itemView.findViewById(R.id.cardView_PhongTroList);
        relativeLayoutList = itemView.findViewById(R.id.Home_PhongList);
        textView1List = itemView.findViewById(R.id.tenNhaTro_List);
        imageViewNenList = itemView.findViewById(R.id.ImageNenTroList);
        v = itemView;
    }
}
