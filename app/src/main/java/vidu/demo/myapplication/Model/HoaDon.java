package vidu.demo.myapplication.Model;

import java.util.HashMap;
import java.util.Map;

public class HoaDon {

    private int id;
    private String tenKH;
    private String phone ;
    private String diaChi ;
    private int tongTien;

    public HoaDon() {
    }

    public HoaDon(int id, String tenKH, String phone, String diaChi, int tongTien) {
        this.id = id;
        this.tenKH = tenKH;
        this.phone = phone;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
    }

    public HoaDon(String tenKH, String phone, String diaChi) {
        this.tenKH = tenKH;
        this.phone = phone;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Map<String , Object> toMap(){
        Map<String , Object> update = new HashMap<> ();
        update.put ("tenKH",tenKH);
        update.put ("phone",phone);
        update.put ("diaChi",diaChi);
        return update;
    }
}
