package com.app.admin.cook.view;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.app.admin.cook.R;
import com.app.admin.cook.adapter.ExpandMenuAdapter;
import com.app.admin.cook.adapter.MonAnMoiNhatAdapter;
import com.app.admin.cook.model.ModelDangNhap;
import com.app.admin.cook.object.LoaiMonAn;
import com.app.admin.cook.object.MonAn;
import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.presenter.PresenterExpandMenu;
import com.app.admin.cook.presenter.PresenterMonAnMoiNhat;
import com.app.admin.cook.presenter.PresenterMonAnYeuThich;
import com.app.admin.cook.ulti.CheckInternet;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements ViewExpandMenu, ViewMonAn, AppBarLayout.OnOffsetChangedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ExpandableListView expLvMenu;
    private CircleImageView imgViewNguoiDung;
    private TextView txtTenNguoiDung, txtEmailNguoiDung;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout linearLayout;
    private Button btnSearch;
    private ViewFlipper viewFlipper;
    private RecyclerView rvMonAnMoiNhat;
    private List<MonAn> monAnList;
    private MonAnMoiNhatAdapter adapter;
    private ModelDangNhap modelDangNhap;
    private Menu menu;
    private MenuItem menuItemDangNhap, menuItemDangXuat, menuItemSearch;
    private boolean checkTaiKhoan = false;
    private NguoiDung nguoiDung;
    public static int maNd = 0;
    public static List<MonAn> listMonAnYeuThich;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        addImageViewFlipper();

        modelDangNhap = new ModelDangNhap(this);

        if (CheckInternet.haveNetworkConnection(getApplicationContext())) {
            PresenterMonAnMoiNhat presenterMonAnMoiNhat = new PresenterMonAnMoiNhat(this, this);
            presenterMonAnMoiNhat.layDuLieu();

            PresenterExpandMenu presenterExpandMenu = new PresenterExpandMenu(this, this);
            presenterExpandMenu.yeuCauDuLieu();
        } else {
            CheckInternet.showDialog(this);
        }

        appBarLayout.addOnOffsetChangedListener(this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible) {
                    Intent iSearch = new Intent(MainActivity.this, TimKiemMonAnActivity.class);
                    startActivity(iSearch);
                }
            }
        });
    }

    private void anhXa() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navViewMenu);
        expLvMenu = findViewById(R.id.expLvMenu);
        imgViewNguoiDung = findViewById(R.id.img_nav_nguoidung);
        txtTenNguoiDung = findViewById(R.id.txtNavTenNguoiDung);
        txtEmailNguoiDung = findViewById(R.id.txtNavEmailNguoiDung);
        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        linearLayout = findViewById(R.id.layoutSearch);
        btnSearch = findViewById(R.id.btnSearch);
        viewFlipper = findViewById(R.id.viewFliper);
        rvMonAnMoiNhat = findViewById(R.id.rvMonAnMoiNhat);
        listMonAnYeuThich = new ArrayList<>();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        monAnList = new ArrayList<>();

    }

    private void addImageViewFlipper() {
        int[] arr = {R.drawable.image_view_flipper_1, R.drawable.image_view_flipper_2, R.drawable.image_view_flipper_3,
                    R.drawable.image_view_flipper_4, R.drawable.image_view_flipper_5, R.drawable.image_view_flipper_6,
                    R.drawable.image_view_flipper_7, R.drawable.image_view_flipper_8};

        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(getApplicationContext()).load(arr[i]).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(this, R.anim.anim_view_flipper_in);
        Animation animation_out = AnimationUtils.loadAnimation(this, R.anim.anim_view_flipper_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;

        menuItemDangNhap = menu.findItem(R.id.itemDangNhap);
        menuItemDangXuat = menu.findItem(R.id.itemDangXuat);
        menuItemSearch = menu.findItem(R.id.item_search);

        nguoiDung = modelDangNhap.layCachedDangNhap();

        if (nguoiDung.getMaNd() != 0) {
            menuItemDangNhap.setTitle(nguoiDung.getTenNd());
            txtTenNguoiDung.setText(nguoiDung.getTenNd());
            txtEmailNguoiDung.setText(nguoiDung.getEmail());
            menuItemDangXuat.setVisible(true);
            checkTaiKhoan = true;
            maNd = nguoiDung.getMaNd();

//            lấy danh sách món ăn yêu thích
            PresenterMonAnYeuThich presenterMonAnYeuThich = new PresenterMonAnYeuThich(this, this, nguoiDung.getMaNd());
            presenterMonAnYeuThich.layDuLieuMonAn();
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                Intent iSearch = new Intent(this, TimKiemMonAnActivity.class);
                startActivity(iSearch);
                break;
            case R.id.itemDangNhap:
                if (!checkTaiKhoan) {
                    Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                }
                break;
            case R.id.itemMonAnYeuThich:
                if (checkTaiKhoan) {
                    Intent intent = new Intent(this, MonAnYeuThichActivity.class);
                    intent.putExtra("nguoidung", nguoiDung);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Bạn chưa đăng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.itemDangXuat:
                modelDangNhap.xoaCachedDangNhap();
                menuItemDangNhap.setTitle("Đăng nhập");
                menuItemDangXuat.setVisible(false);
                checkTaiKhoan = false;
                maNd = 0;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hienThiExpandMenu(List<LoaiMonAn> loaiMonAns) {
        ExpandMenuAdapter adapter = new ExpandMenuAdapter(this, loaiMonAns);
        expLvMenu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hienThiMaMoiNhat(List<MonAn> list) {
        adapter = new MonAnMoiNhatAdapter(this, list);
        rvMonAnMoiNhat.setHasFixedSize(true);
        rvMonAnMoiNhat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMonAnMoiNhat.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hienThiMaYeuThich(List<MonAn> list) {
        nguoiDung.setMonAns(list);
        listMonAnYeuThich.clear();
        listMonAnYeuThich.addAll(list);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (collapsingToolbarLayout.getHeight() + verticalOffset <= 1.3 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            linearLayout.animate().alpha(0).setDuration(200);
            menuItemSearch.setVisible(true);
            isVisible = false;
        } else {
            linearLayout.animate().alpha(1).setDuration(200);
            try {
                menuItemSearch.setVisible(false);
            } catch (Exception e) {

            }
            isVisible = true;
        }
    }
}
