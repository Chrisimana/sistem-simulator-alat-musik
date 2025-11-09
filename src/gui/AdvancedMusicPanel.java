package gui;

import model.*;
import utils.HistoryManager;
import utils.RealAudioPlayer;
import utils.AudioSampleManager;
import enums.KategoriAlat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedMusicPanel extends JPanel {
    private AlatMusik[] alatMusik;
    private JComboBox<String> comboAlatMusik;
    private JTextArea txtOutput;
    private JTextArea txtInfo;
    private JButton btnMainkan, btnInfo, btnSpesial, btnStop, btnVolumeUp, btnVolumeDown;
    private JSlider volumeSlider;
    private JCheckBox chkRealSound;
    private HistoryManager historyManager;
    
    public AdvancedMusicPanel() {
        historyManager = HistoryManager.getInstance();
        initAlatMusik();
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initAlatMusik() {
        alatMusik = new AlatMusik[6];
        alatMusik[0] = new Piano();
        alatMusik[1] = new Biola();
        alatMusik[2] = new Gitar();
        alatMusik[3] = new Drum();
        alatMusik[4] = new Saxophone();
        alatMusik[5] = new Trumpet();
    }
    
    private void initComponents() {
        // Combo box untuk pemilihan alat musik
        String[] namaAlat = {"Piano", "Biola", "Gitar", "Drum", "Saxophone", "Trumpet"};
        comboAlatMusik = new JComboBox<>(namaAlat);
        comboAlatMusik.setSelectedIndex(0);
        
        // Text areas
        txtOutput = new JTextArea(8, 40);
        txtOutput.setEditable(false);
        txtOutput.setBackground(new Color(240, 240, 240));
        txtOutput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        txtInfo = new JTextArea(12, 40);
        txtInfo.setEditable(false);
        txtInfo.setBackground(new Color(255, 255, 240));
        txtInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Buttons dengan styling
        btnMainkan = createStyledButton("🎵 Mainkan", new Color(70, 130, 180));
        btnInfo = createStyledButton("ℹ️ Info Detail", new Color(34, 139, 34));
        btnSpesial = createStyledButton("⭐ Aksi Spesial", new Color(255, 140, 0));
        btnStop = createStyledButton("⏹️ Stop", new Color(220, 53, 69));
        btnVolumeUp = createStyledButton("🔊+", new Color(100, 100, 100));
        btnVolumeDown = createStyledButton("🔉-", new Color(100, 100, 100));
        
        // Volume slider
        volumeSlider = new JSlider(0, 100, 80);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        
        // Checkbox untuk real sound
        chkRealSound = new JCheckBox("Gunakan Suara Real (MIDI)");
        chkRealSound.setSelected(true);
        chkRealSound.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Set initial info
        updateInfoDisplay(0);
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Top panel - Selection
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Pilih Alat Musik:"));
        topPanel.add(comboAlatMusik);
        topPanel.add(btnMainkan);
        topPanel.add(btnInfo);
        topPanel.add(btnSpesial);
        topPanel.add(btnStop);
        
        // Control panel - Volume and settings
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("Volume:"));
        controlPanel.add(btnVolumeDown);
        controlPanel.add(volumeSlider);
        controlPanel.add(btnVolumeUp);
        controlPanel.add(chkRealSound);
        
        // Center panel - Output and Info
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        JScrollPane scrollOutput = new JScrollPane(txtOutput);
        scrollOutput.setBorder(BorderFactory.createTitledBorder("Output Suara & Status Audio"));
        scrollOutput.setPreferredSize(new Dimension(400, 200));
        
        JScrollPane scrollInfo = new JScrollPane(txtInfo);
        scrollInfo.setBorder(BorderFactory.createTitledBorder("Informasi Alat Musik"));
        scrollInfo.setPreferredSize(new Dimension(400, 300));
        
        centerPanel.add(scrollOutput);
        centerPanel.add(scrollInfo);
        
        add(topPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);
        
        // Set preferred sizes
        setPreferredSize(new Dimension(1000, 650));
    }
    
    private void setupListeners() {
        comboAlatMusik.addActionListener(e -> {
            int selectedIndex = comboAlatMusik.getSelectedIndex();
            updateInfoDisplay(selectedIndex);
        });
        
        btnMainkan.addActionListener(e -> mainkanAlatMusik());
        btnInfo.addActionListener(e -> tampilkanInfoDetail());
        btnSpesial.addActionListener(e -> aksiSpesial());
        btnStop.addActionListener(e -> stopAudio());
        
        btnVolumeUp.addActionListener(e -> increaseVolume());
        btnVolumeDown.addActionListener(e -> decreaseVolume());
        
        volumeSlider.addChangeListener(e -> updateVolume());
        
        // Cleanup when panel is destroyed
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                if ((e.getChangeFlags() & java.awt.event.HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                    if (!e.getComponent().isDisplayable()) {
                        RealAudioPlayer.stopAllSounds();
                    }
                }
            }
        });
    }
    
    private void updateInfoDisplay(int index) {
        AlatMusik alat = alatMusik[index];
        String info = String.format(
            "🎵 %s\n\n" +
            "📋 Kategori: %s\n" +
            "🎯 Tingkat Kesulitan: %d/5\n" +
            "🌍 Asal Negara: %s\n" +
            "📅 Tahun Ditemukan: %d\n" +
            "🎼 Jenis Suara: %s\n" +
            "👐 Cara Main: %s\n\n" +
            "🔊 Status Audio: %s\n\n" +
            "📝 Deskripsi:\n%s",
            alat.getNama(),
            alat.getKategori().getDeskripsi(),
            alat.getTingkatKesulitan(),
            alat.getAsalNegara(),
            alat.getTahunDitemukan(),
            alat.getJenisSuara(),
            alat.getCaraMain(),
            chkRealSound.isSelected() ? "AKTIF (MIDI)" : "SIMULASI TEXT",
            alat.getDeskripsi()
        );
        txtInfo.setText(info);
    }
    
    private void mainkanAlatMusik() {
        int selectedIndex = comboAlatMusik.getSelectedIndex();
        AlatMusik alat = alatMusik[selectedIndex];
        
        String suara = alat.mainkan();
        String audioStatus = "";
        
        if (chkRealSound.isSelected()) {
            // Play real MIDI sound
            RealAudioPlayer.playInstrumentSound(alat.getNama());
            audioStatus = "🔊 SUARA REAL SEDANG DIMAINKAN...\n";
        } else {
            audioStatus = "🔇 MODE SIMULASI TEXT SAJA\n";
        }
        
        txtOutput.setText(audioStatus + suara + "\n\n" + 
                         RealAudioPlayer.class.getSimpleName() + ": " + 
                         (chkRealSound.isSelected() ? "MIDI ACTIVE" : "TEXT ONLY"));
        
        // Add to history
        historyManager.addHistory(alat, 
            chkRealSound.isSelected() ? "Memainkan dengan suara real" : "Memainkan simulasi");
    }
    
    private void tampilkanInfoDetail() {
        int selectedIndex = comboAlatMusik.getSelectedIndex();
        AlatMusik alat = alatMusik[selectedIndex];
        
        String detailInfo = "📖 INFO DETAIL LENGKAP\n" +
                           "=".repeat(50) + "\n" +
                           alat.getInfoDetail() + "\n\n" +
                           "🔊 KONFIGURASI AUDIO:\n" +
                           "• MIDI Support: " + (isMIDISupported() ? "TERSEDIA" : "TIDAK TERSEDIA") + "\n" +
                           "• Real Sound: " + (chkRealSound.isSelected() ? "AKTIF" : "NON-AKTIF") + "\n" +
                           "• Volume: " + volumeSlider.getValue() + "%";
        
        JTextArea detailArea = new JTextArea(detailInfo);
        detailArea.setEditable(false);
        detailArea.setBackground(new Color(255, 255, 240));
        detailArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(detailArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Info Detail " + alat.getNama(), JOptionPane.INFORMATION_MESSAGE);
        
        historyManager.addHistory(alat, "Melihat info detail audio");
    }
    
    private void aksiSpesial() {
        int selectedIndex = comboAlatMusik.getSelectedIndex();
        AlatMusik alat = alatMusik[selectedIndex];
        String aksiSpesial = "";
        
        if (alat instanceof Piano) {
            aksiSpesial = ((Piano) alat).mainkanLagu("Für Elise");
        } else if (alat instanceof Biola) {
            aksiSpesial = ((Biola) alat).mainkanVibrato();
        } else if (alat instanceof Gitar) {
            aksiSpesial = ((Gitar) alat).mainkanSolo();
        } else if (alat instanceof Drum) {
            aksiSpesial = ((Drum) alat).mainkanFill();
        } else if (alat instanceof Saxophone) {
            aksiSpesial = ((Saxophone) alat).mainkanImprovisasi();
        } else if (alat instanceof Trumpet) {
            aksiSpesial = ((Trumpet) alat).mainkanFanfare();
        }
        
        if (chkRealSound.isSelected()) {
            // Play extended version for special actions
            RealAudioPlayer.playInstrumentSound(alat.getNama());
            // Play second time for extended effect
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        RealAudioPlayer.playInstrumentSound(alat.getNama());
                    }
                },
                800
            );
        }
        
        txtOutput.setText("🎭 AKSI SPESIAL AKTIF!\n" + aksiSpesial);
        historyManager.addHistory(alat, "Melakukan aksi spesial dengan audio");
    }
    
    private void stopAudio() {
        RealAudioPlayer.stopAllSounds();
        txtOutput.setText("⏹️ SEMUA SUARA DIHENTIKAN\n\nSemua audio yang sedang diputar telah dihentikan.");
    }
    
    private void increaseVolume() {
        int current = volumeSlider.getValue();
        volumeSlider.setValue(Math.min(100, current + 10));
        updateVolume();
    }
    
    private void decreaseVolume() {
        int current = volumeSlider.getValue();
        volumeSlider.setValue(Math.max(0, current - 10));
        updateVolume();
    }
    
    private void updateVolume() {
        // Dalam implementasi nyata, ini akan mengatur volume MIDI
        txtOutput.setText("🔊 Volume diatur ke: " + volumeSlider.getValue() + "%\n" +
                         "Note: Volume control tersedia untuk MIDI synthesizer.");
    }
    
    private boolean isMIDISupported() {
        try {
            return javax.sound.midi.MidiSystem.getSynthesizer() != null;
        } catch (Exception e) {
            return false;
        }
    }
}