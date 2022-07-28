package vidu.demo.myapplication.FragmnetSP;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import vidu.demo.myapplication.Activity.ChiTietSPNuoc;
import vidu.demo.myapplication.Adapter.AdapterBanhSP;
import vidu.demo.myapplication.Adapter.AdapterNuocSP;
import vidu.demo.myapplication.Model.Banh;
import vidu.demo.myapplication.Model.Nuoc;
import vidu.demo.myapplication.R;

public class NuocFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdapterNuocSP adapterNuocSP;
    ArrayList<Nuoc> arrayList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public NuocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_nuoc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mRecyclerView = view.findViewById (R.id.recy_nuoc);
        firebaseDatabase = FirebaseDatabase.getInstance ();
        databaseReference = firebaseDatabase.getReference ("Nuoc");

        mRecyclerView.setLayoutManager (new GridLayoutManager (getActivity (), 2));
        arrayList = new ArrayList<> ();
        adapterNuocSP = new AdapterNuocSP (getActivity (), arrayList, this);
        mRecyclerView.setAdapter (adapterNuocSP);

        databaseReference.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Nuoc nuoc = snapshot.getValue (Nuoc.class);
                arrayList.add (nuoc);
                adapterNuocSP.notifyDataSetChanged ();
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

    public void GetNuocSP(Nuoc nuoc) {
        databaseReference.child (String.valueOf (nuoc.getId ())).addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Banh ba = snapshot.getValue (Banh.class);
                Intent intent = new Intent (getActivity (), ChiTietSPNuoc.class);
                intent.putExtra ("id_nuoc", ba.getId ());
                intent.putExtra ("anhSP_nuoc", ba.getAnhSP ());
                intent.putExtra ("tenSP_nuoc", ba.getTenSP ());
                intent.putExtra ("giaSP_nuoc", ba.getGia ());
                intent.putExtra ("moTa_nuoc", ba.getChiTietSP ());
                startActivity (intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}