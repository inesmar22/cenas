package model;

import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public abstract class Ghost extends Element implements ActionListener {

    public boolean isEdible() {
        return edible;
    }

    private boolean edible;
    Timer animationTimer;
    int animationDelay = 80;

    public Ghost(int x, int y) {
        super(x, y);
        edible=false;
        startAnimation();
    }

    public abstract void draw(TextGraphics graphics, int mode);



    public Position move() {
        switch (new Random().nextInt(4)) {
            case 0:
                return new Position(position.getX(), position.getY() - 1);
            case 1:
                return new Position(position.getX() + 1, position.getY());
            case 2:
                return new Position(position.getX(), position.getY() + 1);
            case 3:
                return new Position(position.getX() - 1, position.getY());
        }
        return new Position(position.getX(), position.getY());
    }

    public void setEdible(boolean edible){
        this.edible=edible;
        this.setPosition(new Position(9, 10));
    }

    public void startAnimation() {
        if (animationTimer == null) {
            animationTimer = new Timer(animationDelay, this); // *** error ***
            animationTimer.start();
        } else if (!animationTimer.isRunning())
            animationTimer.restart();
    }

    public void stopAnimation() {
        animationTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
