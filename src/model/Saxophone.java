package model;

import enums.KategoriAlat;

public class Saxophone extends AlatMusik {
    private String jenisSax;
    private String key;
    
    public Saxophone() {
        super("Saxophone", KategoriAlat.TIUP,
              "Alat musik tiup logam dengan suara yang ekspresif",
              4, "Belgia", 1840);
        this.jenisSax = "Alto";
        this.key = "E♭";
    }
    
    @Override
    public String mainkan() {
        setLastPlayed();
        return "🎷 Saxophone berbunyi: tuuuut tuuuut tuuuut... (Suara jazz yang smooth)";
    }
    
    @Override
    public String getJenisSuara() {
        return "Ekspresif, soulful dengan karakter vokal";
    }
    
    @Override
    public String getCaraMain() {
        return "Ditiup melalui mouthpiece dengan reed, jari menekan keys";
    }
    
    public String mainkanImprovisasi() {
        setLastPlayed();
        return "🎷 Saxophone improvisasi: tuu-tuu-tu-tu-tuuu... (Solo jazz yang improvisatif)";
    }
    
    // Getters
    public String getJenisSax() { return jenisSax; }
    public String getKey() { return key; }
}