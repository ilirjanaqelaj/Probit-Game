import com.vdurmont.emoji.EmojiParser;

import java.util.Random;

public class Game {
    private Display ui = new ConsoleDisplay();
    private Player player;

    private static int COLS = 10;
    private static int ROWS = 10;

    String str4 = ":ghost:";
    String symbol4 = EmojiParser.parseToUnicode(str4);


    public Game(Display ui) {
        this.ui = ui;
    }


   /* private void reset() {
        board.reset();
        ui.showMessage(board.toString());
    }
*/

    public void play(Board board) throws InterruptedException {
        String str = ":grinning:";
        String str2 = ":lock:";
        String str3 = ":cyclone:";
        String str4 = ":ghost:";

        String symbol = EmojiParser.parseToUnicode(str);
        String symbol2 = EmojiParser.parseToUnicode(str2);
        String symbol3 = EmojiParser.parseToUnicode(str3);
        String symbol4 = EmojiParser.parseToUnicode(str4);

        Player player = new Player(symbol);
        Pengesa pengesa = new Pengesa(symbol2);
        BlackHole blackHole = new BlackHole(symbol3);
        DangerousPlayer dangerousPlayer = new DangerousPlayer(symbol4);

        Position positionOfPengesa = new Position();
        Position positionOfBlackHole = new Position();
        Position positionOfPlayer = new Position(0, 0);
        Position positionOfDangerousPlayer = new Position(5, 5);


        board.updatePosition(player, positionOfPlayer);

        board.setLastDangerousPlayerPosition(positionOfDangerousPlayer);
        board.updateDangerousPlayer(dangerousPlayer, positionOfDangerousPlayer);

        Random random = new Random();

        for (int i = 0; i < COLS / 2; i++) {
            positionOfPengesa.setX(random.nextInt(ROWS));
            positionOfPengesa.setY(random.nextInt(COLS));

            positionOfBlackHole.setX(random.nextInt(ROWS));
            positionOfBlackHole.setY(random.nextInt(COLS));

            if (board.isValidPosition(positionOfPengesa)) {
                board.updatePengesa(pengesa, positionOfPengesa);
            }

            if (board.isValidPosition(positionOfBlackHole)) {
                board.updateBlackHole(blackHole, positionOfBlackHole);
            }

        }


    }

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, InterruptedException {
        String strPlayer = ":grinning:";
        String symbolPlayer = EmojiParser.parseToUnicode(strPlayer);

        String strDanger = ":ghost:";
        String symbolDanger = EmojiParser.parseToUnicode(strDanger);

        Player player = new Player(symbolPlayer);

        Board board = new Board(ROWS, COLS);
        DangerousPlayer dangerousPlayer = new DangerousPlayer(symbolDanger);
        WindowDisplay windowDisplay = new WindowDisplay("Loja", player, board, dangerousPlayer);
        Game game = new Game(windowDisplay);

        game.play(board);

        windowDisplay.showMessage("Points: " + board.getUserPoints());
        windowDisplay.update(board);
        windowDisplay.moveDangerousPlayer();


    }
}

