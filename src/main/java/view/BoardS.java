package view;

import model.Coin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;




public class BoardS extends JPanel implements KeyListener {

    private Reader dataBase;
    private Manager panelManager;


    /**
     * Create the panel.
     *
     * @throws IOException
     */
    public BoardS(Reader data, Manager manager) throws IOException {
        setVisible(true);
        dataBase = data;
        panelManager = manager;
        //addKeyListener(this);
        setLayout(null);


        dataBase.loadGame();

    }


    private char[][] lev1Map = {
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', '.', '.', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', '.', 'X', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', 'X', ' ', 'X', 'X', ' ', 'X'},
            {'X', '.', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', '.', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', ' ', 'X'},
            {'X', '.', '.', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'X'},
            {'X', 'X', 'X', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', 'X', ' ', 'X', 'X', 'X', 'X'},
            {' ', ' ', ' ', 'X', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X', ' ', ' ', ' '},
            {'X', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', 'X'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', 'X'},
            {' ', ' ', ' ', 'X', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X', ' ', ' ', ' '},
            {'X', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', 'X', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', 'X', ' ', 'X', 'X', ' ', 'X'},
            {'X', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'},
            {'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', 'X'},
            {'X', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', 'X', 'X', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'X', 'X', 'X', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    };



    public void processKeys(String move) throws IOException {

        dataBase.updateMove(move);
        repaint();

    }


    @Override
    public void paintComponent(Graphics g) {
   //     updateDisplay();
        super.paintComponent(g);
    //    System.out.println("PRINT");
/*
                for (Wall wall : dataBase.getWalls())
                    g.drawImage(dataBase.getWall(), wall.getPosition().getX() * 70, wall.getPosition().getY() * 70, this);
*/
        for (int i = 0; i < lev1Map.length; i++) {
            for (int j = 0; j < lev1Map[0].length; j++) {

                if (this.lev1Map [i][j] == 'X')
                    g.drawImage(dataBase.getWall(), j * 20, i * 20, this);
                else if (this.lev1Map [i][j] == '.')
                    g.drawImage(dataBase.getCoin(), j * 70, i * 70, this);
            }
        }

    paintCharacters(g);
    }

    public void paintCharacters(Graphics g) {
        paintPacman(g);
    }

    public void paintPacman(Graphics g) {
        System.out.println("PACMAN HERE");
            g.drawImage(dataBase.getPacman(), dataBase.getBoard().getPacman().getPosition().getX() * 20,
                    dataBase.getBoard().getPacman().getPosition().getY() * 20, this);
    }

    public void paintCoin(Graphics g) {
        System.out.println("PACMAN HERE");
        for (Coin coin : dataBase.getBoard().getCoins())
        g.drawImage(dataBase.getCoin(), coin.getPosition().getX() * 20,
                coin.getPosition().getY() * 20, this);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
               // processKeys("KeyEvent.VK_LEFT");
                break;
            case KeyEvent.VK_RIGHT:
             //   processKeys("KeyEvent.VK_RIGHT");
                break;
            case KeyEvent.VK_UP:
             //   processKeys("KeyEvent.VK_UP");
                break;
            case KeyEvent.VK_DOWN:
            //    processKeys("KeyEvent.VK_DOWN");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}