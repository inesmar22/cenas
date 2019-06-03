package model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pokey extends Ghost {
    public Pokey(int x, int y) {
        super(x, y);
    }


    public void draw(TextGraphics graphics, int mode) {
        if(mode == 0) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "↥");
        }
        if(mode==1){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "↥");
        }
        if(isEdible()){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "D");
        }
    }

}