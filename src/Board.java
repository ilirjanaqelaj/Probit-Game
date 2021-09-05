import java.util.Random;

public class Board {
    private Box[][] boxes;
    private Position currentPosition;
    static int ROWS;
    static int COLS;
    public Pengesa currentPengesa;
    private BlackHole currentBlackHole;
    private int userPoints;

    public Board(int rreshti, int shtylla) {
        ROWS = rreshti;
        COLS = shtylla;
        boxes = new Box[ROWS][COLS];
        Random rand=new Random();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boxes[row][col] = new Box();
                boxes[row][col].setPoints(1+rand.nextInt(10));

            }
        }
        currentPosition = new Position();
    }


    public boolean hasEmptyBoxes() {
        for (Box[] rowBox : boxes) {
            for (Box box : rowBox) {
                if (box.isEmptyPlayer() && box.isEmptyPengesa()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean areInsideBounds(int xPosition, int yPosition) {
        return xPosition >= 0 && xPosition < ROWS && yPosition >= 0 && yPosition < COLS;
    }



    public boolean isValidPosition(Position position) {

        return getBox(position).isEmptyPlayer() && getBox(position).isEmptyPengesa();
    }

    public int getPositionPoints(Position position) {
        int positionPoints = 0;
        positionPoints = boxes[position.getX()][position.getY()].getPoints();

        return positionPoints;
    }

    public boolean withBlackHolePosition(Position position){
        return !(getBox(position).isEmptyBlackHole());
    }

    public void reset() {
        for (int rreshti = 0; rreshti < ROWS; rreshti++) {
            for (int shtylla = 0; shtylla < COLS; shtylla++) {
                boxes[rreshti][shtylla].clear();
            }
        }
    }



    public Box getBox(Position pozita) {
        return boxes[pozita.getX()][pozita.getY()];
    }

    public Box getBox(int x, int y) {
        Position temp = new Position(x, y);
        return getBox(temp);
    }

    public void updatePosition(Player player, Position position) {
        getBox(position).setPlayer(player);
        this.currentPosition = position;
         this.userPoints+= getPositionPoints(position);
        boxes[position.getX()][position.getY()].setPoints(0);
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void updatePengesa(Pengesa pengesa, Position position) {
        getBox(position).setPengesa(pengesa);
        this.currentPengesa=pengesa;
    }
    public void updateBlackHole(BlackHole blackHole, Position position) {
        getBox(position).setBlackHole(blackHole);
        this.currentBlackHole=blackHole;
    }



    public Position getPositionFromCommand(char command) {
        Position position=new Position();
        getBox(currentPosition).clear();
        int x = 0;
        int y = 0;
        switch (command) {

            case 'W': {
                x = currentPosition.getX() - 1;
                y = currentPosition.getY();
                break;

            }
            case 'A': {
                x = currentPosition.getX();
                y = currentPosition.getY() - 1;
                break;

            }
            case 'D': {
                x = currentPosition.getX();
                y = currentPosition.getY() + 1;
                break;

            }
            case 'S': {
                x = currentPosition.getX() + 1;
                y = currentPosition.getY();
                break;

            }
        }
        position.setX(x);
        position.setY(y);
        return position;

    }


    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder("");
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Box box = boxes[row][col];
                if (box.isEmptyPlayer() && box.isEmptyPengesa() && box.isEmptyBlackHole()) {
                    boardString.append(box.getPoints()+" points");
                } else {
                    boardString.append(""+box);
                }
                if (col < boxes[row].length ) {
                    boardString.append("|");
                }
            }
            boardString.append("\n");
            if (row < boxes.length ) {
                for(int i=0;i<COLS;i++) {
                    boardString.append("--");
                }
                boardString.append("\n");
            }
        }
        boardString.append("\n");
        return boardString.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return currentPosition.getX() == other.getX() && currentPosition.getX() == other.getY();
    }

}
