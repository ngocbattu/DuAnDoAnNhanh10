package vidu.demo.myapplication.FragmentGioHang;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vidu.demo.myapplication.Adapter.AdapterGioHang;
import vidu.demo.myapplication.Model.GioHang;
import vidu.demo.myapplication.Model.HoaDon;
import vidu.demo.myapplication.R;


public class HangDaChonFragment extends Fragment {

    RecyclerView  mRecyclerView;
    AdapterGioHang adapterGioHang;
    ArrayList<GioHang> arrayList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView txt_tong_tien;
    Button btn_thanh_toan;
    int sum = 0 ;
    String tensppp;
    GioHang gioHang;
    int idHDCT = 1;
    String anhSP;
    int id_gh;
    int SL ;


    public HangDaChonFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate (R.layout.fragmnet_hang_chon, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        txt_tong_tien = view.findViewById (R.id.txt_tong_tien);
        mRecyclerView = view.findViewById (R.id.recy_view_hang_da_chon);
        btn_thanh_toan = view.findViewById (R.id.btn_thanh_toan);
        mRecyclerView.setLayoutManager (new LinearLayoutManager (getActivity (),LinearLayoutManager.VERTICAL,false));
        arrayList = new ArrayList<> ();
        adapterGioHang = new AdapterGioHang (getActivity (),arrayList,this);
        mRecyclerView.setAdapter (adapterGioHang);
        GetSPGH ();
//        GetGioHang ();
//        Log.d ("TAG", "onViewCreated: " + id_gh);
       ThanhToan ();
//       AddHoaDonCT ();
    }
    public void GetSPGH(){
        firebaseDatabase = FirebaseDatabase.getInstance ();
        databaseReference = firebaseDatabase.getReference ("GioHang");

        databaseReference.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                GioHang gioHang = snapshot.getValue (GioHang.class);
                id_gh = gioHang.getId ();
                tensppp = gioHang.getTenSP ();
                if(arrayList !=null){
                    int TongTien = gioHang.getTongTien ();
                    sum += TongTien;
                    txt_tong_tien.setText (sum+"$");
                    arrayList.add (gioHang);
                    adapterGioHang.notifyDataSetChanged ();
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void DeleteSP(GioHang gioHang){
        databaseReference.child (String.valueOf (gioHang.getId ())).removeValue (new DatabaseReference.CompletionListener () {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText (getActivity (), "Delete thành công", Toast.LENGTH_SHORT).show ();
                arrayList.remove (gioHang);
                txt_tong_tien.setText (" 0 $");
                adapterGioHang.notifyDataSetChanged ();
            }
        });
    }
    public void ThanhToan(){
        btn_thanh_toan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog (getActivity ());
                dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
                dialog.setContentView (R.layout.dia_log_them_hoa_don);

                EditText edt_name_kh = (EditText) dialog.findViewById (R.id.edt_ten_kh);
                EditText edt_phone_kh =  (EditText) dialog.findViewById (R.id.edt_phone_kh);
                EditText edt_dia_chi_kh = (EditText) dialog.findViewById (R.id.edt_dia_chi_kh);
                Button btn_dong_y = (Button) dialog.findViewById (R.id.btn_dong_y_thanh_toan);
                Button btn_huy = (Button) dialog.findViewById (R.id.btn_huy);
                btn_dong_y.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        firebaseDatabase = FirebaseDatabase.getInstance ();
                        databaseReference = firebaseDatabase.getReference ("HoaDon/" + id_gh);
                        databaseReference.child (String.valueOf (id_gh)).addValueEventListener (new ValueEventListener () {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(edt_name_kh.getText ().toString ().equals ("")){
                                    Toast.makeText (getActivity (), "Vui long nhap ten", Toast.LENGTH_SHORT).show ();
                                    return;
                                }else if(edt_phone_kh.getText ().toString ().equals ("")){
                                    Toast.makeText (getActivity (), "Vui long nhap so dien thoai", Toast.LENGTH_SHORT).show ();
                                    return;
                                }else if(edt_dia_chi_kh.getText ().toString ().equals ("")){
                                    Toast.makeText (getActivity (), "Vui long nhap dia chi", Toast.LENGTH_SHORT).show ();
                                    return;
                                }

                                    String tenkh = edt_name_kh.getText ().toString ();
                                    String phone = edt_phone_kh.getText ().toString ();
                                    String diaChi = edt_dia_chi_kh.getText ().toString ();
                                    HoaDon  hoaDon = new HoaDon (id_gh,tenkh,phone,diaChi,sum);
                                    id_gh +=1;
                                    Log.d ("TAG", "onDataChange: " + id_gh);
                                    Toast.makeText (getActivity (), "Thanh toán thành công", Toast.LENGTH_SHORT).show ();
                                    databaseReference.setValue (hoaDon);
                                    DeleteFull ();
                                    dialog.dismiss ();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

                btn_huy.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss ();
                    }
                });
                dialog.show ();
            }
        });

    }

    public void DeleteFull(){
        firebaseDatabase = FirebaseDatabase.getInstance ();
        databaseReference = firebaseDatabase.getReference ("GioHang");

        databaseReference.removeValue (new DatabaseReference.CompletionListener () {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                arrayList.clear ();
                txt_tong_tien.setText ("");
                adapterGioHang.notifyDataSetChanged ();
            }
        });
    }
//    public void AddHoaDonCT(){
//        firebaseDatabase = FirebaseDatabase.getInstance ();
//        databaseReference = firebaseDatabase.getReference ("HoaDonChiTiet/" + idHDCT);
//
//        databaseReference.child (String.valueOf (idHDCT)).addValueEventListener (new ValueEventListener () {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.setValue (arrayList);
//                idHDCT +=1;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }



}