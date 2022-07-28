package vidu.demo.myapplication.Fragmnet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import vidu.demo.myapplication.Activity.DangNhapActivity;
import vidu.demo.myapplication.R;

//27/6/2022
public class CaNhanFragment extends Fragment {

    ImageView imageView;
    TextView txt_ten_kh ,txt_ngay_sinh_kh,txt_phone , txt_dia_chi;
    Button btn_dang_xuat,btn_them_thong_tin , btn_update_thong_tin;

    public CaNhanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_ca_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        imageView = view.findViewById (R.id.image_ca_nhan);
        txt_ten_kh = view.findViewById (R.id.txt_tenkhachhang);
        txt_ngay_sinh_kh =  view.findViewById (R.id.txt_ngaysinhkhachhang);
        txt_phone =  view.findViewById (R.id.txt_sodienthoaikhachhang);
        txt_dia_chi = view.findViewById (R.id.txt_diachikh);
        btn_dang_xuat = view.findViewById (R.id.btn_dang_xuat);
        btn_them_thong_tin = view.findViewById (R.id.btn_them_thong_tin_kh);
        btn_update_thong_tin = view.findViewById (R.id.btn_sua_thong_tin_kh);

        btn_dang_xuat.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getActivity (), DangNhapActivity.class));
            }
        });
        btn_them_thong_tin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

            }
        });
    }
}