import java.awt.*;
import javax.swing.*;
public class BoardPanel extends JPanel {
    private Board board;
    private int width;
    private int height;
    private int boxWidth;
    private int boxHeight;
    public BoardPanel(int boxSize) {
        width = boxSize * Board.ROWS + 20;
        height = boxSize * Board.COLS + 20;
        boxWidth = width / Board.ROWS;
        boxHeight = height / Board.COLS;
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLS; j++) {
                int boxX = i * boxWidth;
                int boxY = j * boxHeight;
                g.drawRect(boxX, boxY, boxWidth, boxHeight);
                if (board != null) {
                    String content =board.getBox(i, j).toString();
                    g.drawString(content, boxY + boxHeight / 2, boxX + boxWidth / 2);
                }
            }
        }
    }
    public void updateDrawing(Board board) {
        this.board = board;
        repaint();
    }
}