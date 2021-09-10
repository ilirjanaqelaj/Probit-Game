import com.vdurmont.emoji.EmojiParser;

import java.util.Scanner;

public class ConsoleDisplay implements Display {

    Scanner in = new Scanner(System.in);
    static int[] array = new int[4];


    public void showMessage(String s) {
        System.out.print(s);
    }


    public Position readPosition(Player player) {
        showMessage("Vendos Pengesen : ");
        return readPosition();
    }

    public Position readPosition() {
        Position position = null;
        String xPosition;
        String yPosition;
        boolean validInput = true;
        do {
            xPosition = in.nextLine();
            yPosition = in.nextLine();
            // in.nextLine();

            if ((xPosition.equals(" ") || xPosition.equals("")) && (yPosition.equals(" ") || yPosition.equals(""))) {
                return position = null;
            }
            int xposition = Integer.parseInt(xPosition);
            int yposition = Integer.parseInt(yPosition);

            validInput = (xposition >= 0 && xposition < Board.ROWS) && (yposition >= 0 && yposition < Board.COLS);

            if (validInput) {
                position = new Position(xposition, yposition);
            } else {
                showMessage("Rreshti dhe shtylla duhet te jete ndermjet [" + (Board.ROWS - 1) + "," + (Board.COLS - 1) + "]:");
            }
        }


        while (!validInput);
        return position;

    }

    public boolean wantsToPlayMore() {
        boolean vazhdo = true;
        String line;
        do {
            showMessage("Deshiron te luash prap (Y/N)?\n");
            line = in.nextLine();
            if (line.length() > 0) {
                line = line.substring(0, 1);
                if (line.equalsIgnoreCase("Y") || line.equalsIgnoreCase("N")) {
                    vazhdo = false;
                } else {
                    showMessage("Hyrja eshte gabim sheno Y ose N!!\n");
                }
            }
        } while (vazhdo);
        return line.equalsIgnoreCase("Y");
    }

    @Override
    public void update(Board board) {
        showMessage(board.toString());
    }

    public void play() {
        do {
            Scanner sc = new Scanner(System.in);
            Player player = new Player("O");
            System.out.print("Inicializo Tabelen: ");
            for (int i = 0; i < 4; i++) {
                array[i] = sc.nextInt();

            }
            Board board = new Board(array[0], array[1]);
            Position position = new Position(array[2], array[3]);
            System.out.println("Tebela " + array[0] + "x" + array[1] + " u ndertua dhe lojtari u vendos ne poziten " + array[2] + "x" + array[3]);
            System.out.println("\n");
            board.updatePosition(player, position);
            showMessage(board.toString());

            String str2 = ":lock:";
            String symbol2 = EmojiParser.parseToUnicode(str2);

            Pengesa pengesa = new Pengesa(symbol2);

            Position pozitapengeses;
            do {
                pozitapengeses = readPosition(player);
                if (pozitapengeses != null) {
                    if (board.isValidPosition(pozitapengeses)) {
                        board.updatePengesa(pengesa, pozitapengeses);
                        showMessage(board.toString());
                    } else {
                        showMessage("Pozita e pengeses eshte gabim! ");
                    }
                }

            } while (pozitapengeses != null);

            char command1 = ' ';

            do {
                Scanner scanner = new Scanner(System.in);
                showMessage("Jap komanden (W , A , D , S , Q): ");
                String command = scanner.next();
                command1 = Character.toUpperCase(command.charAt(0));
                if (Character.isLetter(command1)) {
                    if ((command1 == 'W') || (command1 == 'A') || (command1 == 'D') || (command1 == 'S')) {

                        Position positionFromCommand = board.getPositionFromCommand(command1);
                        if (board.areInsideBounds(positionFromCommand.getX(), positionFromCommand.getY())) {

                            if (board.isValidPosition(positionFromCommand)) {
                                board.updatePosition(player, positionFromCommand);
                                showMessage(board.toString());
                            } else {
                                System.out.println("Lojtari nuk mund te kaloj ne pengese.");
                            }
                        } else {
                            System.out.println("Komanda nuk eshte brenda tabeles.");
                        }
                    } else if ((command1 == 'Q')) {
                        System.out.println("Loja mbaroi");
                    } else {
                        System.out.println("Keni dhene komande te gabuar!");
                    }

                }
            } while (!(command1 == 'Q'));

        } while (wantsToPlayMore());
    }
}
