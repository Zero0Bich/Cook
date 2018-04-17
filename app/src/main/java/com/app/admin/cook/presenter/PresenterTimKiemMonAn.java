package com.app.admin.cook.presenter;

import android.content.Context;
import android.widget.ProgressBar;

import com.app.admin.cook.model.ModelMonAnTheoLoaiMonAn;
import com.app.admin.cook.model.ModelTimKiemMonAn;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.view.ViewDanhSachMonAn;

import java.util.List;

/**
 * Created by Admin on 4/16/2018.
 */

public class PresenterTimKiemMonAn implements IPresenterTimKiemMonAn {
    private Context context;
    private ViewDanhSachMonAn viewDanhSachMonAn;
    private ModelTimKiemMonAn modelTimKiemMonAn;
    private String tenMa;
    private ProgressBar progressBar;
    private boolean isLoading = false;

    public PresenterTimKiemMonAn(Context context, ViewDanhSachMonAn viewDanhSachMonAn, String tenMa, ProgressBar progressBar) {
        this.context = context;
        this.viewDanhSachMonAn = viewDanhSachMonAn;
        this.tenMa = tenMa;
        this.progressBar = progressBar;
        modelTimKiemMonAn = new ModelTimKiemMonAn(context, this, tenMa);
    }

    @Override
    public void layDanhSachMonAn() {
        modelTimKiemMonAn.downloadJson();
    }

    @Override
    public void layLoadMore() {
        modelTimKiemMonAn.downloadJsonLoadMore();
    }

    @Override
    public void hienThi(List<MonAn> list, boolean chk) {
        viewDanhSachMonAn.hienThiDanhSachMonAn(list, chk);
    }
}
