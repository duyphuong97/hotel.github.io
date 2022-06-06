package com.phuongthao.app_nhatro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;
import com.phuongthao.app_nhatro.R;
import com.phuongthao.app_nhatro.SoPhongTroActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListMotelAdapter extends RecyclerView.Adapter<ListMotelAdapter.ViewHolder> {


    private Context context;
    private ArrayList<ListPhongTroSearch> listPhongTro;

    public ListMotelAdapter(Context context, ArrayList<ListPhongTroSearch> listPhongTro) {
        this.context = context;
        this.listPhongTro = listPhongTro;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_list_motel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListPhongTroSearch list = listPhongTro.get(position);
        holder.tenNhaTro_List.setText(list.getTenPhong());
        Picasso.with(context).load(list.getImageUrl()).into(holder.ImageNenTroList);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SoPhongTroActivity.class);
                intent.putExtra("TenPhongTroHA", list.getTenPhong());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhongTro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ImageNenTroList;
        private TextView tenNhaTro_List;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageNenTroList = itemView.findViewById(R.id.ImageNenTroList);
            tenNhaTro_List = itemView.findViewById(R.id.tenNhaTro_List);
        }
    }
}
