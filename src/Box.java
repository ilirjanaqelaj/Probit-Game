public class Box {
    private Player player;
    private Pengesa pengesa;
    private int points;
    private BlackHole blackHole;
    private DangerousPlayer dangerousPlayer;


    public void clearPlayer() {
        player = null;

    }

    public void clearDangerousPlayer() {
        dangerousPlayer = null;

    }

    public void clearPengesaAndBlackHole(){
        pengesa=null;
        blackHole=null;
    }



    public boolean isEmptyPlayer() {
        return player == null;
    }

    public boolean isEmptyPengesa() {
        return pengesa == null;
    }

    public boolean isEmptyBox() {
        return player == null && pengesa == null;
    }

    public boolean isEmptyBlackHole() {
        return blackHole == null;

    }

    public boolean isEmptyDangerousPlayer() {
        return dangerousPlayer == null;
    }


    public Player getPlayer() {
        if (isEmptyPlayer()) {
            return new Player(" ");
        }
        return player;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }


    public void setPengesa(Pengesa pengesa) {
        this.pengesa = pengesa;
    }

    public Pengesa getPengesa() {
        return pengesa;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBlackHole(BlackHole blackHole) {
        this.blackHole = blackHole;
    }

    public void setDangerousPlayer(DangerousPlayer dangerousPlayer) {
        this.dangerousPlayer = dangerousPlayer;
    }


    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        if (!isEmptyPlayer())
            return player.toString();
        else if (!isEmptyBlackHole())
            return blackHole.toString();
        else if (!isEmptyPengesa())
            return pengesa.toString();
        else if (!isEmptyDangerousPlayer())
            return dangerousPlayer.toString();
        else {
            return "" + points;
        }
    }


}