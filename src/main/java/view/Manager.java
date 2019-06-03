package view;

import javax.swing.*;

public class Manager {

    // State Machine
    public enum State {
        mainPanel, gamePanel
    };

    public enum Event {
        newGame, gameOver
    };

    public State state;
    private JPanel pm;
    private JPanel pg;

    public Manager() {
        state = State.mainPanel;
    }

    public void setPanels(JPanel pm, JPanel pg) {
        this.pm = pm;
        this.pg = pg;
    }

    public void updateState(Event evt) {
        if (evt == Event.newGame) {
            state = State.gamePanel;
            pm.setVisible(false);
            pg.setVisible(true);
            pg.setFocusable(true);
            pg.requestFocusInWindow();
        } else if (evt == Event.gameOver) {
            state = State.mainPanel;
            pm.setVisible(true);
            pg.setVisible(false);
        }
    }
}
