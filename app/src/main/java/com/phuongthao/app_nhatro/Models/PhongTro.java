package com.phuongthao.app_nhatro.Models;

public class PhongTro {
    private String TenPhong;
    private String Image_Phong;
    private String Gia_Phong;
    private String Id_Phong;
    private String Vitri_Phong;
    private String idChuTro;
    private String TrangThai;

    public PhongTro() {
    }

    public PhongTro(String tenPhong, String image_Phong, String gia_Phong, String vitri_Phong, String idChuTro, String trangThai) {
        TenPhong = tenPhong;
        Image_Phong = image_Phong;
        Gia_Phong = gia_Phong;
        Vitri_Phong = vitri_Phong;
        this.idChuTro = idChuTro;
        TrangThai = trangThai;
    }

    public PhongTro(String tenPhong, String image_Phong, String gia_Phong, String id_Phong, String vitri_Phong, String idChuTro, String trangThai) {
        TenPhong = tenPhong;
        Image_Phong = image_Phong;
        Gia_Phong = gia_Phong;
        Id_Phong = id_Phong;
        Vitri_Phong = vitri_Phong;
        this.idChuTro = idChuTro;
        TrangThai = trangThai;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getImage_Phong() {
        return Image_Phong;
    }

    public void setImage_Phong(String image_Phong) {
        Image_Phong = image_Phong;
    }

    public String getGia_Phong() {
        return Gia_Phong;
    }

    public void setGia_Phong(String gia_Phong) {
        Gia_Phong = gia_Phong;
    }

    public String getId_Phong() {
        return Id_Phong;
    }

    public void setId_Phong(String id_Phong) {
        Id_Phong = id_Phong;
    }

    public String getVitri_Phong() {
        return Vitri_Phong;
    }

    public void setVitri_Phong(String vitri_Phong) {
        Vitri_Phong = vitri_Phong;
    }

    public String getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(String idChuTro) {
        this.idChuTro = idChuTro;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
