package com.app.admin.cook.presenter;

import android.content.Context;

import com.app.admin.cook.model.ModelExpandMenu;
import com.app.admin.cook.object.LoaiMonAn;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;
import com.app.admin.cook.view.MainActivity;
import com.app.admin.cook.view.ViewExpandMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 4/6/2018.
 */

public class PresenterExpandMenu implements IPresenterExpandMenu {
    private Context context;
    private ModelExpandMenu modelExpandMenu;
    private ViewExpandMenu viewExpandMenu;

    public PresenterExpandMenu(Context context, ViewExpandMenu viewExpandMenu) {
        this.context = context;
        this.viewExpandMenu = viewExpandMenu;
    }

    @Override
    public void yeuCauDuLieu() {
        modelExpandMenu = new ModelExpandMenu(this, context);
        modelExpandMenu.dowloadJson("0");
    }

    @Override
    public void xuLyExpandMenu(List<LoaiMonAn> loaiMonAns) {
        viewExpandMenu.hienThiExpandMenu(loaiMonAns);
    }
}
