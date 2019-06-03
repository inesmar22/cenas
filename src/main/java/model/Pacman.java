package model;

public class Pacman extends Element{
    public Pacman(int x, int y) {
        super(x,y);
    }

    public Position moveUp(){
        return new Position(this.getPosition().getX(),this.getPosition().getY()-1);
    }
    public Position moveRight(){
        int x = this.getPosition().getX();
        if(x==21) {
            return new Position(1, this.getPosition().getY());
        }
        else

            return new Position(this.getPosition().getX()+1,this.getPosition().getY());
    }
    public Position moveDown(){
        return new Position(this.getPosition().getX(),this.getPosition().getY()+1);
    }

    public Position moveLeft(){
        int x = this.getPosition().getX();
        if(x==0) {
            return new Position(20, this.getPosition().getY());
        }
        else
            return new Position(this.getPosition().getX()-1,this.getPosition().getY());
    }


}
