package model;

import enums.KategoriAlat;

public class Biola extends AlatMusik {
    private String bahanSenar;
    private double panjangBadan;
    
    public Biola() {
        super("Biola", KategoriAlat.GESEK,
              "Alat musik gesek dengan 4 senar yang menghasilkan suara melankolis",
              5, "Italia", 1550);
        this.bahanSenar = "Gut";
        this.panjangBadan = 35.5;
    }
    
    @Override
    public String mainkan() {
        setLastPlayed();
        return "🎻 Biola berbunyi: ngiing ngiing ngiing... (Suara emosional dan mendalam)";
    }
    
    @Override
    public String getJenisSuara() {
        return "Melankolis, emosional dengan vibrato";
    }
    
    @Override
    public String getCaraMain() {
        return "Digesek dengan bow dan jari menekan senar pada fingerboard";
    }
    
    public String mainkanVibrato() {
        setLastPlayed();
        return "🎻 Biola dengan vibrato: ngiing~~~ (Suara bergetar emosional)";
    }
    
    // Getters
    public String getBahanSenar() { return bahanSenar; }
    public double getPanjangBadan() { return panjangBadan; }
}