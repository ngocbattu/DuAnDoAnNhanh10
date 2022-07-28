package vidu.demo.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vidu.demo.myapplication.FragmnetSP.BanhFragment;
import vidu.demo.myapplication.FragmnetSP.NuocFragment;
import vidu.demo.myapplication.Model.Banh;
import vidu.demo.myapplication.Model.Nuoc;
import vidu.demo.myapplication.R;

public class AdapterNuocSP extends RecyclerView.Adapter<AdapterNuocSP.NuocViewHodel>{
    private Context context;
    private List<Nuoc> list;
    private NuocFragment nuocFragment;

    public AdapterNuocSP(Context context, List<Nuoc> list, NuocFragment nuocFragment) {
        this.context = context;
        this.list = list;
        this.nuocFragment = nuocFragment;
    }

    @NonNull
    @Override
    public NuocViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_nuoc,null);
        return new NuocViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull NuocViewHodel holder, int position) {
        Nuoc nuoc = list.get (position);
        Picasso.get ().load (nuoc.getAnhSP ()).into (holder.imageView);
        holder.txt_tensp.setText (nuoc.getTenSP ());
        holder.layout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                nuocFragment.GetNuocSP (nuoc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class NuocViewHodel extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_tensp;
        LinearLayout layout;
        public NuocViewHodel(@NonNull View itemView) {
            super (itemView);
            imageView = itemView.findViewById (R.id.image_sanpham_nuoc);
            txt_tensp = itemView.findViewById (R.id.txt_ten_sp_nuoc);
            layout = itemView.findViewById (R.id.linear_layout_nuoc);
        }
    }
}
