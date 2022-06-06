package com.phuongthao.app_nhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Models.DanhSachDaDuyet;
import com.phuongthao.app_nhatro.Models.DatPhongTro1;
import com.phuongthao.app_nhatro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BangDuyetAdapter extends RecyclerView.Adapter<BangDuyetAdapter.ViewHolder> {
    private Context context1;
    private ArrayList<DanhSachDaDuyet> danhSachDaDuyets;

    public BangDuyetAdapter(Context context1, ArrayList<DanhSachDaDuyet> danhSachDaDuyets) {
        this.context1 = context1;
        this.danhSachDaDuyets = danhSachDaDuyets;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context1).inflate(R.layout.item_hienthibangduyet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DanhSachDaDuyet danhSachDaDuyet = danhSachDaDuyets.get(position);
        String image = danhSachDaDuyet.getHinhCMNN();
        String hoten = danhSachDaDuyet.getHoten();
        String sdt = String.valueOf(danhSachDaDuyet.getSdt());
        String tenPhongTro = danhSachDaDuyet.getTenPhong();
        String soCMNN = danhSachDaDuyet.getSoCMNN();
        String namSinhUser = danhSachDaDuyet.getNamsinh();
        String queQuanUser = danhSachDaDuyet.getQuequan();
        Picasso.with(context1).load(image).into(holder.imageViewUser);
        holder.textView_TenUser.setText(hoten);
        holder.textView_SDTUser.setText(sdt);
        holder.textView_TenPhongTro.setText(tenPhongTro);
        holder.textView_SoCMNNUser.setText(soCMNN);
        holder.textView_NamSinhUSer.setText(namSinhUser);
        holder.textView_QueQuanUser.setText(queQuanUser);
    }

    @Override
    public int getItemCount() {
        return danhSachDaDuyets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewUser;
        private TextView textView_TenUser, textView_SDTUser, textView_TenPhongTro, textView_SoCMNNUser, textView_NamSinhUSer, textView_QueQuanUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewUser = itemView.findViewById(R.id.ImageCMNN);
            textView_TenUser = itemView.findViewById(R.id.tenKHang);
            textView_SDTUser = itemView.findViewById(R.id.Hien_SDT_KH);
            textView_TenPhongTro = itemView.findViewById(R.id.Text_Hien_TenPhong);
            textView_SoCMNNUser = itemView.findViewById(R.id.Text_Hien_CMNN);
            textView_NamSinhUSer = itemView.findViewById(R.id.Text__Hien_NSinh);
            textView_QueQuanUser = itemView.findViewById(R.id.Text_Hien_Quequan);
        }
    }
}
