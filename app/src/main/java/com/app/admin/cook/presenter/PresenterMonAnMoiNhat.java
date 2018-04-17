package com.app.admin.cook.presenter;

import android.content.Context;

import com.app.admin.cook.model.ModelMonAnMoiNhat;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.view.ViewMonAn;

import java.util.List;

/**
 * Created by Admin on 4/10/2018.
 */

public class PresenterMonAnMoiNhat implements IPresenterMonAnMoiNhat {
    private Context context;
    private ViewMonAn viewMonAnMoiNhat;
    private ModelMonAnMoiNhat modelMonAnMoiNhat;

    public PresenterMonAnMoiNhat(Context context, ViewMonAn viewMonAnMoiNhat) {
        this.context = context;
        this.viewMonAnMoiNhat = viewMonAnMoiNhat;
        this.modelMonAnMoiNhat = new ModelMonAnMoiNhat(context, this);
    }

    @Override
    public void layDuLieu() {
        modelMonAnMoiNhat.downloadJson();
    }

    @Override
    public void hienThiDuLieu(List<MonAn> monAnList) {
        viewMonAnMoiNhat.hienThiMaMoiNhat(monAnList);
    }


}
