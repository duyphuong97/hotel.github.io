package com.phuongthao.app_nhatro.Models;

public class UserChuTro {
    String TenChuTro, email, idChuTro;

    public UserChuTro() {
    }

    public UserChuTro(String tenChuTro, String email, String idChuTro) {
        TenChuTro = tenChuTro;
        this.email = email;
        this.idChuTro = idChuTro;
    }


    public String getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(String idChuTro) {
        this.idChuTro = idChuTro;
    }

    public String getTenChuTro() {
        return TenChuTro;
    }

    public void setTenChuTro(String tenChuTro) {
        TenChuTro = tenChuTro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
