package com.phuongthao.app_nhatro.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phuongthao.app_nhatro.Models.DatPhongTro1;
import com.phuongthao.app_nhatro.Models.UserChuTro;
import com.phuongthao.app_nhatro.R;

import java.util.ArrayList;

public class UserChuTroAdapter extends RecyclerView.Adapter<UserChuTroAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UserChuTro> userChuTroList;

    public UserChuTroAdapter(Context context, ArrayList<UserChuTro> userChuTroList) {
        this.context = context;
        this.userChuTroList = userChuTroList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.itemuserchutro,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UserChuTro userChuTro = userChuTroList.get(position);
        String email = userChuTro.getEmail();
        String hotenChuTro = userChuTro.getTenChuTro();
        final String id =  userChuTro.getIdChuTro();
        holder.emailTV.setText("Email: " + email);
        holder.hotenChuTroTv.setText("Tên chủ trọ: " + hotenChuTro);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Yêu cầu xóa CHỦ TRỌ");
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Bạn có muốn xóa không!!!");


                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("UsersChuTro");
                        ref.child(id).removeValue();
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return userChuTroList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hotenChuTroTv,emailTV;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            hotenChuTroTv = itemView.findViewById(R.id.textViewTenChuTro);
            emailTV = itemView.findViewById(R.id.textViewEmail);
        }
    }
}
