package model;

public class Level2 extends Board{
    public Level2(int width, int height) {
        super(width, height);
        pacman = new Pacman(10,14);
        createWalls();
        createCoins();
        createSuperCoins();
        createGhosts();
        new Reminder(10);

    }
}
