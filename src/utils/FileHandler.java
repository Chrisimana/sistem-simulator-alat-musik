package utils;

import model.AlatMusik;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FAVORITES_FILE = "favorites.dat";
    
    public static void saveFavorites(List<AlatMusik> favorites) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FAVORITES_FILE))) {
            oos.writeObject(favorites);
        } catch (IOException e) {
            System.err.println("Error menyimpan favorites: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<AlatMusik> loadFavorites() {
        File file = new File(FAVORITES_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FAVORITES_FILE))) {
            return (List<AlatMusik>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error memuat favorites: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static void exportHistoryToText(String filename, List<HistoryManager.HistoryEntry> history) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("RIWAYAT SIMULASI ALAT MUSIK");
            writer.println("=".repeat(50));
            writer.println();
            
            for (HistoryManager.HistoryEntry entry : history) {
                writer.printf("Waktu: %s\n", entry.getTimestamp());
                writer.printf("Alat: %s\n", entry.getNamaAlat());
                writer.printf("Kategori: %s\n", entry.getKategori());
                writer.printf("Aksi: %s\n", entry.getAksi());
                writer.println("-".repeat(30));
            }
            
            writer.printf("\nTotal Entri: %d\n", history.size());
            writer.printf("Diekspor pada: %s\n", 
                java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                    
        } catch (IOException e) {
            System.err.println("Error ekspor history: " + e.getMessage());
        }
    }
}