package vidu.demo.myapplication.FragmentGioHang;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

import vidu.demo.myapplication.Adapter.AdapterHoaDon;
import vidu.demo.myapplication.Model.HoaDon;
import vidu.demo.myapplication.R;


public class HoaDonFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<HoaDon> arrayList;
    AdapterHoaDon adapterHoaDon;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edt_tenkh_update , edt_phone_update , edt_dia_chi_update;
    String ten , phone , dia_chi;
    int id;

    public HoaDonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_hoa_don, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mRecyclerView = view.findViewById (R.id.recy_hoa_don);
        mRecyclerView.hasFixedSize ();
        mRecyclerView.setLayoutManager (new LinearLayoutManager (getActivity (),RecyclerView.VERTICAL,false));
        arrayList = new ArrayList<> ();
        adapterHoaDon = new AdapterHoaDon (getActivity (),arrayList,this);
        mRecyclerView.setAdapter (adapterHoaDon);
        GetHoaDon ();

    }

    public void GetHoaDon(){
        firebaseDatabase = FirebaseDatabase.getInstance ();
        databaseReference = firebaseDatabase.getReference ("HoaDon");

        databaseReference.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                HoaDon hoaDon = snapshot.getValue (HoaDon.class);
                id = hoaDon.getId ();
                ten = hoaDon.getTenKH ();
                phone = hoaDon.getPhone ();
                dia_chi = hoaDon.getDiaChi ();
                arrayList.add (hoaDon);
                adapterHoaDon.notifyDataSetChanged ();
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
    public void DeleteHD(HoaDon hoaDon){
        firebaseDatabase = FirebaseDatabase.getInstance ();
        databaseReference = firebaseDatabase.getReference ("HoaDon");

        databaseReference.child (String.valueOf (hoaDon.getId ())).removeValue (new DatabaseReference.CompletionListener () {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                arrayList.remove (hoaDon);
                adapterHoaDon.notifyDataSetChanged ();
                Toast.makeText (getActivity (), "Delete thành công", Toast.LENGTH_SHORT).show ();
            }
        });
    }
    public void UpdateHD(int postition){
        Dialog dialog = new Dialog (getActivity ());
        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dia_log_update_hoa_don);

        edt_tenkh_update = (EditText) dialog.findViewById (R.id.edt_update_ten_kh);
        edt_phone_update = (EditText) dialog.findViewById (R.id.edt_update_phone_kh);
        edt_dia_chi_update = (EditText) dialog.findViewById (R.id.edt_update_dia_chi_kh);
        Button btn_update = (Button) dialog.findViewById (R.id.btn_update_hd);
        Button btn_huy = (Button) dialog.findViewById (R.id.btn_update_huy);

        edt_tenkh_update.setText (ten);
        edt_phone_update.setText (phone);
        edt_dia_chi_update.setText (dia_chi);

        btn_huy.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss ();
            }
        });
        btn_update.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance ();
                databaseReference = firebaseDatabase.getReference ("HoaDon");

                if(edt_tenkh_update.getText ().toString ().equals ("")){
                    Toast.makeText (getActivity (), "Vui lòng nhập tên khách hàng  ", Toast.LENGTH_SHORT).show ();
                    return;
                }else if(edt_phone_update.getText ().toString ().equals ("")){
                    Toast.makeText (getActivity (), "Vui long nhập phone ", Toast.LENGTH_SHORT).show ();
                    return;
                }else if(edt_dia_chi_update.getText ().toString ().equals ("")){
                    Toast.makeText (getActivity (), "Vui lòng nhập dia chỉ", Toast.LENGTH_SHORT).show ();
                    return;
                }else {
                    String ten = edt_tenkh_update.getText ().toString ();
                    String phone = edt_phone_update.getText ().toString ();
                    String diaChi =  edt_dia_chi_update.getText ().toString ();
                    HoaDon hoaDon = new HoaDon (ten,phone,diaChi);
                    databaseReference.child (String.valueOf (id)).updateChildren (hoaDon.toMap (), new DatabaseReference.CompletionListener () {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                            arrayList.clear ();
                            hoaDon.setId (id);
                            arrayList.set (postition,hoaDon );
                            adapterHoaDon.notifyDataSetChanged ();
                             Toast.makeText (getActivity (), "Update thanh công ", Toast.LENGTH_SHORT).show ();
                            dialog.dismiss ();
                        }
                    });
                }
            }
        });
        dialog.show ();
    }
}