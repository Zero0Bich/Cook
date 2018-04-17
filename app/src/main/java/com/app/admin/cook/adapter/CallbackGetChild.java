package com.app.admin.cook.adapter;

import com.app.admin.cook.object.LoaiMonAn;
import com.app.admin.cook.object.MonAn;

import java.util.List;

/**
 * Created by Admin on 4/6/2018.
 */

public interface CallbackGetChild {
    void layLoaiMonAnChild(int id, List<LoaiMonAn> loaiMonAns);

    void layMonAnTheoLoaiMonAn(int maLoaiCha, int maLoaiCon, List<MonAn> monAnList);
}
