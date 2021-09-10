public interface Display {

    void showMessage(String s);

    void update(Board board);

    Position readPosition();

    Position readPosition(Player player);

    boolean wantsToPlayMore();
}