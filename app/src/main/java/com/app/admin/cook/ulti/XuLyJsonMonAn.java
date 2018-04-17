package com.app.admin.cook.ulti;

import com.app.admin.cook.object.MonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/15/2018.
 */

public class XuLyJsonMonAn {
    public static ArrayList<MonAn> xuLy(String s) {
        ArrayList<MonAn> monAnList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                int maMa = object.getInt("maMa");
                String tenMa = object.getString("tenMa");
                String hinhMa = object.getString("hinhMa");
                String gioiThieu = object.getString("gioiThieu");
                String nguyenLieu = object.getString("nguyenLieu");
                String cheBien = object.getString("cheBien");
                String thoiGian = object.getString("thoiGian");

                MonAn monAn = new MonAn(maMa, tenMa, hinhMa, gioiThieu, nguyenLieu, cheBien, thoiGian);
                monAnList.add(monAn);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return monAnList;
    }
}