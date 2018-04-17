package com.app.admin.cook.presenter;

import android.content.Context;

import com.app.admin.cook.model.ModelMonAnYeuThich;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.view.ViewMonAn;

import java.util.List;

/**
 * Created by Admin on 4/15/2018.
 */

public class PresenterMonAnYeuThich implements IPresenterMonAnYeuThich {
    private Context context;
    private ModelMonAnYeuThich modelMonAnYeuThich;
    private ViewMonAn viewMonAn;
    private int maNd;

    public PresenterMonAnYeuThich(Context context, ViewMonAn viewMonAn, int maNd) {
        this.context = context;
        this.viewMonAn = viewMonAn;
        this.maNd = maNd;
        this.modelMonAnYeuThich = new ModelMonAnYeuThich(context, this, maNd);
    }

    @Override
    public void layDuLieuMonAn() {
        modelMonAnYeuThich.downloadJson();
    }

    @Override
    public void ganDuLieuMonAn(List<MonAn> monAnList) {
        viewMonAn.hienThiMaYeuThich(monAnList);
    }
}
