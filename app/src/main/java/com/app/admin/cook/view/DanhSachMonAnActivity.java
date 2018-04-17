package com.app.admin.cook.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.app.admin.cook.presenter.IPresenterDanhSachMonAn;
import com.app.admin.cook.presenter.PresenterDanhSachMonAn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DanhSachMonAnActivity extends AppCompatActivity implements ViewDanhSachMonAn, View.OnClickListener {
    private Toolbar toolbar;
    private Button btnSortName, btnSortTime;
    private RecyclerView rvMonAn;
    private ProgressBar progressBar;
    private DanhSachMonAnAdapter adapter;
    private List<MonAn> monAnList;
    private LoaiMonAn loaiMonAn;
    public int maNd;
    private RecyclerView.LayoutManager layoutManager;
    private IPresenterDanhSachMonAn presenterDanhSachMonAn;
    private mHandler mHandler;
    private boolean isLoading = false;
    private boolean limitData = false;
    private boolean sort_a_z = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mon_an);

        toolbar = findViewById(R.id.toolBarDanhSachMonAn);
        btnSortName = findViewById(R.id.btnSapXepTheoTen);
        btnSortTime = findViewById(R.id.btnSapXepTheoThoiGian);
        rvMonAn = findViewById(R.id.rvDanhSachMonAn);
        progressBar = findViewById(R.id.progressBarLoadMore);
        monAnList = new ArrayList<>();
        adapter = new DanhSachMonAnAdapter(this, monAnList);
        mHandler = new mHandler();

        rvMonAn.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rvMonAn.setLayoutManager(layoutManager);
        rvMonAn.setAdapter(adapter);

        Intent intent = getIntent();
        loaiMonAn = (LoaiMonAn) intent.getSerializableExtra("loaimonan");
        maNd = intent.getIntExtra("maNd", 0);

        toolbar.setTitle(loaiMonAn.getTenLoaiMa());
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        presenterDanhSachMonAn = new PresenterDanhSachMonAn(this, this, loaiMonAn.getMaLoaiMa(), progressBar);
        presenterDanhSachMonAn.layDanhSachMonAn();

        btnSortName.setOnClickListener(this);
        btnSortTime.setOnClickListener(this);
        rvMonAn.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                loadMoreData();
            }
        });
    }

    private class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    presenterDanhSachMonAn.layLoadMore();
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private class ThreadData extends Thread {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

    private void loadMoreData() {
        int tongItem = layoutManager.getItemCount();
        int itemLoadMore = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        if (itemLoadMore == (tongItem - 1) && tongItem != 0 && isLoading == false && limitData == false) {
            Log.d("item", String.valueOf(tongItem));
            isLoading = true;
            ThreadData threadData = new ThreadData();
            threadData.start();
        }
    }

    @Override
    public void hienThiDanhSachMonAn(List<MonAn> list, boolean chk) {
        progressBar.setVisibility(View.GONE);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Log.d("monan", list.get(i).getTenMa());
            }
            monAnList.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            limitData = true;
            if (chk) {
                Toast.makeText(this, "Không có món ăn nào!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Đã hết món ăn!", Toast.LENGTH_SHORT).show();
            }
        }
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
