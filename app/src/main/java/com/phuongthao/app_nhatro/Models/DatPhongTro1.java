package com.phuongthao.app_nhatro.Models;

public class DatPhongTro1 {
    private String ImageUrl, Ten_KH, SDT_KH, Quequan_KH, NamSinh_KH, CMNN_KH, idKhachHang, idPhong, TenPhong, idChuTro;

    public DatPhongTro1() {
    }

    public DatPhongTro1(String imageUrl, String ten_KH, String SDT_KH, String quequan_KH, String namSinh_KH, String CMNN_KH, String idKhachHang, String idPhong, String tenPhong, String idChuTro) {
        ImageUrl = imageUrl;
        Ten_KH = ten_KH;
        this.SDT_KH = SDT_KH;
        Quequan_KH = quequan_KH;
        NamSinh_KH = namSinh_KH;
        this.CMNN_KH = CMNN_KH;
        this.idKhachHang = idKhachHang;
        this.idPhong = idPhong;
        TenPhong = tenPhong;
        this.idChuTro = idChuTro;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTen_KH() {
        return Ten_KH;
    }

    public void setTen_KH(String ten_KH) {
        Ten_KH = ten_KH;
    }

    public String getSDT_KH() {
        return SDT_KH;
    }

    public void setSDT_KH(String SDT_KH) {
        this.SDT_KH = SDT_KH;
    }

    public String getQuequan_KH() {
        return Quequan_KH;
    }

    public void setQuequan_KH(String quequan_KH) {
        Quequan_KH = quequan_KH;
    }

    public String getNamSinh_KH() {
        return NamSinh_KH;
    }

    public void setNamSinh_KH(String namSinh_KH) {
        NamSinh_KH = namSinh_KH;
    }

    public String getCMNN_KH() {
        return CMNN_KH;
    }

    public void setCMNN_KH(String CMNN_KH) {
        this.CMNN_KH = CMNN_KH;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(String idChuTro) {
        this.idChuTro = idChuTro;
    }
}