package model;

import enums.KategoriAlat;

public class Gitar extends AlatMusik {
    private int jumlahSenar;
    private String jenisGitar;
    
    public Gitar() {
        super("Gitar", KategoriAlat.PETIK,
              "Alat musik petik dengan badan berongga dan senar",
              3, "Spanyol", 1500);
        this.jumlahSenar = 6;
        this.jenisGitar = "Akustik";
    }
    
    public Gitar(int jumlahSenar, String jenisGitar) {
        super("Gitar", KategoriAlat.PETIK,
              "Alat musik petik dengan badan berongga dan senar",
              3, "Spanyol", 1500);
        this.jumlahSenar = jumlahSenar;
        this.jenisGitar = jenisGitar;
    }
    
    @Override
    public String mainkan() {
        setLastPlayed();
        return "🎸 Gitar berbunyi: jreng jreng jreng... (Chord rock menggelegar)";
    }
    
    @Override
    public String getJenisSuara() {
        return "Ritmis dan harmonis, bisa akustik atau elektrik";
    }
    
    @Override
    public String getCaraMain() {
        return "Dipetik atau dipetik dengan pick, jari menekan senar pada fret";
    }
    
    public String mainkanSolo() {
        setLastPlayed();
        return "🎸 Gitar solo: jreeeng~~~ (Lead guitar yang epic!)";
    }
    
    // Getters
    public int getJumlahSenar() { return jumlahSenar; }
    public String getJenisGitar() { return jenisGitar; }
}