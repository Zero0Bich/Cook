package com.app.admin.cook.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.admin.cook.adapter.CallbackGetChild;
import com.app.admin.cook.object.LoaiMonAn;
import com.app.admin.cook.presenter.IPresenterExpandMenu;
import com.app.admin.cook.ulti.DownloadJsonUseVolley;
import com.app.admin.cook.ulti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 4/6/2018.
 */

public class ModelExpandMenu implements CallbackXuLyJson {
    private IPresenterExpandMenu iPresenterExpandMenu;
    private CallbackGetChild callbackGetChild;
    private Context context;
    private boolean child = false;
    private int id;

    public ModelExpandMenu(Context context, CallbackGetChild callbackGetChild) {
        this.context = context;
        this.callbackGetChild = callbackGetChild;
        this.child = true;
    }

    public ModelExpandMenu(IPresenterExpandMenu iPresenterExpandMenu, Context context) {
        this.iPresenterExpandMenu = iPresenterExpandMenu;
        this.context = context;
        this.child = false;
    }

    public void dowloadJson(String maLoaiCha) {
        id = Integer.parseInt(maLoaiCha);
//        Log.i("ii", String.valueOf(id));
        Map<String, String> map = new HashMap<>();
        map.put("maloaicha", maLoaiCha);
        DownloadJsonUseVolley dl = new DownloadJsonUseVolley(context, this, Server.urlLoaiMonAn, map);
        dl.downloadDataUsePost();
    }

    @Override
    public void xuLyJson(String s) {
        ArrayList<LoaiMonAn> loaiMonAns = new ArrayList<>();
        Log.d("aaaa", "g");

        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                int maLoaiMa = object.getInt("id");
                String tenLoaiMa = object.getString("tenLoaiMa");
                int maLoaiCha = object.getInt("maLoaiCha");

                LoaiMonAn loaiMonAn = new LoaiMonAn();
                loaiMonAn.setMaLoaiMa(maLoaiMa);
                loaiMonAn.setTenLoaiMa(tenLoaiMa);
                loaiMonAn.setMaLoaiCha(maLoaiCha);

                loaiMonAns.add(loaiMonAn);
            }

            if (child) {
//                Log.i("i", String.valueOf(id));
                callbackGetChild.layLoaiMonAnChild(id, loaiMonAns);
            } else {
                iPresenterExpandMenu.xuLyExpandMenu(loaiMonAns);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
