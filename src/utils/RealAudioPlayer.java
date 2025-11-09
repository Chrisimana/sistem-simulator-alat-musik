package utils;

import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.*;
import javax.sound.sampled.*;

public class RealAudioPlayer {
    private static Map<String, String> audioFiles = new HashMap<>();
    private static Synthesizer synthesizer;
    private static MidiChannel[] channels;
    
    static {
        // Inisialisasi mapping alat musik ke note MIDI
        audioFiles.put("Piano", "PIANO");
        audioFiles.put("Biola", "VIOLIN"); 
        audioFiles.put("Gitar", "GUITAR");
        audioFiles.put("Drum", "DRUMS");
        audioFiles.put("Saxophone", "SAXOPHONE");
        audioFiles.put("Trumpet", "TRUMPET");
        
        initializeMIDI();
    }
    
    private static void initializeMIDI() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();
            
            // Set instruments (program change)
            channels[0].programChange(0);  // Piano
            channels[1].programChange(40); // Violin
            channels[2].programChange(24); // Guitar
            channels[3].programChange(65); // Saxophone
            channels[4].programChange(56); // Trumpet
            
        } catch (MidiUnavailableException e) {
            System.err.println("MIDI synthesizer tidak tersedia: " + e.getMessage());
        }
    }
    
    public static void playInstrumentSound(String instrumentName) {
        new Thread(() -> {
            try {
                switch (instrumentName.toUpperCase()) {
                    case "PIANO":
                        playPiano();
                        break;
                    case "BIOLA":
                        playViolin();
                        break;
                    case "GITAR":
                        playGuitar();
                        break;
                    case "DRUM":
                        playDrums();
                        break;
                    case "SAXOPHONE":
                        playSaxophone();
                        break;
                    case "TRUMPET":
                        playTrumpet();
                        break;
                    default:
                        playDefaultTone();
                }
            } catch (Exception e) {
                System.err.println("Error memainkan suara: " + e.getMessage());
                playFallbackTone(instrumentName);
            }
        }).start();
    }
    
    private static void playPiano() {
        try {
            // Mainkan chord C major
            channels[0].noteOn(60, 80);  // C4
            Thread.sleep(200);
            channels[0].noteOn(64, 80);  // E4
            Thread.sleep(200);
            channels[0].noteOn(67, 80);  // G4
            Thread.sleep(500);
            
            channels[0].noteOff(60);
            channels[0].noteOff(64);
            channels[0].noteOff(67);
            
            // Mainkan melodi sederhana
            Thread.sleep(100);
            channels[0].noteOn(72, 90);  // C5
            Thread.sleep(300);
            channels[0].noteOff(72);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playViolin() {
        try {
            // Mainkan nada violin yang emosional
            channels[1].noteOn(67, 70);  // G4
            Thread.sleep(400);
            channels[1].noteOff(67);
            
            channels[1].noteOn(65, 75);  // F4
            Thread.sleep(300);
            channels[1].noteOff(65);
            
            channels[1].noteOn(64, 80);  // E4
            Thread.sleep(500);
            channels[1].noteOff(64);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playGuitar() {
        try {
            // Mainkan riff gitar
            channels[2].noteOn(64, 85);  // E4
            Thread.sleep(150);
            channels[2].noteOff(64);
            
            channels[2].noteOn(67, 85);  // G4
            Thread.sleep(150);
            channels[2].noteOff(67);
            
            channels[2].noteOn(64, 85);  // E4
            Thread.sleep(150);
            channels[2].noteOff(64);
            
            channels[2].noteOn(62, 85);  // D4
            Thread.sleep(300);
            channels[2].noteOff(62);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playDrums() {
        try {
            // Channel 9 adalah channel drum di MIDI
            MidiChannel drumChannel = channels[9];
            
            // Beat drum sederhana
            drumChannel.noteOn(36, 100); // Bass drum
            Thread.sleep(200);
            drumChannel.noteOff(36);
            
            drumChannel.noteOn(42, 90);  // Closed hi-hat
            Thread.sleep(100);
            drumChannel.noteOff(42);
            
            drumChannel.noteOn(38, 95);  // Snare drum
            Thread.sleep(200);
            drumChannel.noteOff(38);
            
            drumChannel.noteOn(42, 90);  // Closed hi-hat
            Thread.sleep(100);
            drumChannel.noteOff(42);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playSaxophone() {
        try {
            // Mainkan nada saxophone yang smooth
            channels[3].noteOn(65, 75);  // F4
            Thread.sleep(300);
            channels[3].noteOn(67, 80);  // G4
            Thread.sleep(200);
            channels[3].noteOff(65);
            Thread.sleep(300);
            channels[3].noteOff(67);
            
            channels[3].noteOn(69, 85);  // A4
            Thread.sleep(400);
            channels[3].noteOff(69);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playTrumpet() {
        try {
            // Mainkan fanfare trumpet
            channels[4].noteOn(72, 95);  // C5
            Thread.sleep(200);
            channels[4].noteOff(72);
            
            channels[4].noteOn(74, 95);  // D5
            Thread.sleep(150);
            channels[4].noteOff(74);
            
            channels[4].noteOn(76, 100); // E5
            Thread.sleep(300);
            channels[4].noteOff(76);
            
            channels[4].noteOn(72, 100); // C5
            Thread.sleep(400);
            channels[4].noteOff(72);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playDefaultTone() {
        try {
            // Tone default menggunakan beep system
            for (int i = 0; i < 3; i++) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void playFallbackTone(String instrumentName) {
        // Fallback ke generated tone menggunakan AudioFormat
        try {
            byte[] sinWave = generateSineWave(440, 800); // A4 note
            playGeneratedSound(sinWave);
        } catch (Exception e) {
            System.err.println("Fallback sound juga gagal: " + e.getMessage());
        }
    }
    
    private static byte[] generateSineWave(double frequency, int durationMs) {
        int sampleRate = 44100;
        int numSamples = durationMs * sampleRate / 1000;
        byte[] output = new byte[numSamples];
        
        for (int i = 0; i < numSamples; i++) {
            double angle = 2.0 * Math.PI * i / (sampleRate / frequency);
            output[i] = (byte) (Math.sin(angle) * 127);
        }
        
        return output;
    }
    
    private static void playGeneratedSound(byte[] audioBytes) {
        try {
            AudioFormat format = new AudioFormat(44100, 8, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            
            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Line tidak supported untuk generated audio");
                return;
            }
            
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            line.write(audioBytes, 0, audioBytes.length);
            line.drain();
            line.close();
            
        } catch (LineUnavailableException e) {
            System.err.println("Line audio tidak tersedia: " + e.getMessage());
        }
    }
    
    public static void stopAllSounds() {
        if (channels != null) {
            for (MidiChannel channel : channels) {
                if (channel != null) {
                    channel.allNotesOff();
                }
            }
        }
    }
    
    public static void close() {
        stopAllSounds();
        if (synthesizer != null && synthesizer.isOpen()) {
            synthesizer.close();
        }
    }
}