package main;

import java.util.LinkedList;

/**
 * Created by Kelly on 3/3/16.
 */
public class Environment {

    private Cell[][] cells;
    private Cell initial, goal, currentCell;

    public Environment(Cell[][] cells, Cell initial, Cell goal) {
        this.cells = cells;
        this.initial = initial;
        this.goal = goal;
    }

    public LinkedList<Cell> getNeighbors(Cell currentCell)
    {
        this.currentCell = currentCell;
        LinkedList<Cell> neighbors = new LinkedList<Cell>();

        int X = currentCell.position.X;
        int Y = currentCell.position.Y;

        // up
        if((Y != 0) && (cells[X][Y-1].state.equals(Cell.State.OPEN)
                || cells[X][Y-1].state.equals(Cell.State.GOAL))) {
            neighbors.add(cells[X][Y-1]);
        }

        // down
        if((Y != FileManager.size-1) && (cells[X][Y+1].state.equals(Cell.State.OPEN)
                || cells[X][Y+1].state.equals(Cell.State.GOAL))) {
            neighbors.add(cells[X][Y+1]);
        }

        // left
        if((X != 0) && ((cells[X-1][Y].state.equals(Cell.State.OPEN))
                || cells[X-1][Y].state.equals(Cell.State.GOAL))) {
            neighbors.add(cells[X-1][Y]);
        }

        // right
        if((X != FileManager.size-1) && (cells[X+1][Y].state.equals(Cell.State.OPEN)
                || cells[X+1][Y].state.equals(Cell.State.GOAL))) {
            neighbors.add(cells[X+1][Y]);
        }

        return neighbors;
    }

    public CellPosition getPosition()
    {
        return currentCell.position;
    }

    public Cell getInitial() {
        return initial;
    }

    public Cell getGoal() {
        return goal;
    }




}
