package com.phuongthao.app_nhatro.Models;

public class PhongTro {
    private String TenPhong;
    private int Image_Phong;
    private int Gia_Phong;
    private String Id_Phong;
    private String Vitri_Phong;

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public int getImage_Phong() {
        return Image_Phong;
    }

    public void setImage_Phong(int image_Phong) {
        Image_Phong = image_Phong;
    }

    public int getGia_Phong() {
        return Gia_Phong;
    }

    public void setGia_Phong(int gia_Phong) {
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

    public PhongTro(String tenPhong, int image_Phong, int gia_Phong, String vitri_Phong) {
        TenPhong = tenPhong;
        Image_Phong = image_Phong;
        Gia_Phong = gia_Phong;
        Vitri_Phong = vitri_Phong;
    }

    public PhongTro() {
    }
}
