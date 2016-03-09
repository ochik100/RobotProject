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
            /*if(manhattan(a, goal) > manhattan(b, goal)) {
                return -1;
            }
            if(manhattan(a, goal) < manhattan(b, goal)){
                return 1;
            }*/
            if(a.cost > b.cost) {
                return 1;
            }
            if(a.cost < b.cost){
                return -1;
            }
            return 0;
        }
    }

    public double euclidean(Cell current, Cell goal) {
        //double value = Math.sqrt((current.position.X - goal.position.X)^2 + (current.position.Y - goal.position.Y)^2);
        double value = Math.sqrt(Math.pow(current.position.X - goal.position.X, 2) + Math.pow(current.position.Y - goal.position.Y, 2));
        current.setCost(value);
        return value;
    }

    public double manhattan(Cell current, Cell goal) {
        double value = Math.abs(current.position.X - goal.position.X) + Math.abs(current.position.Y - goal.position.Y);
        current.setCost(value);
        return value;
    }

    public double euclideanAndCost(Cell current, Cell goa) {
        double value = euclidean(current, goa) + Double.valueOf(path.size());
        current.setCost(value);
        return value;
    }

    public double manhattanAndCost(Cell current, Cell goal) {
        double value = manhattan(current, goal) + Double.valueOf(path.size());
        current.setCost(value);
        return value;
    }

    public double evaluationFunctions(int function, Cell current, Cell goal) {

        if(function == 1) {
            return euclidean(current, goal);
        }
        else if(function == 2) {
            return manhattan(current, goal);
        } else if(function == 3) {
            return  euclideanAndCost(current, goal);
        } else if(function == 4) {
            return manhattanAndCost(current, goal);
        }
        return 0;
    }

    public void bestFirstSearch(int function) {

        while(visited.size() != FileManager.size*FileManager.size && current != goal) {

            LinkedList<Cell> neighbors = environment.getNeighbors(current);
            for(Cell cell : neighbors) {

                if(visited.contains(cell)) {
                    continue;
                }
                if(cell.getState().equals(Cell.State.GOAL)) {
                    path.push(cell);
                    createSolution();
                    return;
                }

                //manhattan(cell, goal);
                evaluationFunctions(function, cell, goal);
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

    public void bestFirstSearch2(int function) {

        fringe.add(current);
        while(!fringe.isEmpty() && (current != goal)) {

            current = fringe.poll();
            path.push(current);
            LinkedList<Cell> neighbors = environment.getNeighbors(current);
            if(neighbors.isEmpty()) {
                path.pop();
            }
            for(Cell cell : neighbors) {

                if(visited.contains(cell)) {
                    continue;
                }

                evaluationFunctions(function, cell, goal);
                fringe.add(cell);
            }

            visited.addLast(current);

        }
        createSolution();

    }

    public Cell bestFirstSearch3(int function) {

        fringe.add(current);
        while(!fringe.isEmpty()) {

            current = fringe.poll();
            if(current == goal)
                return current;
            LinkedList<Cell> neighbors = environment.getNeighbors(current);

            for(Cell cell : neighbors) {

                if(visited.contains(cell)) {
                    continue;
                }

                cell.setParent(current);
                evaluationFunctions(function, cell, goal);
                fringe.add(cell);
            }

            visited.addLast(current);

        }

        return current;
        //createSolution();

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
            if(FileManager.cells[c.position.X][c.position.Y].getState().equals(Cell.State.OPEN))
                FileManager.cells[c.position.X][c.position.Y].setState(Cell.State.PATH);
        }

    }

    public void createSolution2(Cell goal) {
        Cell parent = goal.getParent();
        while(parent.getState() != Cell.State.INITIAL) {
            //System.out.println(parent.position.X + ":" + parent.position.Y);
            if(FileManager.cells[parent.position.X][parent.position.Y].getState().equals(Cell.State.OPEN))
                FileManager.cells[parent.position.X][parent.position.Y].setState(Cell.State.PATH);
            parent = parent.getParent();
        }
    }

}
