package model;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import view.Lanterna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
        Lanterna lanterna = new Lanterna();
    public Board board;

    public enum State {
        init, game, gameOver, gameWin
    };

    public enum Event {
        newGame, levelUp, collision, win
    };

    public State currentState = State.init;
    public boolean newLevel = false;



    public void updateState(Event evt) {
       // new GhostMove();

        if (evt == Event.newGame)
            currentState = State.game;
        else if (evt == Event.levelUp) {
            newLevel = true;
            setNextBoard();
        } else if (evt == Event.collision)
            currentState = State.gameOver;
        else if (evt == Event.win) {
            currentState = State.gameWin;
            System.exit(0);
        }
    }

    public Game() throws IOException {
/*
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(21, 21)).createTerminal();
        this.screen =new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();*/
        board = new Level1(21, 21);
        createWalls();
        board.createGhosts();
        createSuperCoins();
        createCoins();
        drawMap();
        new GhostMove();


    }


    public void run() throws IOException {
        Screen screen = lanterna.getScreen();

        while (!board.dead()) {

            KeyStroke key;
            if ((key=screen.readInput()) != null) {
                if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') || key.getKeyType() == KeyType.EOF) {
                    screen.close();
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                        screen.close();
                    if (key.getKeyType() == KeyType.EOF)
                        break;
                }
                processKey(key);
                updateGame();
                display();
            }
        }
    }


  private void processKey(KeyStroke key){
        board.processKey(key);
    }

    public class GhostMove {
        java.util.Timer timer;

        public GhostMove() {
            timer = new Timer();
            timer.schedule(new RemindTask(), 0, //initial delay
                    1 * 500); //subsequent rate
        }

        class RemindTask extends TimerTask {

            public void run() {
                board.moveGhosts();
                try {
                    drawMap();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public void updateGame(){
        if(board.checkMaze()) updateState(Game.Event.levelUp);
      //  ghostMove.updateTimer();
    }

    public void setNextBoard() {

        if (board.nextBoard() != null) {
            board = board.nextBoard();

        } else
            updateState(Event.win);
    }
    public void setCurrentBoard(Board board) {
        board = board;
    }



    public void display() throws IOException {
        this.drawMap();

        if (currentState == State.gameWin)
            System.out.print("-- YOU WON --");
        else if (newLevel) {
            System.out.print("-- LEVEL UP --\n");
            newLevel = false;
        } else if (currentState == State.gameOver)
            System.out.print("-- YOU LOST --\n");
    }

    public void createCoins() {
        boolean can = true;
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 1; i < board.getWidth()- 1; i++) {
            for (int j = 1; j < board.getHeight() - 1; j++) {
                Position p = new Position(i, j);
                if (p.getY() != board.getHeight() / 2 - 1 || p.getX() == board.getWidth() / 4 || p.getX() == board.getWidth() - board.getWidth() / 4 - 1)
                    if ((j != board.getHeight() / 2 - 2 && j != board.getHeight() / 2 && j != board.getHeight() / 2 + 1 && j != board.getHeight() / 2 - 3) || p.getX() == board.getWidth() / 4 || p.getX() == board.getWidth() - board.getWidth() / 4 - 1) {
                        for (Wall wall : board.getWalls())
                            if (wall.getPosition().equals(p)) can = false;
                        if (board.getPacman().getPosition().equals(p)) can = false;

                        if (can)
                            coins.add(new Coin(i, j));
                        can = true;
                    }
            }
        }
        board.setCoins(coins);
    }


    public void createSuperCoins() {
        ArrayList<SuperCoin> superCoins = new ArrayList<>();

        superCoins.add(new SuperCoin(2, 3));
        superCoins.add(new SuperCoin(board.getWidth() - 3, 3));
        superCoins.add(new SuperCoin(board.getWidth() - 3, board.getHeight() - 4));
        superCoins.add(new SuperCoin(2, board.getHeight() - 4));

        this.board.setSuperCoins(superCoins);
    }

    public void createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 1; c < board.getWidth() - 1; c++) {
            walls.add(new Wall(c, 1));
            walls.add(new Wall(c, board.getHeight() - 2));
        }

        for (int r = 1; r < (board.getHeight() / 2) - 3; r++) {
            walls.add(new Wall(1, r));
            walls.add(new Wall(board.getWidth() - 2, r));
        }

        for (int r = (board.getHeight() / 2) + 2 + 1; r < board.getHeight() - 1; r++) {
            walls.add(new Wall(1, r - 1));
            walls.add(new Wall(board.getWidth() - 2, r - 1));
        }

        for (int c = 2; c < 3; c++) {
            walls.add(new Wall(c, board.getHeight() - (board.getHeight() / 4) - 1));
            walls.add(new Wall(c + board.getWidth() - 5, board.getHeight() - (board.getHeight() / 4) - 1));
        }

        for (int c = 3; c < 5; c++) {
            walls.add(new Wall(c, board.getHeight() - (board.getHeight() / 4) - 3));
            walls.add(new Wall(c + board.getWidth() - 8, board.getHeight() - (board.getHeight() / 4) - 3));
        }

        for (int c = 6; c < 9; c++) {
            walls.add(new Wall(c, board.getHeight() - (board.getHeight() / 4) - 3));
            walls.add(new Wall(c + board.getWidth() - 15, board.getHeight() - (board.getHeight() / 4) - 3));
        }

        for (int c = 3; c < 9; c++) {
            walls.add(new Wall(c, board.getHeight() - 4));
            walls.add(new Wall(c + board.getWidth() - ((board.getWidth()) / 2) - 2, board.getHeight() - 4));
        }

        for (int r = board.getHeight() - 5; r < board.getHeight() - 3; r++) {
            walls.add(new Wall(board.getWidth() / 2, r));
            walls.add(new Wall(board.getWidth() / 2, r - 4));
            walls.add(new Wall(board.getWidth() / 4 + 1, r - 1));
            walls.add(new Wall(board.getWidth() - 7, r - 1));
            walls.add(new Wall(board.getWidth() - 5, r - 2));
            walls.add(new Wall(board.getWidth() / 4 - 1, r - 2));
            walls.add(new Wall(board.getWidth() / 4 + 1, r - 6));
            walls.add(new Wall(board.getWidth() - 7, r - 6));
        }

        for (int r = 5; r < 9; r++) {
            walls.add(new Wall(board.getWidth() / 4 + 1, r));
            walls.add(new Wall(board.getWidth() - 7, r));
        }

        for (int c = board.getWidth() / 4 + 1; c < board.getWidth() / 4 + 4; c++) {
            walls.add(new Wall(c, 7));
            walls.add(new Wall(c + 6, 7));
        }

        for (int c = board.getWidth() / 4 + 1; c < board.getWidth() / 4 + 6; c++) {
            walls.add(new Wall(c + 2, 5));
        }

        for (int c = (board.getWidth()) / 2 - 2; c < (board.getWidth()) / 2 + 3; c++) {
            walls.add(new Wall(c, board.getHeight() - 6));
            walls.add(new Wall(c, board.getHeight() - 10));
        }

        for (int c = 3; c < 5; c++) {
            walls.add(new Wall(c, 3));
            walls.add(new Wall(c + board.getWidth() - 8, 3));
            walls.add(new Wall(c, 5));
            walls.add(new Wall(c + board.getWidth() - 8, 5));
        }

        for (int c = 6; c < 9; c++) {
            walls.add(new Wall(c, 3));
            walls.add(new Wall(c + board.getWidth() - 15, 3));
        }

        for (int c = 2; c < 4; c++) {
            walls.add(new Wall(board.getWidth() / 2, c));
            walls.add(new Wall(board.getWidth() / 2, c + 4));
        }

        for (int c = 1; c < 5; c++) {
            walls.add(new Wall(c, 7));
            walls.add(new Wall(c + board.getWidth() - 6, 7));
            walls.add(new Wall(c, 8));
            walls.add(new Wall(c + board.getWidth() - 6, 8));
            walls.add(new Wall(c, 11));
            walls.add(new Wall(c + board.getWidth() - 6, 11));
            walls.add(new Wall(c, 10));
            walls.add(new Wall(c + board.getWidth() - 6, 10));
        }


        this.board.setWalls(walls);
    }

    public void createGhosts() {

        ArrayList<Ghost> ghosts = new ArrayList<>();

        ghosts.add(new Blinky(9, 10));
        ghosts.add(new Pinky(10, 10));
        ghosts.add(new Inky(11, 10));
        ghosts.add(new Pokey(10, 9));

        this.board.setGhosts(ghosts);
       // ghostMove = new GhostMove();
    }

    public void drawMap() throws IOException {
        lanterna.draw(board.getPacman(),board.getWalls(),board.getCoins(),board.getSuperCoins(), board.getGhosts(), board.getMode());

    }

}
