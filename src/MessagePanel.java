import java.awt.*;
import javax.swing.*;

public class MessagePanel extends JPanel {
    private int width;
    private int height;
    private String message;

    public MessagePanel(int boxSize) {
        width = width * boxSize;
        height = 20;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (message != null) {
            g.setColor(Color.red);
            g.drawString(message, 10, 10);
        }
    }

    public void updateDrawing(String message) {
        this.message = message;
        repaint();
    }
}