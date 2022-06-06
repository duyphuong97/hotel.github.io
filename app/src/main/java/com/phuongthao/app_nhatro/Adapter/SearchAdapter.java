package com.phuongthao.app_nhatro.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phuongthao.app_nhatro.Login_Logout.Login_Logout;
import com.phuongthao.app_nhatro.Models.ListPhongTro;
import com.phuongthao.app_nhatro.Models.ListPhongTroSearch;
import com.phuongthao.app_nhatro.MyListPhong;
import com.phuongthao.app_nhatro.R;
import com.phuongthao.app_nhatro.Search;
import com.phuongthao.app_nhatro.SoPhongTroActivity;
import com.phuongthao.app_nhatro.ViewActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ListPhongTroSearch> listPhongTro;

    public SearchAdapter(Context context, ArrayList<ListPhongTroSearch> listPhongTro) {
        this.context = context;
        this.listPhongTro = listPhongTro;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_list_phong, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

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
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
               alertDialog.setTitle("Yêu cầu xóa NHÀ TRỌ");
               alertDialog.setIcon(R.mipmap.ic_launcher);
               alertDialog.setMessage("Bạn có muốn xóa không!!!");


               alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("PhongTro");
                       ref.child(list.getIdNhatro()).removeValue();
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
