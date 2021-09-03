public class Box {
    private Player player;
    private Pengesa pengesa;
    private int points;
    private BlackHole blackHole;

    public void clear() {
        player = null;
    }

    public boolean isEmptyPlayer() {
        return player == null;
    }

    public boolean isEmptyPengesa() {
        return pengesa==null;
    }

    public boolean isEmptyBox() {
        return player==null && pengesa==null;
    }

    public boolean isEmptyBlackHole() {
        return blackHole == null;

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

    public void setPoints(int points){
        this.points = points;
    }

    public void setBlackHole(BlackHole blackHole){
        this.blackHole = blackHole;
    }


    public int getPoints()
    {
        return points;
    }

    @Override
    public String toString()
    {
        if(!isEmptyPlayer() && isEmptyPengesa() && isEmptyBlackHole())
            return player.toString() ;
        else if(isEmptyPlayer() && !isEmptyPengesa() && isEmptyBlackHole())
            return pengesa.toString();
        else if(isEmptyPlayer() && isEmptyPengesa() && !isEmptyBlackHole())
            return blackHole.toString();
        else {
            return ""+ points;
        }
    }


}