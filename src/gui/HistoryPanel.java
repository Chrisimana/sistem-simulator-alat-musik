package gui;

import utils.HistoryManager;
import utils.FileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryPanel extends JPanel {
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private JButton btnRefresh, btnClear, btnExport;
    private HistoryManager historyManager;
    
    public HistoryPanel() {
        historyManager = HistoryManager.getInstance();
        initComponents();
        setupLayout();
        setupListeners();
        refreshTable();
    }
    
    private void initComponents() {
        // Table model
        String[] columnNames = {"Waktu", "Alat Musik", "Kategori", "Aksi"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        historyTable = new JTable(tableModel);
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyTable.getTableHeader().setReorderingAllowed(false);
        
        // Buttons
        btnRefresh = createStyledButton("🔄 Refresh", new Color(70, 130, 180));
        btnClear = createStyledButton("🗑️ Clear History", new Color(220, 53, 69));
        btnExport = createStyledButton("📁 Export ke Text", new Color(40, 167, 69));
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        return button;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExport);
        
        // Table with scroll
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Riwayat Simulasi Alat Musik"));
        
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void setupListeners() {
        btnRefresh.addActionListener(e -> refreshTable());
        
        btnClear.addActionListener(e -> clearHistory());
        
        btnExport.addActionListener(e -> exportHistory());
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        List<HistoryManager.HistoryEntry> history = historyManager.getHistory();
        
        for (HistoryManager.HistoryEntry entry : history) {
            tableModel.addRow(new Object[]{
                entry.getTimestamp(),
                entry.getNamaAlat(),
                entry.getKategori(),
                entry.getAksi()
            });
        }
        
        JOptionPane.showMessageDialog(this, 
            String.format("Data diperbarui! Total %d entri riwayat.", history.size()),
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearHistory() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin menghapus semua riwayat?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            historyManager.clearHistory();
            refreshTable();
            JOptionPane.showMessageDialog(this,
                "Riwayat berhasil dihapus!",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportHistory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Riwayat sebagai Text");
        fileChooser.setSelectedFile(new java.io.File("riwayat_alat_musik.txt"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileName.toLowerCase().endsWith(".txt")) {
                fileName += ".txt";
            }
            
            List<HistoryManager.HistoryEntry> history = historyManager.getHistory();
            FileHandler.exportHistoryToText(fileName, history);
            
            JOptionPane.showMessageDialog(this,
                String.format("Riwayat berhasil diekspor ke:\n%s", fileName),
                "Ekspor Sukses",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}