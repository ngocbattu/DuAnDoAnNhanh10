package vidu.demo.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import vidu.demo.myapplication.Model.GioHang;
import vidu.demo.myapplication.R;

public class ChiTietSPHome extends AppCompatActivity {

    ImageView img_ct_sp;
    TextView txt_ten_sp,txt_gia_sp,txt_mota;
    Toolbar toolbar;
    Button btn_xac_nhan;
    EditText edt_so_luong;
    String AnhSP;
    String TenSP;
    int GiaSP;
    String moTaSP;
    int id;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_chi_tiet_sphome);
        Init ();
        setSupportActionBar (toolbar);
        getSupportActionBar ().setTitle ("Quay lại");
        getSupportActionBar ().setHomeAsUpIndicator (R.drawable.ic_back);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        Intent intent = getIntent ();
        id = intent.getIntExtra ("id_home",0);
        AnhSP = intent.getStringExtra ("anhSP_home");
        TenSP = intent.getStringExtra ("tenSP_home");
        GiaSP = intent.getIntExtra ("giaSP_home",0);
        moTaSP = intent.getStringExtra ("moTa_home");

        Log.d ("TAG", "onCreate: " + AnhSP);
        Log.d ("TAG", "onCreate: " + TenSP);
        Picasso.get ().load (AnhSP).into (img_ct_sp);
        txt_ten_sp.setText (TenSP);
        txt_gia_sp.setText ("Giá : " + GiaSP + "$");
        txt_mota.setText (moTaSP);
        InserSoluong (id); // truyền id sản phầm vào hàm
    }
    public void Init(){
        img_ct_sp = findViewById (R.id.img_anh_ctsp_home);
        txt_ten_sp = findViewById (R.id.txt_ct_tensp_home);
        txt_gia_sp =  findViewById (R.id.txt_ct_gia_sp_home);
        txt_mota = findViewById (R.id.txt_mota_sp_home);
        toolbar = findViewById (R.id.toolbar_ctsp_home);
        btn_xac_nhan = findViewById (R.id.btn_xac_nhan_home);
        edt_so_luong = findViewById (R.id.edt_so_luong_sp_home);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed ();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void InserSoluong(int idsp){
        btn_xac_nhan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance ();
                DatabaseReference databaseReference = firebaseDatabase.getReference ("GioHang/"+idsp);

                databaseReference.child (String.valueOf (idsp)).addValueEventListener (new ValueEventListener () {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(edt_so_luong.getText ().toString ().equals ("")){
                            Toast.makeText (ChiTietSPHome.this, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show ();
                            return;
                        }
                        int SL = Integer.parseInt (edt_so_luong.getText ().toString ());
                        int tongTien = GiaSP * SL;
                        GioHang gioHang = new GioHang (idsp,AnhSP,TenSP,GiaSP,SL,tongTien);
                        databaseReference.setValue (gioHang);
                        finish ();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}