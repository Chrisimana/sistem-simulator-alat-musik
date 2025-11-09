package model;

import enums.KategoriAlat;
import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AlatMusik implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String nama;
    protected KategoriAlat kategori;
    protected String deskripsi;
    protected int tingkatKesulitan;
    protected String asalNegara;
    protected int tahunDitemukan;
    protected LocalDateTime lastPlayed;
    
    public AlatMusik(String nama, KategoriAlat kategori, String deskripsi, 
                    int tingkatKesulitan, String asalNegara, int tahunDitemukan) {
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.tingkatKesulitan = tingkatKesulitan;
        this.asalNegara = asalNegara;
        this.tahunDitemukan = tahunDitemukan;
    }
    
    // Abstract method yang harus diimplementasikan subclass
    public abstract String mainkan();
    public abstract String getJenisSuara();
    public abstract String getCaraMain();
    
    // Concrete methods
    public String getInfoDetail() {
        return String.format(
            "Nama: %s\nKategori: %s\nAsal Negara: %s\nTahun Ditemukan: %d\nTingkat Kesulitan: %d/5\nCara Main: %s\nDeskripsi: %s",
            nama, kategori, asalNegara, tahunDitemukan, tingkatKesulitan, getCaraMain(), deskripsi
        );
    }
    
    public void setLastPlayed() {
        this.lastPlayed = LocalDateTime.now();
    }
    
    // Getters
    public String getNama() { return nama; }
    public KategoriAlat getKategori() { return kategori; }
    public String getDeskripsi() { return deskripsi; }
    public int getTingkatKesulitan() { return tingkatKesulitan; }
    public String getAsalNegara() { return asalNegara; }
    public int getTahunDitemukan() { return tahunDitemukan; }
    public LocalDateTime getLastPlayed() { return lastPlayed; }
    
    @Override
    public String toString() {
        return nama;
    }
}