package com.app.admin.cook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.view.ChiTietMonAnActivity;
import com.app.admin.cook.view.DanhSachMonAnActivity;
import com.app.admin.cook.view.MainActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 4/11/2018.
 */

public class DanhSachMonAnAdapter extends RecyclerView.Adapter<DanhSachMonAnAdapter.ViewHolder> {
    private Context context;
    private List<MonAn> monAnList;

    public DanhSachMonAnAdapter(Context context, List<MonAn> monAnList) {
        this.context = context;
        this.monAnList = monAnList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dong_ds_mon_an, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.txtTen.setText(monAn.getTenMa());
        holder.txtThoiGian.setText(monAn.getThoiGian());
        Glide.with(context).load(monAn.getHinhMa())
                .placeholder(R.drawable.image_is_loading)
                .error(R.drawable.error_load_image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtTen;
        private TextView txtThoiGian;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgViewMonAn);
            txtTen = itemView.findViewById(R.id.txtTenMonAn);
            txtThoiGian = itemView.findViewById(R.id.txtThoiGianMonAn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MonAn ma = monAnList.get(getAdapterPosition());
//                    Toast.makeText(context, ma.getTenMa(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ChiTietMonAnActivity.class);
                    intent.putExtra("monan", ma);
                    intent.putExtra("maNd", MainActivity.maNd);
                    context.startActivity(intent);
                }
            });
        }
    }
}
