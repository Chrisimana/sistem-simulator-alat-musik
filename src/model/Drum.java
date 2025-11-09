package model;

import enums.KategoriAlat;

public class Drum extends AlatMusik {
    private int jumlahPiece;
    private String material;
    
    public Drum() {
        super("Drum Set", KategoriAlat.PUKUL,
              "Alat musik pukul terdiri dari berbagai drum dan cymbal",
              4, "Amerika", 1890);
        this.jumlahPiece = 5;
        this.material = "Maple";
    }
    
    @Override
    public String mainkan() {
        setLastPlayed();
        return "🥁 Drum berbunyi: dug dug dug dug... (Beat energik dan powerful)";
    }
    
    @Override
    public String getJenisSuara() {
        return "Ritmis, perkusif dengan berbagai timbre";
    }
    
    @Override
    public String getCaraMain() {
        return "Dipukul dengan stik drum, tangan, atau mallet";
    }
    
    public String mainkanFill() {
        setLastPlayed();
        return "🥁 Drum fill: dug-da-dug-da-dug! (Fill yang kompleks dan cepat)";
    }
    
    // Getters
    public int getJumlahPiece() { return jumlahPiece; }
    public String getMaterial() { return material; }
}