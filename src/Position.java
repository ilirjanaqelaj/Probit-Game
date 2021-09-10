import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position() {
        x = -1;
        y = -1;
    }

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinateFromPosition(int position) {
        this.x = position / Board.ROWS;
        this.y = position % Board.COLS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}

