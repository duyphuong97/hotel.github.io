package com.phuongthao.app_nhatro.Models;

public class ListPhongTro {
    private String TenPhong;
    private String Image_Phong;
    private String Id_Phong;
    private String Idchutro;
    private String ImageUrl;



    public ListPhongTro() {
    }

    public ListPhongTro(String tenPhong, String id_Phong, String idchutro, String imageUrl) {
        TenPhong = tenPhong;
        Id_Phong = id_Phong;
        Idchutro = idchutro;
        ImageUrl = imageUrl;
    }

    public ListPhongTro(String tenPhong, String image_Phong, String id_Phong) {
        TenPhong = tenPhong;
        Image_Phong = image_Phong;
        Id_Phong = id_Phong;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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

    public String getId_Phong() {
        return Id_Phong;
    }

    public void setId_Phong(String id_Phong) {
        Id_Phong = id_Phong;
    }

    public String getIdchutro() {
        return Idchutro;
    }

    public void setIdchutro(String idchutro) {
        Idchutro = idchutro;
    }
}
