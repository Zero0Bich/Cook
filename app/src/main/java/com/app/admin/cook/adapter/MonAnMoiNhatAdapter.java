package com.app.admin.cook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.admin.cook.R;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.view.ChiTietMonAnActivity;
import com.app.admin.cook.view.MainActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 4/10/2018.
 */

public class MonAnMoiNhatAdapter extends RecyclerView.Adapter<MonAnMoiNhatAdapter.ViewHolder> {
    private Context context;
    private List<MonAn> monAnList;

    public MonAnMoiNhatAdapter(Context context, List<MonAn> monAnList) {
        this.context = context;
        this.monAnList = monAnList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dong_rv_ma_moi_nhat, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.txtView.setText(monAn.getTenMa());
        Glide.with(context).load(monAn.getHinhMa())
                .placeholder(R.drawable.image_is_loading)
                .error(R.drawable.error_load_image).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private TextView txtView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgViewMonAnMoiNhat);
            txtView = itemView.findViewById(R.id.txtTenMonAnMoiNhat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietMonAnActivity.class);
                    MonAn monAn = monAnList.get(getAdapterPosition());
                    intent.putExtra("monan", monAn);
                    intent.putExtra("maNd", MainActivity.maNd);
                    context.startActivity(intent);
                }
            });
        }
    }
}
