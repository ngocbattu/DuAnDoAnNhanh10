package vidu.demo.myapplication.Fragmnet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vidu.demo.myapplication.Activity.ChiTietSPActivity;
import vidu.demo.myapplication.Activity.ChiTietSPHome;
import vidu.demo.myapplication.Adapter.AdapterSanPhamHome;
import vidu.demo.myapplication.Model.SanPham;
import vidu.demo.myapplication.R;


public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdapterSanPhamHome adapterSanPhamHome;
    ArrayList<SanPham> arrayList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mRecyclerView = view.findViewById (R.id.recy_home);
        firebaseDatabase = FirebaseDatabase.getInstance ();
        databaseReference = firebaseDatabase.getReference ("SanPham");
        mRecyclerView.setLayoutManager (new GridLayoutManager (getActivity (),2));
        arrayList = new ArrayList<> ();
        adapterSanPhamHome = new AdapterSanPhamHome (getActivity (),arrayList,this);
        mRecyclerView.setAdapter (adapterSanPhamHome);
        GetSP ();
    }
    public void GetSP(){
        databaseReference.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham = snapshot.getValue (SanPham.class);
                if(sanPham != null){
                    arrayList.add (sanPham);
                }
                adapterSanPhamHome.notifyDataSetChanged ();
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
    public void getSP(SanPham sp){

        databaseReference.child (String.valueOf (sp.getId ())).addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SanPham sanPham = snapshot.getValue (SanPham.class);
                Intent intent = new Intent (getActivity (),ChiTietSPHome.class);
                intent.putExtra ("id_home",sanPham.getId ());
                intent.putExtra ("anhSP_home",sanPham.getAnhSP ());
                intent.putExtra ("tenSP_home",sanPham.getTenSP ());
                intent.putExtra ("giaSP_home",sanPham.getGia ());
                intent.putExtra ("moTa_home" ,sanPham.getChiTietSP ());
                startActivity (intent);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}