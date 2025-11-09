import gui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        // Set look and feel yang lebih kompatibel
        try {
            // Coba beberapa Look and Feel yang umum
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName()) || "Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Jika gagal, gunakan default
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        // Run GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
            
            // Show welcome message
            javax.swing.JOptionPane.showMessageDialog(frame,
                "🎵 Selamat datang di Sistem Simulator Alat Musik!\n\n" +
                "Fitur Super:\n" +
                "• Simulasi 6 alat musik berbeda\n" +
                "• Info detail sejarah dan cara main\n" +
                "• Aksi spesial setiap alat musik\n" +
                "• Riwayat dan statistik\n" +
                "• Visualizer audio yang keren\n" +
                "• Penyimpanan riwayat otomatis",
                "Welcome to Music World!",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        });
    }
}