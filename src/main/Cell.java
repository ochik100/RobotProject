package main;

/**
 * Created by Kelly on 3/3/16.
 */
public class Cell {

    public State state;
    public CellPosition position;

    public Cell(State state, CellPosition position) {
        this.state = state;
        this.position = position;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State currentState() {
        return this.state;
    }

    public enum State {
        INITIAL('i'),
        OBSTACLE('+'),
        OPEN('.'),
        PATH('o'),
        GOAL('g');

        State(Character value) {
            this.value = value;
        }

        public final Character value;
        public Character getValue(){return value;}
    }

    public static State getState(Character c) {

        if(c.equals(State.INITIAL.getValue())) {
            return State.INITIAL;
        } else if (c.equals(State.OBSTACLE.getValue())) {
            return State.OBSTACLE;
        } else if (c.equals(State.OPEN.getValue())) {
            return State.OPEN;
        } else if (c.equals(State.PATH.getValue())) {
            return State.PATH;
        } else if (c.equals(State.GOAL.getValue())) {
            return State.GOAL;
        } else {
            return null;
        }
    }

    public static boolean isOpen(Cell cell) {
        if(cell.state.equals(State.OPEN))
            return true;
        else
            return false;
    }

    public static Cell up(Cell cell) {
        if(cell.position.X != 0)
            cell.position.X-=1;
        return cell;
    }

    public static Cell down(Cell cell) {
        if(cell.position.X != FileManager.size-1)
            cell.position.X+=1;
        return cell;
    }

    public static Cell left(Cell cell) {
        if(cell.position.Y != 0)
            cell.position.Y-=1;
        return cell;
    }

    public static Cell right(Cell cell) {
        if(cell.position.Y != FileManager.size-1)
            cell.position.Y+=1;
        return cell;
    }



}
