package com.app.admin.cook.presenter;

import android.content.Context;

import com.app.admin.cook.model.ModelChiTietMonAn;
import com.app.admin.cook.view.ViewChiTietMonAn;

/**
 * Created by Admin on 4/16/2018.
 */

public class PresenterChiTietMonAn implements IPresenterChiTietMonAn {
    private Context context;
    private ViewChiTietMonAn viewChiTietMonAn;
    private ModelChiTietMonAn modelChiTietMonAn;
    private String url;
    private int maMa, maNd;

    public PresenterChiTietMonAn(Context context, ViewChiTietMonAn viewChiTietMonAn, String url, int maMa, int maNd) {
        this.context = context;
        this.viewChiTietMonAn = viewChiTietMonAn;
        this.url = url;
        this.maMa = maMa;
        this.maNd = maNd;
        this.modelChiTietMonAn = new ModelChiTietMonAn(context, this, url, maMa, maNd);
    }

    @Override
    public void xuLyDuLieu() {
        modelChiTietMonAn.downloadJson();
    }

    @Override
    public void xuLyKetQua(String s) {
        viewChiTietMonAn.ketQuaXuLy(s);
    }
}
