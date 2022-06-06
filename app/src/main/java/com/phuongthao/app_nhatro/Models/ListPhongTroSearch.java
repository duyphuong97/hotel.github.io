package com.phuongthao.app_nhatro.Models;

public class ListPhongTroSearch {
    private String ImageUrl,TenPhong,Idchutro, idNhatro, ToaDo;

    public ListPhongTroSearch() {
    }

    public ListPhongTroSearch(String imageUrl, String tenPhong, String idchutro, String idNhatro, String ToaDo) {
        ImageUrl = imageUrl;
        TenPhong = tenPhong;
        Idchutro = idchutro;
        this.idNhatro = idNhatro;
        this.ToaDo = ToaDo;
    }

    public String getToaDo() {
        return ToaDo;
    }

    public void setToaDo(String toaDo) {
        ToaDo = toaDo;
    }

    public String getIdNhatro() {
        return idNhatro;
    }

    public void setIdNhatro(String idNhatro) {
        this.idNhatro = idNhatro;
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

    public String getIdchutro() {
        return Idchutro;
    }

    public void setIdchutro(String idchutro) {
        Idchutro = idchutro;
    }
}
