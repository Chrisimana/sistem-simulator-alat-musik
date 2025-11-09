package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VisualizerPanel extends JPanel {
    private Timer animationTimer;
    private int[] waveform;
    private Color visualizerColor;
    private Random random;
    
    public VisualizerPanel() {
        random = new Random();
        waveform = new int[100];
        visualizerColor = new Color(70, 130, 180);
        initWaveform();
        setupAnimation();
    }
    
    private void initWaveform() {
        for (int i = 0; i < waveform.length; i++) {
            waveform[i] = 10 + random.nextInt(30);
        }
    }
    
    private void setupAnimation() {
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWaveform();
                repaint();
            }
        });
        animationTimer.start();
    }
    
    private void updateWaveform() {
        // Shift waveform to the left
        for (int i = 0; i < waveform.length - 1; i++) {
            waveform[i] = waveform[i + 1];
        }
        // Add new random value at the end
        waveform[waveform.length - 1] = 10 + random.nextInt(50);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background gradient
        GradientPaint gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 
                                                  getWidth(), getHeight(), new Color(60, 60, 60));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String title = "🎵 Visualizer Audio Alat Musik";
        FontMetrics fm = g2d.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        g2d.drawString(title, (getWidth() - titleWidth) / 2, 40);
        
        // Draw waveform
        int centerY = getHeight() / 2;
        int barWidth = getWidth() / waveform.length;
        
        for (int i = 0; i < waveform.length; i++) {
            int height = waveform[i];
            int x = i * barWidth;
            int y = centerY - height / 2;
            
            // Create color gradient based on height
            int green = Math.min(255, 100 + height * 3);
            int blue = Math.min(255, 150 + height * 2);
            Color barColor = new Color(70, green, blue);
            
            g2d.setColor(barColor);
            g2d.fillRect(x, y, barWidth - 1, height);
            
            // Add highlight effect
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.fillRect(x, y, barWidth - 1, 3);
        }
        
        // Draw center line
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.drawLine(0, centerY, getWidth(), centerY);
        
        // Draw info text
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        String info = "Visualizer menampilkan gelombang suara dari alat musik yang dimainkan";
        int infoWidth = g2d.getFontMetrics().stringWidth(info);
        g2d.drawString(info, (getWidth() - infoWidth) / 2, getHeight() - 20);
    }
    
    public void setVisualizerColor(Color color) {
        this.visualizerColor = color;
        repaint();
    }
    
    public void stopAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }
    
    public void startAnimation() {
        if (animationTimer != null) {
            animationTimer.start();
        }
    }
}