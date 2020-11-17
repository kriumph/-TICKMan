package stickman.memento;

public class CareTaker {
    private levelMemento levelMemento;

    //empty constructor for caretaker
    public CareTaker(){

    }

    //getter for memento
    public levelMemento getLevelMemento() {return this.levelMemento;}

    //setter for memento
    public void setLevelMemento(levelMemento levelMemento) {this.levelMemento = levelMemento;}
}
