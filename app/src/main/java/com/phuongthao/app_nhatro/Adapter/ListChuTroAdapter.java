package com.phuongthao.app_nhatro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.MyListPhong;
import com.phuongthao.app_nhatro.QuanLy_ChuTro;
import com.phuongthao.app_nhatro.R;
import com.phuongthao.app_nhatro.SoPhongTroActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListChuTroAdapter extends RecyclerView.Adapter<ListChuTroAdapter.ViewHolder> {

    Context context;
    ArrayList<ListPhongTro> listPhongTroModel;

    public ListChuTroAdapter(Context context, ArrayList<ListPhongTro> listPhongTroModel) {
        this.context = context;
        this.listPhongTroModel = listPhongTroModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_list_phong, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListPhongTro model = listPhongTroModel.get(position);
        holder.textView1List.setText(model.getTenPhong());
        Picasso.with(context).load(model.getImageUrl()).into(holder.imageViewNenList);
        //Click vào mỗi phần tử sẽ chuyển sang ViewActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SoPhongTroActivity.class);
                intent.putExtra("TenPhongTroHA", model.getTenPhong());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listPhongTroModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewList;
        RelativeLayout relativeLayoutList;
        TextView textView1List;
        ImageView imageViewNenList;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            cardViewList = itemView.findViewById(R.id.cardView_PhongTroList);
            relativeLayoutList = itemView.findViewById(R.id.Home_PhongList);
            textView1List = itemView.findViewById(R.id.tenNhaTro_List);
            imageViewNenList = itemView.findViewById(R.id.ImageNenTroList);
        }
    }
}
