package enums;

public enum KategoriAlat {
    TIUP("Alat Musik Tiup", "🎺"),
    PETIK("Alat Musik Petik", "🎸"),
    PUKUL("Alat Musik Pukul", "🥁"),
    GESEK("Alat Musik Gesek", "🎻"),
    TEKAN("Alat Musik Tekan", "🎹");
    
    private final String deskripsi;
    private final String emoji;
    
    KategoriAlat(String deskripsi, String emoji) {
        this.deskripsi = deskripsi;
        this.emoji = emoji;
    }
    
    public String getDeskripsi() {
        return deskripsi + " " + emoji;
    }
    
    public String getEmoji() {
        return emoji;
    }
}