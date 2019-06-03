package model;

import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public abstract class Board {

    private int width;
    private int height;
    protected Pacman pacman;
    private List<Wall> walls;
    private List<Ghost> ghosts;
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<SuperCoin> getSuperCoins() {
        return superCoins;
    }

    public void setSuperCoins(List<SuperCoin> superCoins) {
        this.superCoins = superCoins;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public int getPacLives() {
        return pacLives;
    }

    public void setPacLives(int pacLives) {
        this.pacLives = pacLives;
    }

    private List<Coin> coins;
    private List<SuperCoin> superCoins;

    private int mode;
    private boolean dying = false;
    private int pacLives;

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public List<Wall> getWalls() {
        return walls;
    }
    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public Board(int width, int height){
        this.width = width;
        this.height = height;

        pacLives = 3;
        mode = 0;

        this.setPacman(pacman);
    }


    public void processKey(KeyStroke key){
        System.out.println(key);

        switch (key.getKeyType()) {
            case ArrowUp:
                movePacman(pacman.moveUp());
                break;
            case ArrowRight:
                movePacman(pacman.moveRight());
                break;
            case ArrowDown:
                movePacman(pacman.moveDown());
                break;
            case ArrowLeft:
                movePacman(pacman.moveLeft());
                break;
            default:
                break;
        }
        moveGhosts();
        retrieveCoins();
        verifyGhostCollisions();
    }

    public void movePacman(Position position){

            if (canPacmanMove(position)) pacman.setPosition(position);
        }

    public void moveGhosts() {
        for (Ghost ghost : ghosts) {
            Position monsterPosition = ghost.move();
            if (canMonsterMove(monsterPosition))
                ghost.setPosition(monsterPosition);
        }
        verifyGhostCollisions();
    }

    private boolean canMonsterMove(Position position) {
        int n =0;
        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;
        for (Ghost ghost : ghosts)
            if (ghost.getPosition().equals(position)) n++;
            if(n > 1) return false;

        return true;
    }

    private boolean canPacmanMove(Position position) {
        if (position.getY() < 0) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        return true;
    }

    public boolean dead() {
        return false;
    }

    public void verifyGhostCollisions() {
        for (Ghost ghost : ghosts) {
            if (pacman.getPosition().equals(ghost.getPosition()) && mode == 0) {
                pacman.setPosition(new Position(10, 14));
                dying = true;
                death();
            }
            if (pacman.getPosition().equals(ghost.getPosition()) && mode == 1) {
                ghost.setEdible(true);
                break;
            }
        }
    }

    private void retrieveCoins() {
        for (Coin coin : coins)
            if (pacman.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        for (SuperCoin superCoin : superCoins)
            if (pacman.getPosition().equals(superCoin.getPosition())) {
                superCoins.remove(superCoin);
                mode = 1;
                new Reminder(5);
                break;
            }
    }


    public void death() {
        pacLives--;
        System.out.println(pacLives);
        if (pacLives == 0) {
            System.out.println("You died!");
            System.exit(0);
        } else {
            dying=false;
            return;

        }
    }



    public Board nextBoard() {
        return null;
    }

    public boolean checkMaze(){
        System.out.println(this.getCoins().size());
        if(this.getCoins().size()==0 && this.getSuperCoins().size()==0)
            return true;
        else return false;
    }


    public void createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 1; c < this.getWidth() - 1; c++) {
            walls.add(new Wall(c, 1));
            walls.add(new Wall(c, this.getHeight() - 2));
        }

        for (int r = 1; r < (this.getHeight() / 2) - 3; r++) {
            walls.add(new Wall(1, r));
            walls.add(new Wall(this.getWidth() - 2, r));
        }

        for (int r = (this.getHeight() / 2) + 2 + 1; r < this.getHeight() - 1; r++) {
            walls.add(new Wall(1, r - 1));
            walls.add(new Wall(this.getWidth() - 2, r - 1));
        }

        for (int c = 2; c < 3; c++) {
            walls.add(new Wall(c, this.getHeight() - (this.getHeight() / 4) - 1));
            walls.add(new Wall(c + this.getWidth() - 5, this.getHeight() - (this.getHeight() / 4) - 1));
        }

        for (int c = 3; c < 5; c++) {
            walls.add(new Wall(c, this.getHeight() - (this.getHeight() / 4) - 3));
            walls.add(new Wall(c + this.getWidth() - 8, this.getHeight() - (this.getHeight() / 4) - 3));
        }

        for (int c = 6; c < 9; c++) {
            walls.add(new Wall(c, this.getHeight() - (this.getHeight() / 4) - 3));
            walls.add(new Wall(c + this.getWidth() - 15, this.getHeight() - (this.getHeight() / 4) - 3));
        }

        for (int c = 3; c < 9; c++) {
            walls.add(new Wall(c, this.getHeight() - 4));
            walls.add(new Wall(c + this.getWidth() - ((this.getWidth()) / 2) - 2, this.getHeight() - 4));
        }

        for (int r = this.getHeight() - 5; r < this.getHeight() - 3; r++) {
            walls.add(new Wall(this.getWidth() / 2, r));
            walls.add(new Wall(this.getWidth() / 2, r - 4));
            walls.add(new Wall(this.getWidth() / 4 + 1, r - 1));
            walls.add(new Wall(this.getWidth() - 7, r - 1));
            walls.add(new Wall(this.getWidth() - 5, r - 2));
            walls.add(new Wall(this.getWidth() / 4 - 1, r - 2));
            walls.add(new Wall(this.getWidth() / 4 + 1, r - 6));
            walls.add(new Wall(this.getWidth() - 7, r - 6));
        }

        for (int r = 5; r < 9; r++) {
            walls.add(new Wall(this.getWidth() / 4 + 1, r));
            walls.add(new Wall(this.getWidth() - 7, r));
        }

        for (int c = this.getWidth() / 4 + 1; c < this.getWidth() / 4 + 4; c++) {
            walls.add(new Wall(c, 7));
            walls.add(new Wall(c + 6, 7));
        }

        for (int c = this.getWidth() / 4 + 1; c < this.getWidth() / 4 + 6; c++) {
            walls.add(new Wall(c + 2, 5));
        }

        for (int c = (this.getWidth()) / 2 - 2; c < (this.getWidth()) / 2 + 3; c++) {
            walls.add(new Wall(c, this.getHeight() - 6));
            walls.add(new Wall(c, this.getHeight() - 10));
        }

        for (int c = 3; c < 5; c++) {
            walls.add(new Wall(c, 3));
            walls.add(new Wall(c + this.getWidth() - 8, 3));
            walls.add(new Wall(c, 5));
            walls.add(new Wall(c + this.getWidth() - 8, 5));
        }

        for (int c = 6; c < 9; c++) {
            walls.add(new Wall(c, 3));
            walls.add(new Wall(c + this.getWidth() - 15, 3));
        }

        for (int c = 2; c < 4; c++) {
            walls.add(new Wall(this.getWidth() / 2, c));
            walls.add(new Wall(this.getWidth() / 2, c + 4));
        }

        for (int c = 1; c < 5; c++) {
            walls.add(new Wall(c, 7));
            walls.add(new Wall(c + this.getWidth() - 6, 7));
            walls.add(new Wall(c, 8));
            walls.add(new Wall(c + this.getWidth() - 6, 8));
            walls.add(new Wall(c, 11));
            walls.add(new Wall(c + this.getWidth() - 6, 11));
            walls.add(new Wall(c, 10));
            walls.add(new Wall(c + this.getWidth() - 6, 10));
        }


        this.setWalls(walls);
    }

    public void createSuperCoins() {
        ArrayList<SuperCoin> superCoins = new ArrayList<>();

        superCoins.add(new SuperCoin(2, 3));
        superCoins.add(new SuperCoin(getWidth() - 3, 3));
        superCoins.add(new SuperCoin(getWidth() - 3, getHeight() - 4));
        superCoins.add(new SuperCoin(2,getHeight() - 4));

        this.setSuperCoins(superCoins);
    }


    public void createGhosts() {

        ArrayList<Ghost> ghosts = new ArrayList<>();

        ghosts.add(new Blinky(9, 10));
        ghosts.add(new Pinky(10, 10));
        ghosts.add(new Inky(11, 10));
        ghosts.add(new Pokey(10, 9));

        this.setGhosts(ghosts);
        // ghostMove = new GhostMove();
    }

    public void createCoins() {
        boolean can = true;
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 1; i < this.getWidth()- 1; i++) {
            for (int j = 1; j < this.getHeight() - 1; j++) {
                Position p = new Position(i, j);
                if (p.getY() != this.getHeight() / 2 - 1 || p.getX() == this.getWidth() / 4 || p.getX() == this.getWidth() - this.getWidth() / 4 - 1)
                    if ((j != this.getHeight() / 2 - 2 && j != this.getHeight() / 2 && j != this.getHeight() / 2 + 1 && j != this.getHeight() / 2 - 3) || p.getX() == this.getWidth() / 4 || p.getX() == this.getWidth() - this.getWidth() / 4 - 1) {
                        for (Wall wall : this.getWalls())
                            if (wall.getPosition().equals(p)) can = false;
                        if (this.getPacman().getPosition().equals(p)) can = false;

                        if (can)
                            coins.add(new Coin(i, j));
                        can = true;
                    }
            }
        }
        this.setCoins(coins);
    }



}
