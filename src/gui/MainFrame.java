package gui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    
    public MainFrame() {
        initComponents();
        setupFrame();
    }
    
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Create panels
        AdvancedMusicPanel musicPanel = new AdvancedMusicPanel();
        HistoryPanel historyPanel = new HistoryPanel();
        VisualizerPanel visualizerPanel = new VisualizerPanel();
        
        // Add tabs dengan icon sederhana
        tabbedPane.addTab("Simulator Alat Musik", musicPanel);
        tabbedPane.addTab("Riwayat", historyPanel);
        tabbedPane.addTab("Visualizer", visualizerPanel);
        
        // Set tooltips
        tabbedPane.setToolTipTextAt(0, "Simulasi memainkan berbagai alat musik");
        tabbedPane.setToolTipTextAt(1, "Lihat riwayat simulasi alat musik");
        tabbedPane.setToolTipTextAt(2, "Visualizer audio untuk alat musik");
    }
    
    private void setupFrame() {
        setTitle("Sistem Simulator Alat Musik - Super Edition v2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        
        // Create menu bar dengan fitur tambahan
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        
        // Add tabbed pane to frame
        add(tabbedPane);
        
        pack();
        setLocationRelativeTo(null); // Center the frame
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu File
        JMenu menuFile = new JMenu("File");
        JMenuItem menuBackup = new JMenuItem("Backup Data");
        JMenuItem menuEkspor = new JMenuItem("Ekspor Riwayat");
        JMenuItem menuExit = new JMenuItem("Keluar");
        
        menuBackup.addActionListener(e -> backupData());
        menuEkspor.addActionListener(e -> eksporData());
        menuExit.addActionListener(e -> System.exit(0));
        
        menuFile.add(menuBackup);
        menuFile.add(menuEkspor);
        menuFile.addSeparator();
        menuFile.add(menuExit);
        
        // Menu Alat Musik
        JMenu menuAlat = new JMenu("Alat Musik");
        JMenuItem menuTentangAlat = new JMenuItem("Tentang Alat Musik");
        menuTentangAlat.addActionListener(e -> showAlatInfo());
        menuAlat.add(menuTentangAlat);
        
        // Menu Help
        JMenu menuHelp = new JMenu("Help");
        JMenuItem menuAbout = new JMenuItem("Tentang");
        menuAbout.addActionListener(e -> showAbout());
        menuHelp.add(menuAbout);
        
        menuBar.add(menuFile);
        menuBar.add(menuAlat);
        menuBar.add(menuHelp);
        
        return menuBar;
    }
    
    private void backupData() {
        // Simulasi backup data
        JOptionPane.showMessageDialog(this, 
            "Backup data riwayat berhasil dilakukan!", "Backup", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void eksporData() {
        // Arahkan ke tab riwayat untuk ekspor
        tabbedPane.setSelectedIndex(1);
        JOptionPane.showMessageDialog(this,
            "Silakan gunakan tombol export di tab Riwayat",
            "Ekspor Data",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showAlatInfo() {
        String infoAlat = 
            "🎵 INFORMASI ALAT MUSIK\n\n" +
            "Alat Musik yang Tersedia:\n\n" +
            "🎹 PIANO (Tekan)\n" +
            "• Asal: Italia (1700)\n" +
            "• Cara Main: Ditekan dengan jari\n" +
            "• Suara: Harmonik dan resonan\n\n" +
            "🎻 BIOLA (Gesek)\n" +
            "• Asal: Italia (1550)\n" +
            "• Cara Main: Digesek dengan bow\n" +
            "• Suara: Melankolis dan emosional\n\n" +
            "🎸 GITAR (Petik)\n" +
            "• Asal: Spanyol (1500)\n" +
            "• Cara Main: Dipetik dengan jari/pick\n" +
            "• Suara: Ritmis dan harmonis\n\n" +
            "🥁 DRUM (Pukul)\n" +
            "• Asal: Amerika (1890)\n" +
            "• Cara Main: Dipukul dengan stik\n" +
            "• Suara: Perkusif dan ritmis\n\n" +
            "🎷 SAXOPHONE (Tiup)\n" +
            "• Asal: Belgia (1840)\n" +
            "• Cara Main: Ditiup dengan reed\n" +
            "• Suara: Ekspresif dan soulful\n\n" +
            "🎺 TRUMPET (Tiup)\n" +
            "• Asal: Jerman (1500)\n" +
            "• Cara Main: Ditiup dengan mouthpiece\n" +
            "• Suara: Terang dan heroik";
            
        JTextArea textArea = new JTextArea(infoAlat);
        textArea.setEditable(false);
        textArea.setBackground(new Color(255, 255, 240));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Informasi Alat Musik", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showAbout() {
        String aboutText = 
            "Sistem Simulator Alat Musik - Super Edition v2.0\n\n" +
            "Fitur Utama:\n" +
            "• Simulasi 6 alat musik berbeda\n" +
            "• Info detail setiap alat musik\n" +
            "• Aksi spesial untuk setiap alat\n" +
            "• Riwayat simulasi lengkap\n" +
            "• Visualizer audio\n" +
            "• Ekspor riwayat ke file text\n\n" +
            "Dibuat dengan Java Swing\n" +
            "Architecture: MVC Pattern";
            
        JOptionPane.showMessageDialog(this, aboutText, 
            "Tentang Aplikasi", JOptionPane.INFORMATION_MESSAGE);
    }
}