package vidu.demo.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vidu.demo.myapplication.FragmentGioHang.HoaDonFragment;
import vidu.demo.myapplication.Model.HoaDon;
import vidu.demo.myapplication.R;

public class AdapterHoaDon extends RecyclerView.Adapter<AdapterHoaDon.HoaDonVideoHodel>{

    private Context context;
    private List<HoaDon> list;
    private HoaDonFragment hoaDonFragment;

    public AdapterHoaDon(Context context, List<HoaDon> list, HoaDonFragment hoaDonFragment) {
        this.context = context;
        this.list = list;
        this.hoaDonFragment = hoaDonFragment;
    }

    @NonNull
    @Override
    public HoaDonVideoHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_hoa_don,null);
        return new HoaDonVideoHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonVideoHodel holder, int position) {
        HoaDon hoaDon = list.get (position);
        holder.txt_name.setText (hoaDon.getTenKH ());
        holder.txt_phone.setText (hoaDon.getPhone ());
        holder.txt_dia_chi.setText (hoaDon.getDiaChi ());
        holder.txt_tong_tien.setText (hoaDon.getTongTien ()+"$");
        holder.image_delete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                hoaDonFragment.DeleteHD (hoaDon);
            }
        });
        holder.image_update.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                hoaDonFragment.UpdateHD (position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class HoaDonVideoHodel extends RecyclerView.ViewHolder{
        TextView txt_name,txt_phone , txt_dia_chi ,txt_tong_tien;
        ImageView image_delete , image_update;
        public HoaDonVideoHodel(@NonNull View itemView) {
            super (itemView);
            txt_name = itemView.findViewById (R.id.txt_ten_kh_hd);
            txt_phone = itemView.findViewById (R.id.txt_phone_kh_hd);
            txt_dia_chi = itemView.findViewById (R.id.txt_diaChi_kh_hd);
            txt_tong_tien = itemView.findViewById (R.id.txt_tongTien_kh_hd);
            image_delete = itemView.findViewById (R.id.image_xoa_hoa_don);
            image_update = itemView.findViewById (R.id.image_update_hd);
        }
    }
}
