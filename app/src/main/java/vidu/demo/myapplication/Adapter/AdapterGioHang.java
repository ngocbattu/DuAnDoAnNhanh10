package vidu.demo.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vidu.demo.myapplication.FragmentGioHang.HangDaChonFragment;
import vidu.demo.myapplication.Model.GioHang;
import vidu.demo.myapplication.R;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHodelGioHang>{

    private Context context;
    private List<GioHang> list;
    private HangDaChonFragment hangDaChonFragment;

    public AdapterGioHang(Context context, List<GioHang> list, HangDaChonFragment hangDaChonFragment) {
        this.context = context;
        this.list = list;
        this.hangDaChonFragment = hangDaChonFragment;
    }

    @NonNull
    @Override
    public ViewHodelGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_gio_hang,null);
        return new ViewHodelGioHang (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelGioHang holder, int position) {
        GioHang gioHang = list.get (position);
        Picasso.get ().load (gioHang.getAnhSP ()).into (holder.imageView);
        holder.txt_tensp.setText (gioHang.getTenSP ());
        holder.txt_gia_sp.setText ("Giá : " +gioHang.getGiaSP () + "$");
        holder.txt_so_luong.setText ("Số Lượng : " + gioHang.getSoLuong () + "");
        holder.btn_xoa_sp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                hangDaChonFragment.DeleteSP(gioHang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class ViewHodelGioHang extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txt_tensp,txt_gia_sp,txt_so_luong;
        ImageView btn_xoa_sp;
        public ViewHodelGioHang(@NonNull View itemView) {
            super (itemView);
            imageView = itemView.findViewById (R.id.image_sanpham_gio_hang);
            txt_tensp = itemView.findViewById (R.id.txt_ten_san_pham_gio_hang);
            txt_gia_sp = itemView.findViewById (R.id.txt_gia_sp_gio_hang);
            txt_so_luong = itemView.findViewById (R.id.txt_so_luong_gio_hang);
            btn_xoa_sp = itemView.findViewById (R.id.btn_huy_sp_gio_hang);
        }
    }
}
