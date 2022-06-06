package com.phuongthao.app_nhatro.Models;

public class DanhSachDaDuyet {
    String SoCMNN, TenPhong, hinhCMNN, hoten, idKhachHang, idPhong, namsinh, quequan, sdt, idChuTro;

    public DanhSachDaDuyet() {
    }

    public DanhSachDaDuyet(String soCMNN, String tenPhong, String hinhCMNN, String hoten, String idKhachHang, String idPhong, String namsinh, String quequan, String sdt, String idChuTro) {
        SoCMNN = soCMNN;
        TenPhong = tenPhong;
        this.hinhCMNN = hinhCMNN;
        this.hoten = hoten;
        this.idKhachHang = idKhachHang;
        this.idPhong = idPhong;
        this.namsinh = namsinh;
        this.quequan = quequan;
        this.sdt = sdt;
        this.idChuTro = idChuTro;
    }

    public String getSoCMNN() {
        return SoCMNN;
    }

    public void setSoCMNN(String soCMNN) {
        SoCMNN = soCMNN;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getHinhCMNN() {
        return hinhCMNN;
    }

    public void setHinhCMNN(String hinhCMNN) {
        this.hinhCMNN = hinhCMNN;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
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

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(String idChuTro) {
        this.idChuTro = idChuTro;
    }
}
