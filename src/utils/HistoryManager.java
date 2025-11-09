package utils;

import model.AlatMusik;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static final String HISTORY_FILE = "music_history.dat";
    private List<HistoryEntry> history;
    private static HistoryManager instance;
    
    private HistoryManager() {
        this.history = new ArrayList<>();
        loadHistory();
    }
    
    public static HistoryManager getInstance() {
        if (instance == null) {
            instance = new HistoryManager();
        }
        return instance;
    }
    
    public void addHistory(AlatMusik alatMusik, String aksi) {
        HistoryEntry entry = new HistoryEntry(alatMusik, aksi);
        history.add(0, entry); // Add to beginning for latest first
        
        // Keep only last 100 entries
        if (history.size() > 100) {
            history = history.subList(0, 100);
        }
        
        saveHistory();
    }
    
    public List<HistoryEntry> getHistory() {
        return new ArrayList<>(history);
    }
    
    public void clearHistory() {
        history.clear();
        saveHistory();
    }
    
    @SuppressWarnings("unchecked")
    private void loadHistory() {
        File file = new File(HISTORY_FILE);
        if (!file.exists()) {
            System.out.println("File history tidak ditemukan, akan dibuat baru.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORY_FILE))) {
            Object data = ois.readObject();
            if (data instanceof List) {
                history = (List<HistoryEntry>) data;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File history tidak ditemukan, akan dibuat baru.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading history: " + e.getMessage());
            // Buat history baru jika error
            history = new ArrayList<>();
        }
    }
    
    private void saveHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE))) {
            oos.writeObject(history);
        } catch (IOException e) {
            System.err.println("Error saving history: " + e.getMessage());
        }
    }
    
    public static class HistoryEntry implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private final String namaAlat;
        private final String kategori;
        private final String aksi;
        private final String timestamp;
        
        public HistoryEntry(AlatMusik alatMusik, String aksi) {
            this.namaAlat = alatMusik.getNama();
            this.kategori = alatMusik.getKategori().getDeskripsi();
            this.aksi = aksi;
            this.timestamp = java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        }
        
        // Getters
        public String getNamaAlat() { return namaAlat; }
        public String getKategori() { return kategori; }
        public String getAksi() { return aksi; }
        public String getTimestamp() { return timestamp; }
        
        @Override
        public String toString() {
            return String.format("[%s] %s - %s: %s", timestamp, kategori, namaAlat, aksi);
        }
    }
}