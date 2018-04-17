package com.app.admin.cook.view;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.app.admin.cook.R;
import com.app.admin.cook.object.NguoiDung;
import com.app.admin.cook.presenter.PresenterDangNhap;

/**
 * Created by Admin on 4/6/2018.
 */

public class FragmentDangNhap extends Fragment implements ViewDangNhap, View.OnFocusChangeListener {
    private View view;
    private EditText edtEmail, edtMatKhau;
    private Button btnDangNhap;
    private TextView txtQuenMatKhau;
    private TextInputLayout inputLayoutEmail, inputLayoutMatKhau;
    private String reMatKhau = "[0-9a-zA-Z]{6,30}";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);

        anhXa();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });

        return view;
    }

    private void anhXa() {
        edtEmail = view.findViewById(R.id.edtEmailDangNhap);
        edtMatKhau = view.findViewById(R.id.edtMatKhauDangNhap);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        txtQuenMatKhau = view.findViewById(R.id.txtQuenMatKhau);
        inputLayoutEmail = view.findViewById(R.id.inputEmailDn);
        inputLayoutMatKhau = view.findViewById(R.id.inputMatKhauDn);
    }

    private void dangNhap() {
        String email = edtEmail.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();

        if (email.equals("") || matKhau.equals("")) {
            Toast.makeText(getActivity(),"Bạn chưa nhập email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            PresenterDangNhap presenterDangNhap = new PresenterDangNhap(getActivity(), this);
            presenterDangNhap.xuLyDangNhap(email, matKhau);
        }
    }

    @Override
    public void dangNhapThanhCong() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void dangNhapThatBai() {
        Toast.makeText(getActivity(),"Email hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.edtEmailDangNhap:
                if (!b) {
                    String email = edtEmail.getText().toString().trim();
                    if (email.equals("")) {
                        inputLayoutEmail.setErrorEnabled(true);
                        inputLayoutEmail.setError("Bạn phải nhập email!");
                    } else {
                        inputLayoutEmail.setErrorEnabled(false);
                        inputLayoutEmail.setError("");
                    }
                }
                break;
            case R.id.edtMatKhauDangNhap:
                if (!b) {
                    String matKhau = edtMatKhau.getText().toString().trim();
                    if (matKhau.equals("")) {
                        inputLayoutEmail.setErrorEnabled(true);
                        inputLayoutEmail.setError("Bạn phải nhập mật khẩu!");
                    } else {
                        inputLayoutEmail.setErrorEnabled(false);
                        inputLayoutEmail.setError("");
                    }
                }
                break;
        }
    }
}
