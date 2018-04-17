package com.app.admin.cook.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.model.ModelExpandMenu;
import com.app.admin.cook.model.ModelMonAnTheoLoaiMonAn;
import com.app.admin.cook.object.LoaiMonAn;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.view.DanhSachMonAnActivity;
import com.app.admin.cook.view.MainActivity;

import java.util.List;

/**
 * Created by Admin on 4/6/2018.
 */

public class ExpandMenuAdapter extends BaseExpandableListAdapter implements CallbackGetChild{
    private Context context;
    private List<LoaiMonAn> listLoaiMonAns;
    private ModelExpandMenu modelExpandMenu;
    private ModelMonAnTheoLoaiMonAn modelMonAnTheoLoaiMonAn;

    public ExpandMenuAdapter(Context context, List<LoaiMonAn> listLoaiMonAns) {
        this.context = context;
        this.listLoaiMonAns = listLoaiMonAns;

        for (int i = 0; i < listLoaiMonAns.size(); i++) {
            modelExpandMenu = new ModelExpandMenu(context, this);
            String id = String.valueOf(listLoaiMonAns.get(i).getMaLoaiMa());
            modelExpandMenu.dowloadJson(id);
        }

//        for (int i = 0; i < listLoaiMonAns.size(); i++) {
//            for (int j = 0; j < listLoaiMonAns.get(i).getListCon().size(); j++) {
//                modelMonAnTheoLoaiMonAn = new ModelMonAnTheoLoaiMonAn(context, this, i, j);
//                modelMonAnTheoLoaiMonAn.downloadJson();
//            }
//        }
    }

    @Override
    public int getGroupCount() {
        return listLoaiMonAns.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listLoaiMonAns.get(i).getListCon().size();
    }

    @Override
    public Object getGroup(int i) {
        return listLoaiMonAns.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listLoaiMonAns.get(i).getListCon().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void layLoaiMonAnChild(int id, List<LoaiMonAn> loaiMonAns) {
//        Log.i("iiiii", id + "---" + loaiMonAns.size());
        listLoaiMonAns.get(id - 1).setListCon(loaiMonAns);
//        viet ra 1 phuong thuc roi khi onclick vào de hien thi navigation thi moi goi ham lay monan
//        hoac su dung wait, syn trong thread
    }

    @Override
    public void layMonAnTheoLoaiMonAn(int maLoaiCha, int maLoaiCon, List<MonAn> monAnList) {
        LoaiMonAn loaiMonAn = (LoaiMonAn) getChild(maLoaiCha, maLoaiCon);
        loaiMonAn.setDsMonAn(monAnList);
        Log.i("infor", String.valueOf(loaiMonAn.getDsMonAn().size()));
    }

    private class ViewHolderGroup {
        TextView txtTenLoaiMaGroup;
        ImageView imgView;
    }

    private class ViewHolderChild {
        TextView txtTenLoaiMaChild;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderGroup holder;
        if (view == null) {
            holder = new ViewHolderGroup();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_dong_expand_group, viewGroup, false);

            holder.txtTenLoaiMaGroup = view.findViewById(R.id.txtTenLoaiMaGroup);
            holder.imgView = view.findViewById(R.id.imgViewShowChild);

            view.setTag(holder);
        } else {
            holder = (ViewHolderGroup) view.getTag();
        }

        LoaiMonAn loaiMonAn = (LoaiMonAn) getGroup(i);
        holder.txtTenLoaiMaGroup.setText(loaiMonAn.getTenLoaiMa());
        if (b) {
            holder.imgView.setImageResource(R.drawable.ic_miniature_menu);
            view.setBackgroundResource(R.color.colorGray);
        } else {
            holder.imgView.setImageResource(R.drawable.ic_extend_menu);
            view.setBackgroundResource(R.color.colorWhite);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderChild holder;
        if (view == null) {
            holder = new ViewHolderChild();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_dong_expand_child, viewGroup, false);

            holder.txtTenLoaiMaChild = view.findViewById(R.id.txtTenLoaiMaChild);

            view.setTag(holder);
        } else {
            holder = (ViewHolderChild) view.getTag();
        }

        final LoaiMonAn loaiMonAn = (LoaiMonAn) getChild(i, i1);

        holder.txtTenLoaiMaChild.setText(loaiMonAn.getTenLoaiMa());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachMonAnActivity.class);
                intent.putExtra("loaimonan", loaiMonAn);
                intent.putExtra("maNd", MainActivity.maNd);
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
