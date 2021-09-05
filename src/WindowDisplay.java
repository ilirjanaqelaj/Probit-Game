
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
    private int userPoints=0;

    public WindowDisplay(String title, Player player, Board board) {
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
        this.board = board;
        showMessage("Points : " + this.userPoints);

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
        return JOptionPane.showConfirmDialog(null, "Deshiron te luash prap?", "Zgjedh", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION;
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
        if (e.getKeyChar() == 'w') {
            position = board.getPositionFromCommand('W');
        } else if (e.getKeyChar() == 's') {
            position = board.getPositionFromCommand('S');

        } else if (e.getKeyChar() == 'a') {
            position = board.getPositionFromCommand('A');

        } else if (e.getKeyChar() == 'd') {
            position = board.getPositionFromCommand('D');

        }



        try {
            if(board.withBlackHolePosition(position)){
                JOptionPane.showMessageDialog(null, "GAME OVER");
                if (wantsToPlayMore())
                {
                    Board newBoard=new Board(Board.ROWS,Board.COLS);
                    WindowDisplay windowsDisplay=new WindowDisplay("",player,newBoard);
                    Game game=new Game(windowsDisplay);
                    windowsDisplay.update(newBoard);
                    game.play(newBoard);
                } else {
                    this.userPoints = 0;
                }
            }


            if (board.isValidPosition(position) && board.areInsideBounds(position.getX(), position.getY())) {

                board.updatePosition(player, position);
                showMessage("Points: " + board.getUserPoints());
                update(board);

            }
        } catch (Exception ex) {

        }

    }


}