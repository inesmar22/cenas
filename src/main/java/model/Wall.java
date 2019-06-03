package model;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}