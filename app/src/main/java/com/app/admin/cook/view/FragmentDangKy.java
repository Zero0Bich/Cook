package com.app.admin.cook.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.presenter.PresenterDangKy;

/**
 * Created by Admin on 4/6/2018.
 */

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnFocusChangeListener {
    private View view;
    private EditText edtTen, edtEmail, edtMatKhau;
    private Button btnDangKy;
    private TextInputLayout inputLayoutTen, inputLayoutEmail, inputLayoutMatKhau;
    private String reTen = "[a-zA-Z ]+";
    private String reMatKhau = "[0-9a-zA-Z]{6,30}";
    private boolean checkTen = false;
    private boolean checkEmail = false;
    private boolean checkMat = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        anhXa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKy();
            }
        });

        edtTen.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtMatKhau.setOnFocusChangeListener(this);

        return view;
    }

    private void anhXa() {
        edtTen = view.findViewById(R.id.edtTenHienThi);
        edtEmail = view.findViewById(R.id.edtEmailDangKy);
        edtMatKhau = view.findViewById(R.id.edtMatKhauDangKy);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        inputLayoutTen = view.findViewById(R.id.inputLayoutTen);
        inputLayoutEmail = view.findViewById(R.id.inputLayoutEmail);
        inputLayoutMatKhau = view.findViewById(R.id.inputLayoutMatKhau);
    }

    private void dangKy() {
        String hoTen = edtTen.getText().toString();
        String email = edtEmail.getText().toString();
        String matKhau = edtMatKhau.getText().toString();

        if (checkTen && checkEmail && checkMat) {
            NguoiDung nguoiDung = new NguoiDung(hoTen, email, matKhau);
            PresenterDangKy presenterDangKy = new PresenterDangKy(getActivity(),this, nguoiDung);
            presenterDangKy.thucHienDangKy();
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.edtTenHienThi:
                if (!b) {
                    String chuoi = ((EditText)view).getText().toString().trim();
                    if (!chuoi.matches(reTen)) {
                        inputLayoutTen.setErrorEnabled(true);
                        inputLayoutTen.setError("Bạn cần phải nhập đúng tên!");
                        checkTen = false;
                    } else {
                        inputLayoutTen.setErrorEnabled(false);
                        inputLayoutTen.setError("");
                        checkTen = true;
                    }
                }
                break;
            case R.id.edtEmailDangKy:
                if (!b) {
                    String chuoi = ((EditText)view).getText().toString().trim();
                    if (!Patterns.EMAIL_ADDRESS.matcher(chuoi).matches()) {
                        inputLayoutEmail.setErrorEnabled(true);
                        inputLayoutEmail.setError("Bạn cần phải nhập đúng định dạng email!");
                        checkEmail = false;
                    } else {
                        inputLayoutEmail.setErrorEnabled(false);
                        inputLayoutEmail.setError("");
                        checkEmail = true;
                    }
                }
                break;
            case R.id.edtMatKhauDangKy:
                if (!b) {
                    String chuoi = ((EditText)view).getText().toString().trim();
                    if (!chuoi.matches(reMatKhau)) {
                        inputLayoutMatKhau.setErrorEnabled(true);
                        inputLayoutMatKhau.setError("Bạn cần phải nhập đúng định dạng mật khẩu!");
                        checkMat = false;
                    } else {
                        inputLayoutMatKhau.setErrorEnabled(false);
                        inputLayoutMatKhau.setError("");
                        checkMat = true;
                    }
                }
        }
    }

    @Override
    public void dangKyThanhCong() {
        Toast.makeText(getActivity(),"Đăng ký thành công.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dangKyThatBai() {
        Toast.makeText(getActivity(),"Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
    }
}
