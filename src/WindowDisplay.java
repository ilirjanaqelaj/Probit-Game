import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class WindowDisplay extends JFrame implements Display, KeyListener {
    private MessagePanel pnlMessage;
    private BoardPanel pnlBoard;
    private int boxSize = 50;
    private Board board;
    private Player player;
    private DangerousPlayer dangerousPlayer;
    private int userPoints=0;
    protected Timer timer = null;

    int x = 5;
    int y = 5;

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
        showMessage("Points : " + this.userPoints);



        this.timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == timer) {
                    moveDangerousPlayer();
                }

            }
        });

        timer.start();
    }


    private void moveDangerousPlayer() {

        Random random = new Random();
        int x = random.nextInt(board.ROWS);
        int y = random.nextInt(board.COLS);

       // System.out.println(String.format("X = %d, Y = %d", x, y));
        Position position = new Position(x,y);


        if (board.areInsideBounds(x,y) && board.getBox(x,y).isEmptyPengesa()) {
                    board.updateDangerousPlayer(dangerousPlayer, position);
                    showMessage("Points: " + board.getUserPoints());
                    update(board);

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
            if(board.withBlackHolePosition(position)){
             gameOver();
            }


            if (board.isValidPosition(position) && board.areInsideBounds(position.getX(), position.getY())) {

                board.updatePosition(player, position);
                if(board.samePositionAsDangerous()) {
                    gameOver();
                }
//                showMessage("Points: " + board.getUserPoints());
                update(board);

            }

        } catch (Exception ex) {

        }

    }

public void gameOver(){
    timer.stop();
    JOptionPane.showMessageDialog(null, "GAME OVER");
    if (wantsToPlayMore())
    {
        this.userPoints=0;
        Board newBoard=new Board(Board.ROWS,Board.COLS);
        WindowDisplay windowsDisplay=new WindowDisplay("",player,newBoard, dangerousPlayer);
        Game game=new Game(windowsDisplay);
        windowsDisplay.update(newBoard);
        game.play(newBoard);
    } else {
        System.exit(0);
    }
}

}