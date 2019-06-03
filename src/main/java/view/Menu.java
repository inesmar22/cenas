package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu extends JPanel{
    private Reader reader;
    private Manager manager;
    private JDialog settings;

    /**
     * Create the panel.
     *
     * @throws IOException
     * @throws FontFormatException
     * @throws FileNotFoundException
     *
     */
    public Menu(Reader read, Manager manager)throws FileNotFoundException, IOException {

        setVisible(true);
        reader = read;
        manager = manager;
        //this.settings = new DialogSettings(reader, manager);
        setLayout(null);

        initializeButtons();
    }

    public void initializeButtons() {
        buttonNewGame();
        buttonExit();
    }

    public void buttonNewGame() {
        JButton newGameB = new JButton("New Game");
       // newGameB.setFont(dataBase.getCustomFont());
        newGameB.setBounds(400, 330, 200, 50);
        newGameB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                manager.updateState(Manager.Event.newGame);
            }
        });
        add(newGameB);
    }

    public void buttonExit() {
        JButton btnExit = new JButton("Exit");
        //btnExit.setFont(dataBase.getCustomFont());
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        btnExit.setBounds(400, 440, 200, 50);
        add(btnExit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
     //   g.drawImage(reader.getMainMenu(), 0, 0, this);
    }


}
