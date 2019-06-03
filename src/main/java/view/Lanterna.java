package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.*;

import java.io.IOException;
import java.util.List;

public class Lanterna {
    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    private Screen screen;
    private TextGraphics graphics;

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public Lanterna() throws IOException{

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(21, 21)).createTerminal();
        this.screen =new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
     }

    public void draw(Pacman pacman, List<Wall>walls, List<Coin>coins, List<SuperCoin>superCoins, List<Ghost> ghosts, int mode) throws IOException{
        screen.clear();
        graphics=screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(21, 21), ' ');

        drawPacman(graphics,0,pacman);

        for (Wall wall : walls) drawWall(graphics,0,wall);
        for (Coin coin : coins) drawCoin(graphics,0,coin);
        for (SuperCoin superCoin : superCoins) drawSuperCoin(graphics,mode, superCoin);
        for (Ghost ghost : ghosts) drawGhosts(graphics, mode,ghost);
        screen.refresh();
    }

    public void drawPacman(TextGraphics graphics, int mode, Pacman pacman) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(pacman.getPosition().getX(), pacman.getPosition().getY()), "♥");
    }

    public void drawWall(TextGraphics graphics, int mode, Wall wall) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#0f13ff"));
        graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), " ");
    }

    public void drawSuperCoin(TextGraphics graphics, int mode, SuperCoin superCoin) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(new TerminalPosition(superCoin.getPosition().getX(), superCoin.getPosition().getY()), "0");
    }

    public void drawCoin(TextGraphics graphics, int mode, Coin coin) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(new TerminalPosition(coin.getPosition().getX(), coin.getPosition().getY()), "*");
    }

    public void drawGhosts(TextGraphics graphics, int mode, Ghost ghost) {
        if(mode == 0) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(ghost.getPosition().getX(), ghost.getPosition().getY()), "↥");
        }
        if(mode==1){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(ghost.getPosition().getX(), ghost.getPosition().getY()), "↥");
        }
    }





}
