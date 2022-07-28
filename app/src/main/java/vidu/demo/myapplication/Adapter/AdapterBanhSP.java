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
import vidu.demo.myapplication.Model.Banh;
import vidu.demo.myapplication.R;

public class AdapterBanhSP extends RecyclerView.Adapter<AdapterBanhSP.BanhViewHodel>{

    private Context context;
    private List<Banh> list;
    private BanhFragment banhFragment;

    public AdapterBanhSP(Context context, List<Banh> list, BanhFragment banhFragment) {
        this.context = context;
        this.list = list;
        this.banhFragment = banhFragment;
    }

    @NonNull
    @Override
    public BanhViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_banh,null);
        return new BanhViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanhViewHodel holder, int position) {
        Banh banh = list.get (position);
        Picasso.get ().load (banh.getAnhSP ()).into (holder.imageView);
        holder.txt_tensp.setText (banh.getTenSP ());
        Log.d ("TAG", "onBindViewHolder: " + banh.getTenSP ());
        holder.layout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                banhFragment.GetSPBanh (banh);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class BanhViewHodel extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_tensp;
        LinearLayout layout;
        public BanhViewHodel(@NonNull View itemView) {
            super (itemView);
            imageView = itemView.findViewById (R.id.image_sanpham_banh);
            txt_tensp = itemView.findViewById (R.id.txt_ten_sp_banh);
            layout = itemView.findViewById (R.id.linear_layout_banh);
        }
    }
}
