package controller;

import model.Game;
import model.Level1;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.board = new Level1(21, 21);
        game.createWalls();
        game.createGhosts();
        game.createSuperCoins();
        game.createCoins();
        game.drawMap();
        while (true) {

         //   game.input(move);

            game.run();

        }
    //    return;

    }
}