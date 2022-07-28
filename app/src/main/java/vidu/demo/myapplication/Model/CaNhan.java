package vidu.demo.myapplication.Model;

public class CaNhan {
    private int id;
    private String tenCaNhan;
    private String ngaySinh;
    private String phone ;
    private String diaChi;

    public CaNhan() {
    }

    public CaNhan(int id, String tenCaNhan, String ngaySinh, String phone, String diaChi) {
        this.id = id;
        this.tenCaNhan = tenCaNhan;
        this.ngaySinh = ngaySinh;
        this.phone = phone;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCaNhan() {
        return tenCaNhan;
    }

    public void setTenCaNhan(String tenCaNhan) {
        this.tenCaNhan = tenCaNhan;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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
}
