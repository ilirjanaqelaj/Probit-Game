import com.vdurmont.emoji.EmojiParser;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class WindowDisplay extends JFrame implements Display, KeyListener {
    private MessagePanel pnlMessage;
    private BoardPanel pnlBoard;
    private int boxSize = 50;
    private Board board;
    private Player player;
    private DangerousPlayer dangerousPlayer;
    private int userPoints = 0;


    public WindowDisplay(String title, Player player, Board board, DangerousPlayer dangerousPlayer) {

        setTitle(title);
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        pnlBoard = new BoardPanel(boxSize);
        content.add(pnlBoard, BorderLayout.NORTH);
        pnlMessage = new MessagePanel(boxSize);
        content.add(pnlMessage, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 200);
        pack();
        setVisible(true);
        setResizable(false);
        this.addKeyListener(this);
        this.player = player;
        this.dangerousPlayer = dangerousPlayer;
        this.board = board;
        showMessage("Points : " + userPoints);

    }


    public void moveDangerousPlayer() throws InterruptedException {
        Thread thread = new Thread();
        for (int row = board.ROWS - 1; row >= 0; row--) {
            for (int col = board.COLS - 1; col >= 0; col--) {

                Position position = new Position(row, col);

                if (board.playerSamePositionAsDangerous()) {
                    gameOver();
                }
                if (!(board.getBox(row, col).isEmptyPengesa())) {
                    ++col;
                    --row;
                    position.setX(row);
                    position.setY(col);

                }


                board.updateDangerousPlayer(dangerousPlayer, position);
                Thread.sleep(1000);
                update(board);

            }

        }
    }


    @Override
    public void showMessage(String s) {
        pnlMessage.updateDrawing(s);
    }

    @Override
    public Position readPosition() {
        return null;
    }

    @Override
    public Position readPosition(Player player) {
        return null;
    }

    @Override
    public void update(Board board) {
        pnlBoard.updateDrawing(board);
    }


    @Override
    public boolean wantsToPlayMore() {
        return JOptionPane.showConfirmDialog(null, "Deshiron te luash prap?", "Zgjedh", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
        Position position = new Position(0, 0);
        if (e.getKeyCode() == 87) {
            position = board.getPositionFromCommand('W');
        } else if (e.getKeyCode() == 83) {
            position = board.getPositionFromCommand('S');

        } else if (e.getKeyCode() == 65) {
            position = board.getPositionFromCommand('A');

        } else if (e.getKeyCode() == 68) {
            position = board.getPositionFromCommand('D');

        }

        try {

            if (board.isValidPosition(position) && board.areInsideBounds(position.getX(), position.getY())) {
                board.updatePosition(player, position);
                update(board);

            } else {
                board.updatePosition(player, board.getCurrentPosition());
            }

            if (board.withBlackHolePosition(position)) {
                gameOver();

            }


        } catch (Exception ex) {
            //ex.printStackTrace();
        }

    }

    public void gameOver() throws InterruptedException {
        JOptionPane.showMessageDialog(null, "GAME OVER");
        if (wantsToPlayMore()) {
            this.userPoints = 0;
            Board newBoard = new Board(Board.ROWS, Board.COLS);
            WindowDisplay windowsDisplay = new WindowDisplay("", player, newBoard, dangerousPlayer);
            Game game = new Game(windowsDisplay);
            windowsDisplay.update(newBoard);
            game.play(newBoard);
        } else {
            System.exit(0);
        }
    }

}