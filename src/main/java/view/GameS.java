package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameS {

    private JFrame frame;
    private JPanel panelMain;
    private JPanel panelGame;

    private Reader reader;
    private Manager manager;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameS window = new GameS();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the application.
     *
     * @throws IOException
     */
    public GameS() throws IOException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     *
     * @throws IOException
     */
    private void initialize() throws IOException {
        frame = new JFrame();

        frame.setBounds(0, 0, 1018, 747);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        reader = new Reader(frame);
        manager = new Manager();

      /*  panelMain = new Menu(reader, manager);
        panelMain.setBounds(0, 0, 1000, 700);
        frame.getContentPane().add(panelMain);*/
        panelGame = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        panelGame = new BoardS(reader, manager);
        panelGame.setBounds(0, 0, 1070, 770);
        panelGame.setLayout(null);
        frame.getContentPane().add(panelGame);


        manager.setPanels(panelMain, panelGame);
    }
}
