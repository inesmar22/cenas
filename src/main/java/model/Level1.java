package model;

import java.util.List;
import java.util.TimerTask;

public class Level1 extends Board {

    int mode = this.getMode();
    List<Ghost> ghosts = this.getGhosts();

    public Level1(int width, int height){
        super(width, height);
        Pacman pac = new Pacman(10,14);
        setPacman(pac);
    }

    public Board nextBoard() {

        Board board;
        board = new Level2(21,21);
        return board;
    }


    public class Reminder {
        java.util.Timer timer;

        public Reminder(int seconds) {
            timer = new java.util.Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
        }

        class RemindTask extends TimerTask {
            public void run() {

                mode = 0;
                for(Ghost ghost : ghosts){
                    ghost.setEdible(false);
                }
                timer.cancel(); //Terminate the timer thread
            }
        }
    }



}
