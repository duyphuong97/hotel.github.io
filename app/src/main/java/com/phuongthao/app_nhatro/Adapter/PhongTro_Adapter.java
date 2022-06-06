package com.phuongthao.app_nhatro.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phuongthao.app_nhatro.Models.PhongTro;
import com.phuongthao.app_nhatro.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhongTro_Adapter extends RecyclerView.Adapter<PhongTro_Adapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<PhongTro> phongTro;
    private ArrayList<PhongTro> roomListFiltered;

    public PhongTro_Adapter(Context context, ArrayList<PhongTro> phongTro) {
        this.context = context;
        this.phongTro = phongTro;
        this.roomListFiltered = new ArrayList<>(phongTro);
    }

    @NonNull
    @Override
    public PhongTro_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View phongtroView = inflater.inflate(R.layout.item_danhsachphong, parent, false);
        ViewHolder viewHolder = new ViewHolder(phongtroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhongTro_Adapter.ViewHolder holder, int position) {
        PhongTro phongTro1 = phongTro.get(position);
        holder.relativeLayout.setBackgroundResource(Integer.parseInt(phongTro1.getImage_Phong()));
        holder.textView.setText(phongTro1.getGia_Phong() + "");
        holder.textView1.setText(phongTro1.getTenPhong());
        holder.textView2.setText(phongTro1.getVitri_Phong());
    }

    @Override
    public int getItemCount() {
        return phongTro.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //Run background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PhongTro> filtered = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filtered.addAll(roomListFiltered);
            }else {
                for (PhongTro phongTroo: roomListFiltered){
                    Log.d("111", "performFiltering: " + phongTroo.getTenPhong());
                    Log.d("222", "performFiltering: " + constraint.toString().toLowerCase());
                    if (phongTroo.getTenPhong().toLowerCase().contains(constraint.toString().toLowerCase())){
//                        Toast.makeText(context, "Da vao dc", Toast.LENGTH_SHORT).show();
                        Log.d("333", "performFiltering: vao dcccccccc" );
//                        filtered.clear();
                        filtered.add(phongTroo);
                    }else{
//                        Toast.makeText(context, "Ra ne", Toast.LENGTH_SHORT).show();
                        Log.d("333", "performFiltering: K vaoooooo");
                    }
                }
            }


            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            Log.d("444", "performFiltering: " + filterResults.values);
            return filterResults;
        }
        //Run on a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            phongTro.clear();
            phongTro.addAll((Collection<? extends PhongTro>) results.values);
//            roomListFiltered = (ArrayList<PhongTro>) results.values;
//            Toast.makeText(context, "Da chay ham nay", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private RelativeLayout relativeLayout, layout_ViTri;
        private TextView textView;
        private TextView textView1;
        private TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.Home_Phong);
            layout_ViTri = itemView.findViewById(R.id.Layout_ViTri);
            cardView = itemView.findViewById(R.id.cardView_PhongTro);
            textView = itemView.findViewById(R.id.GiaPhongTro);
            textView1 = itemView.findViewById(R.id.tenNhaTro);
            textView2 = itemView.findViewById(R.id.Text_ViTri);
        }
    }
}
