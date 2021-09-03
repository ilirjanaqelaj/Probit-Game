
import com.vdurmont.emoji.EmojiParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Display ui = new ConsoleDisplay();
    private Player player;

    private static int COLS=6;
    private static int ROWS=6;


    public Game(Display ui) {
        this.ui = ui;
    }


   /* private void reset() {
        board.reset();
        ui.showMessage(board.toString());
    }
*/

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException{
        String str = ":grinning:";
        String str2=":lock:";
        String str3=":cyclone:";

        String symbol = EmojiParser.parseToUnicode(str);
        String symbol2=EmojiParser.parseToUnicode(str2);
        String symbol3=EmojiParser.parseToUnicode(str3);

        Player player=new Player(symbol);
        Pengesa pengesa=new Pengesa(symbol2);
        BlackHole blackHole=new BlackHole(symbol3);

        Board board=new Board(COLS,ROWS);
        WindowDisplay windowDisplay=new WindowDisplay("Loja",player,board);

        Position positionOfPlayer=new Position(0,0);
        Position positionOfPengesa=new Position();
        Position positionOfBlackHole=new Position();

        board.updatePosition(player,positionOfPlayer);

        Random random=new Random();

        for(int i=0;i<COLS/2;i++)
        {
            positionOfPengesa.setX(random.nextInt(COLS));
            positionOfPengesa.setY(random.nextInt(COLS));

            positionOfBlackHole.setX(random.nextInt(COLS));
            positionOfBlackHole.setY(random.nextInt(COLS));

            board.updatePengesa(pengesa,positionOfPengesa);
            board.updateBlackHole(blackHole,positionOfBlackHole);

        }




        windowDisplay.update(board);

    }
}

