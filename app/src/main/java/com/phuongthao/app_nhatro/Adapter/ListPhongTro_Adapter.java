package com.phuongthao.app_nhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.Models.PhongTro;
import com.phuongthao.app_nhatro.R;

import java.util.ArrayList;

public class ListPhongTro_Adapter extends RecyclerView.Adapter<ListPhongTro_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<ListPhongTro> listPhongTros;

    public ListPhongTro_Adapter(Context context, ArrayList<ListPhongTro> listPhongTros) {
        this.context = context;
        this.listPhongTros = listPhongTros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View phongtroView = inflater.inflate(R.layout.item_listphongtro, parent, false);
        ViewHolder viewHolder = new ViewHolder(phongtroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListPhongTro phongTro = listPhongTros.get(position);
        holder.relativeLayout.setBackgroundResource(Integer.parseInt(phongTro.getImage_Phong()));
        holder.textView1.setText(phongTro.getTenPhong());
    }

    @Override
    public int getItemCount() {
        return listPhongTros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private RelativeLayout relativeLayout;
        private TextView textView1;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            relativeLayout = itemView.findViewById(R.id.Home_Phong);
            cardView = itemView.findViewById(R.id.cardView_PhongTro);
            textView1 = itemView.findViewById(R.id.tenNhaTro);
        }
    }

}
