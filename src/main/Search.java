package main;

import java.util.*;

/**
 * Created by Kelly on 3/3/16.
 */
public class Search {

    private Environment environment;
    private Cell initial, goal, current;
    private PriorityQueue<Cell> fringe;
    private LinkedList<Cell> visited, path;

    public Search(Environment environment) {
        this.environment = environment;
        this.initial = environment.getInitial();
        this.current = initial;
        this.goal = environment.getGoal();
        this.visited = new LinkedList<Cell>();
        visited.addLast(current);
        this.path = new LinkedList<Cell>();
        Comparator<Cell> comparator = new CellComparator();
        this.fringe = new PriorityQueue<>(comparator);
    }

    public class CellComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell a, Cell b) {
            if(manhattan(a, goal) > manhattan(b, goal)) {
                return -1;
            }
            if(manhattan(a, goal) < manhattan(b, goal)){
                return 1;
            }
            return 0;
        }
    }

    public int manhattan(Cell current, Cell goal) {
        int value = Math.abs(current.position.X - goal.position.X) + Math.abs(current.position.Y - goal.position.Y);
        return value;
    }

    public void bestFirstSearchManhattan() {

        while(visited.size() != FileManager.size*FileManager.size && current != goal) {

            LinkedList<Cell> neighbors = environment.getNeighbors(current);
            for(Cell cell : neighbors) {

                if(visited.contains(cell)) {
                    continue;
                }
                if(cell.currentState().equals(Cell.State.GOAL)) {
                    path.push(cell);
                    createSolution();
                    return;
                }

                manhattan(cell, goal);
                fringe.add(cell);
            }

            if(fringe.size() > 0 && !path.contains(current)) {
                path.push(current);
            }

            if(fringe.size() == 0) {
                current = path.pop();
                continue;
            }

            if(current == goal)
                break;

            current = fringe.poll();
            visited.addLast(current);

            path.push(current);
            fringe.clear();

        }
        createSolution();

    }

    public LinkedList<Cell> getVisited() {
        return visited;
    }

    public LinkedList<Cell> getPath() {
        return path;
    }

    public void createSolution() {

        LinkedList<Cell> path = getPath();
        for (Cell c : path) {
            if(FileManager.cells[c.position.X][c.position.Y].currentState().equals(Cell.State.OPEN))
                FileManager.cells[c.position.X][c.position.Y].setState(Cell.State.PATH);
        }

    }
}
