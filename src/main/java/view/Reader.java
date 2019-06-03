package view;

import model.Board;
import model.Game;
import model.Game.State;
import model.Level1;
import model.Wall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Reader {

    private JFrame frame;
    private int width;
    private int height;
    private BufferedImage mainMenu;
    private BufferedImage wall;
    private List<Wall> walls;
    private Game game;


    public BufferedImage getBlinky() {
        return blinky;
    }

    public BufferedImage getPinky() {
        return pinky;
    }

    public BufferedImage getPokey() {
        return pokey;
    }

    public BufferedImage getInky() {
        return inky;
    }

    private BufferedImage blinky;
    private BufferedImage pinky;
    private BufferedImage pokey;
    private BufferedImage inky;

    public BufferedImage getCoin() {
        return coin;
    }

    public void setCoin(BufferedImage coin) {
        this.coin = coin;
    }

    private BufferedImage coin;


    public BufferedImage getPacman() {
        return pacman;
    }

    private BufferedImage pacman;



    public Reader(JFrame frame) throws FileNotFoundException, IOException {
        this.frame = frame;
        this.width = frame.getWidth();
        this.height = frame.getHeight();
      //  setNewFont();
        loadImages();
    }

    public BufferedImage getWall() {
        return wall;
    }

    public void loadImages() throws IOException {
        this.mainMenu = ImageIO.read(new File("images/menu.png"));
        this.blinky = ImageIO.read(new File("images/red.png"));
        this.pokey = ImageIO.read(new File("images/blue.png"));
        this.pinky = ImageIO.read(new File("images/pinky.png"));
        this.inky = ImageIO.read(new File("images/orange.png"));
        this.pacman = ImageIO.read(new File("images/Pacman.png"));
        this.wall = ImageIO.read(new File("images/wall.jpg"));
        this.coin = ImageIO.read(new File("images/coin.png"));

    }

    public BufferedImage getMainMenu() {
        return mainMenu;
    }

    public void loadGame() throws IOException {

        game = new Game();
        game.board = new Level1(21,21);

        game.createWalls();
        game.createCoins();
        game.createSuperCoins();
        game.createGhosts();
    }

    public State getGameState() {
        return game.currentState;
    }

    public void setFrame(int dimension) {
        setWidth(dimension * 70 + 300);
        setHeight(dimension * 70 + 47);
        frame.setSize(width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public List<Wall> getWalls() {
        return game.board.getWalls();
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public void updateMove(String move) {
      //  KeyStroke key = KeyStroke.getKeyStroke(move);
       // game.processKey(move);
        System.out.println(move);

    }
    public void deleteGame() {
        game = null;
    }

    public Board getBoard(){
        return game.board;
    }
}
