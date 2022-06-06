package com.phuongthao.app_nhatro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Adapter.QuanLyDatPhongAdapter;
import com.phuongthao.app_nhatro.Models.DatPhongTro1;
import com.phuongthao.app_nhatro.Models.PhongTro1;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhongTro1_Adapter  extends RecyclerView.Adapter<PhongTro1_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<PhongTro1> uslistDatPhongTro;

    public PhongTro1_Adapter(Context context, ArrayList<PhongTro1> uslistDatPhongTro) {
        this.context = context;
        this.uslistDatPhongTro = uslistDatPhongTro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_my_view_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PhongTro1 phongTro1 = uslistDatPhongTro.get(position);
        holder.tenNhaTro.setText(phongTro1.getTenPhong());
        holder.GiaPhongTro.setText(phongTro1.getGiaPhong());
        holder.Text_ViTri.setText(phongTro1.getVitriPhong());
        String tragthai = phongTro1.getTrangThai();

        if (tragthai.equals("conphong")){
            holder.TrangThaiPhongTro.setText("CÒN PHÒNG");
        }else if (tragthai.equals("daduocdat")){
            holder.TrangThaiPhongTro.setText("ĐÃ ĐƯỢC ĐẶT");

        }else {
            holder.TrangThaiPhongTro.setText("HẾT PHÒNG");

        }



        Picasso.with(context).load(phongTro1.getImageUrl()).into(holder.ImageNen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra("PhongTroKey", phongTro1.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return uslistDatPhongTro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tenNhaTro,GiaPhongTro,Text_ViTri,TrangThaiPhongTro;
        private ImageView ImageNen;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tenNhaTro= itemView.findViewById(R.id.tenNhaTro);
            ImageNen = itemView.findViewById(R.id.ImageNen);

            GiaPhongTro = itemView.findViewById(R.id.GiaPhongTro);
            Text_ViTri = itemView.findViewById(R.id.Text_ViTri);
            TrangThaiPhongTro = itemView.findViewById(R.id.TrangThaiPhongTro);
        }
    }
}
