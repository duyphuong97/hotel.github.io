package com.phuongthao.app_nhatro.Models;

public class DatPhongTro {
    private String HoTenKhachHang;
    private String SoCMNNKhachHang;
    private String HinhKh;
    private Integer SDTKhachHang;
    private String NamSinhKhachHang;
    private String QueQuan;
   private String idKH;

    public DatPhongTro() {
    }

    public DatPhongTro(String hoTenKhachHang, String soCMNNKhachHang, String hinhKh, Integer SDTKhachHang, String namSinhKhachHang, String queQuan, String idKH) {
        HoTenKhachHang = hoTenKhachHang;
        SoCMNNKhachHang = soCMNNKhachHang;
        HinhKh = hinhKh;
        this.SDTKhachHang = SDTKhachHang;
        NamSinhKhachHang = namSinhKhachHang;
        QueQuan = queQuan;
        this.idKH = idKH;
    }

    public String getHoTenKhachHang() {
        return HoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        HoTenKhachHang = hoTenKhachHang;
    }

    public String getSoCMNNKhachHang() {
        return SoCMNNKhachHang;
    }

    public void setSoCMNNKhachHang(String soCMNNKhachHang) {
        SoCMNNKhachHang = soCMNNKhachHang;
    }

    public String getHinhKh() {
        return HinhKh;
    }

    public void setHinhKh(String hinhKh) {
        HinhKh = hinhKh;
    }

    public Integer getSDTKhachHang() {
        return SDTKhachHang;
    }

    public void setSDTKhachHang(Integer SDTKhachHang) {
        this.SDTKhachHang = SDTKhachHang;
    }

    public String getNamSinhKhachHang() {
        return NamSinhKhachHang;
    }

    public void setNamSinhKhachHang(String namSinhKhachHang) {
        NamSinhKhachHang = namSinhKhachHang;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }
}
