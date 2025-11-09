package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class AudioSampleManager {
    private static Map<String, File> audioSamples = new HashMap<>();
    private static final String SAMPLE_DIR = "audio_samples/";
    
    static {
        // Buat directory jika belum ada
        new File(SAMPLE_DIR).mkdirs();
        initializeSamples();
    }
    
    private static void initializeSamples() {
        // Untuk demo, kita buat sample audio sederhana
        createDemoSamples();
    }
    
    private static void createDemoSamples() {
        try {
            // Create simple demo samples (in real app, you would load real audio files)
            System.out.println("Menyiapkan sample audio demo...");
            
            // Sample files would be created here
            // For now, we'll use MIDI instead for real sound
            
        } catch (Exception e) {
            System.err.println("Error creating demo samples: " + e.getMessage());
        }
    }
    
    public static void playSample(String instrumentName) {
        String sampleKey = getSampleKey(instrumentName);
        
        if (audioSamples.containsKey(sampleKey) && audioSamples.get(sampleKey).exists()) {
            playAudioFile(audioSamples.get(sampleKey));
        } else {
            // Fallback to MIDI
            RealAudioPlayer.playInstrumentSound(instrumentName);
        }
    }
    
    private static String getSampleKey(String instrumentName) {
        return instrumentName.toUpperCase().replace(" ", "_");
    }
    
    private static void playAudioFile(File audioFile) {
        new Thread(() -> {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                
                SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
                audioLine.open(format);
                audioLine.start();
                
                byte[] buffer = new byte[4096];
                int bytesRead;
                
                while ((bytesRead = audioStream.read(buffer)) != -1) {
                    audioLine.write(buffer, 0, bytesRead);
                }
                
                audioLine.drain();
                audioLine.close();
                audioStream.close();
                
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                System.err.println("Error playing audio file: " + e.getMessage());
                // Fallback to generated sound
                RealAudioPlayer.playInstrumentSound(getInstrumentFromFile(audioFile));
            }
        }).start();
    }
    
    private static String getInstrumentFromFile(File file) {
        String name = file.getName().toLowerCase();
        if (name.contains("piano")) return "PIANO";
        if (name.contains("violin") || name.contains("biola")) return "BIOLA";
        if (name.contains("guitar") || name.contains("gitar")) return "GITAR";
        if (name.contains("drum")) return "DRUM";
        if (name.contains("sax")) return "SAXOPHONE";
        if (name.contains("trumpet")) return "TRUMPET";
        return "PIANO"; // default
    }
    
    public static boolean hasSample(String instrumentName) {
        return audioSamples.containsKey(getSampleKey(instrumentName)) && 
               audioSamples.get(getSampleKey(instrumentName)).exists();
    }
}