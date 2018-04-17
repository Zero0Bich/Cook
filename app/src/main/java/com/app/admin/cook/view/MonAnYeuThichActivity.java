package com.app.admin.cook.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.adapter.DanhSachMonAnAdapter;
import com.app.admin.cook.object.LoaiMonAn;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.presenter.IPresenterDanhSachMonAn;
import com.app.admin.cook.presenter.PresenterDanhSachMonAn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MonAnYeuThichActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btnSortName, btnSortTime;
    private RecyclerView rvMonAn;
    private DanhSachMonAnAdapter adapter;
    private List<MonAn> monAnList;
    private NguoiDung nguoiDung;
    private RecyclerView.LayoutManager layoutManager;
    private boolean sort_a_z = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mon_an);

        Intent intent = getIntent();
        nguoiDung = (NguoiDung) intent.getSerializableExtra("nguoidung");
//        Toast.makeText(this, "" + nguoiDung.getMonAns().size(), Toast.LENGTH_SHORT).show();

//        vì giống với màn hình ds món ăn nên dùng chung layout và adapter
        toolbar = findViewById(R.id.toolBarDanhSachMonAn);
        btnSortName = findViewById(R.id.btnSapXepTheoTen);
        btnSortTime = findViewById(R.id.btnSapXepTheoThoiGian);
        rvMonAn = findViewById(R.id.rvDanhSachMonAn);

        monAnList = nguoiDung.getMonAns();
        adapter = new DanhSachMonAnAdapter(this, monAnList);

        rvMonAn.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rvMonAn.setLayoutManager(layoutManager);
        rvMonAn.setAdapter(adapter);

        toolbar.setTitle("Danh sách món ăn yêu thích của bạn");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSortName.setOnClickListener(this);
        btnSortTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSapXepTheoTen) {
            Comparator<MonAn> comp = null;
            if (sort_a_z) {
                comp = new Comparator<MonAn>() {
                    @Override
                    public int compare(MonAn monAn, MonAn t1) {
                        return t1.getTenMa().compareTo(monAn.getTenMa());
                    }
                };
                sort_a_z = false;
            } else {
                comp = new Comparator<MonAn>() {
                    @Override
                    public int compare(MonAn monAn, MonAn t1) {
                        return monAn.getTenMa().compareTo(t1.getTenMa());
                    }
                };
                sort_a_z = true;
            }

            Collections.sort(monAnList, comp);
            adapter.notifyDataSetChanged();
        } else {
            Comparator<MonAn> comp = new Comparator<MonAn>() {
                @Override
                public int compare(MonAn monAn, MonAn t1) {
                    String timeMonAn = tinhTime(monAn);
                    String timeT1 = tinhTime(t1);

                    return timeMonAn.compareTo(timeT1);
                }
            };
            Collections.sort(monAnList, comp);
            adapter.notifyDataSetChanged();
        }
    }

    private String tinhTime(MonAn monAn) {
        int time = 0;

        String str = monAn.getThoiGian();

        if (str.endsWith("p")) {
            if (str.length() == 2) {
                time = Integer.parseInt(str.substring(0, 1));
            } else {
                time = Integer.parseInt(str.substring(0, 2));
            }
        } else {
            String[] arr = str.split("h");
            int h = Integer.parseInt(arr[0]);
            int m = 0;
            if (arr.length != 2) {
                m = Integer.parseInt(arr[1]);
            }

            time = (h * 60) + m;
        }

        return String.valueOf(time);
    }
}
