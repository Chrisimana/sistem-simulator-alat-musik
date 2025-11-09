package model;

import enums.KategoriAlat;

public class Trumpet extends AlatMusik {
    private String tuning;
    private int jumlahValve;
    
    public Trumpet() {
        super("Trumpet", KategoriAlat.TIUP,
              "Alat musik tiup logam dengan suara terang dan jernih",
              4, "Jerman", 1500);
        this.tuning = "B♭";
        this.jumlahValve = 3;
    }
    
    @Override
    public String mainkan() {
        setLastPlayed();
        return "🎺 Trumpet berbunyi: tuuu tuuu tuuu... (Suara terang dan heroik)";
    }
    
    @Override
    public String getJenisSuara() {
        return "Terang, jernih, dan heroik dengan proyeksi kuat";
    }
    
    @Override
    public String getCaraMain() {
        return "Ditiup melalui mouthpiece, kombinasi bibir dan valve";
    }
    
    public String mainkanFanfare() {
        setLastPlayed();
        return "🎺 Trumpet fanfare: Tu-tu-ru-tuuu! (Fanfare kerajaan yang megah)";
    }
    
    // Getters
    public String getTuning() { return tuning; }
    public int getJumlahValve() { return jumlahValve; }
}