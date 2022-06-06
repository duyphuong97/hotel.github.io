package com.phuongthao.app_nhatro.Models;

public class PhongTro1 {
    private String GiaPhong;
    private String GioiThieuDienTich;
    private String GioiThieuPhong;
    private String ImageUrl;
    private String SDTPhong;
    private String TenPhong;
    private String VitriPhong;
    private String idChuTro;
    private String id;
    private String TrangThai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PhongTro1() {
    }

    public PhongTro1(String giaPhong, String gioiThieuDienTich, String gioiThieuPhong, String imageUrl,
                     String SDTPhong, String tenPhong, String vitriPhong, String idChuTro, String id, String trangThai) {
        GiaPhong = giaPhong;
        GioiThieuDienTich = gioiThieuDienTich;
        GioiThieuPhong = gioiThieuPhong;
        ImageUrl = imageUrl;
        this.SDTPhong = SDTPhong;
        TenPhong = tenPhong;
        VitriPhong = vitriPhong;
        this.idChuTro = idChuTro;
        this.id = id;
        TrangThai = trangThai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(String giaPhong) {
        GiaPhong = giaPhong;
    }

    public String getGioiThieuDienTich() {
        return GioiThieuDienTich;
    }

    public void setGioiThieuDienTich(String gioiThieuDienTich) {
        GioiThieuDienTich = gioiThieuDienTich;
    }

    public String getGioiThieuPhong() {
        return GioiThieuPhong;
    }

    public void setGioiThieuPhong(String gioiThieuPhong) {
        GioiThieuPhong = gioiThieuPhong;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getSDTPhong() {
        return SDTPhong;
    }

    public void setSDTPhong(String SDTPhong) {
        this.SDTPhong = SDTPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getVitriPhong() {
        return VitriPhong;
    }

    public void setVitriPhong(String vitriPhong) {
        VitriPhong = vitriPhong;
    }

    public String getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(String idChuTro) {
        this.idChuTro = idChuTro;
    }
}
