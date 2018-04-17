package com.app.admin.cook.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.adapter.DanhSachMonAnAdapter;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.presenter.IPresenterTimKiemMonAn;
import com.app.admin.cook.presenter.PresenterTimKiemMonAn;

import java.util.ArrayList;
import java.util.List;

public class TimKiemMonAnActivity extends AppCompatActivity implements ViewDanhSachMonAn, SearchView.OnQueryTextListener{
    private MenuItem itemSearch;
    private Toolbar toolbar;
    private RecyclerView rvMonAn;
    private ProgressBar progressBar;
    private DanhSachMonAnAdapter adapter;
    private List<MonAn> monAnList;
    private boolean limitData = false;
    private mHandler mHandler;
    private boolean isLoading = false;
    private RecyclerView.LayoutManager layoutManager;
    private IPresenterTimKiemMonAn presenterTimKiemMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_mon_an);

        toolbar = findViewById(R.id.toolBarTimKiemMa);
        rvMonAn = findViewById(R.id.rvTimKiemMonAn);
        progressBar = findViewById(R.id.progressBarLoadMoreTimKiem);
        monAnList = new ArrayList<>();
        adapter = new DanhSachMonAnAdapter(this, monAnList);
        mHandler = new mHandler();

        rvMonAn.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rvMonAn.setLayoutManager(layoutManager);
        rvMonAn.setAdapter(adapter);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rvMonAn.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Toast.makeText(TimKiemMonAnActivity.this, "aa", Toast.LENGTH_SHORT).show();
                loadMoreData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tim_kiem, menu);

        itemSearch = menu.findItem(R.id.item_search_tim_kiem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                Toast.makeText(this, "Không tìm thấy món ăn nào!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Đã hết món ăn!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    presenterTimKiemMonAn.layLoadMore();
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
            isLoading = true;
            ThreadData threadData = new ThreadData();
            threadData.start();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterTimKiemMonAn = new PresenterTimKiemMonAn(this, this, query, progressBar);
        presenterTimKiemMonAn.layDanhSachMonAn();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
