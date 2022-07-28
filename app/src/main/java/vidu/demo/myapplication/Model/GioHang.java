package vidu.demo.myapplication.Model;

public class GioHang {
    private int id;
    private String anhSP;
    private String tenSP;
    private int giaSP;
    private int soLuong ;
    private int tongTien;

    public GioHang() {
    }

    public GioHang(int id, String anhSP, String tenSP, int giaSP, int soLuong, int tongTien) {
        this.id = id;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
