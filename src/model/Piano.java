package model;

import enums.KategoriAlat;

public class Piano extends AlatMusik {
    private int jumlahOctave;
    private String jenisPiano;
    
    public Piano() {
        super("Piano", KategoriAlat.TEKAN, 
              "Alat musik dengan tuts yang menghasilkan suara ketika ditekan", 
              4, "Italia", 1700);
        this.jumlahOctave = 7;
        this.jenisPiano = "Grand Piano";
    }
    
    public Piano(int jumlahOctave, String jenisPiano) {
        super("Piano", KategoriAlat.TEKAN, 
              "Alat musik dengan tuts yang menghasilkan suara ketika ditekan", 
              4, "Italia", 1700);
        this.jumlahOctave = jumlahOctave;
        this.jenisPiano = jenisPiano;
    }
    
    @Override
    public String mainkan() {
        setLastPlayed();
        return "🎹 Piano berbunyi: ting ting ting ting... (Melodi indah mengalun)";
    }
    
    @Override
    public String getJenisSuara() {
        return "Harmonik dan resonan dengan sustain panjang";
    }
    
    @Override
    public String getCaraMain() {
        return "Ditekan menggunakan jari-jari pada tuts";
    }
    
    public String mainkanLagu(String judulLagu) {
        setLastPlayed();
        return String.format("🎹 Memainkan '%s' di Piano: ting ting ting... (Lagu mengalun sempurna)", judulLagu);
    }
    
    // Getters
    public int getJumlahOctave() { return jumlahOctave; }
    public String getJenisPiano() { return jenisPiano; }
}