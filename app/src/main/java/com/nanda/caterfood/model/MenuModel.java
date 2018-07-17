package com.nanda.caterfood.model;

public class MenuModel {

    private String mNamaMenu;
    private String mKategori;
    private String mHarga;
    private String mGambar;
    private String mDesk;

    public MenuModel(String mNamaMenu, String mKategori, String mHarga, String mGambar, String mDesk) {
        this.mNamaMenu = mNamaMenu;
        this.mKategori = mKategori;
        this.mHarga = mHarga;
        this.mGambar = mGambar;
        this.mDesk = mDesk;
    }

    public MenuModel(String mNamaMenu, String mKategori, String mHarga, String mGambar) {
        this.mNamaMenu = mNamaMenu;
        this.mKategori = mKategori;
        this.mHarga = mHarga;
        this.mGambar = mGambar;

    }

    public MenuModel() {
    }

    public String getmNamaMenu() {
        return mNamaMenu;
    }

    public void setmNamaMenu(String mNamaMenu) {
        this.mNamaMenu = mNamaMenu;
    }

    public String getmKategori() {
        return mKategori;
    }

    public void setmKategori(String mKategori) {
        this.mKategori = mKategori;
    }

    public String getmHarga() {
        return mHarga;
    }

    public void setmHarga(String mHarga) {
        this.mHarga = mHarga;
    }

    public String getmGambar() {
        return mGambar;
    }

    public void setmGambar(String mGambar) {
        this.mGambar = mGambar;
    }

    public String getmDesk() {
        return mDesk;
    }

    public void setmDesk(String mDesk) {
        this.mDesk = mDesk;
    }
}