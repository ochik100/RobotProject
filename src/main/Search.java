package main;

import java.util.*;

/**
 * Created by Kelly on 3/3/16.
 */
public class Search {

    private Environment environment;
    private Cell initial, goal, current;
    private PriorityQueue<Cell> fringe;
    private LinkedList<Cell> visited;
    private int path = 0;

    public Search(Environment environment) {
        this.environment = environment;
        this.initial = environment.getInitial();
        this.current = initial;
        this.goal = environment.getGoal();
        this.visited = new LinkedList<Cell>();
        visited.addLast(current);
        Comparator<Cell> comparator = new CellComparator();
        this.fringe = new PriorityQueue<>(comparator);
    }

    public class CellComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell a, Cell b) {

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
        double value = Math.sqrt(Math.pow(current.position.X - goal.position.X, 2) + Math.pow(current.position.Y - goal.position.Y, 2));
        current.setCost(value);
        return value;
    }

    public double manhattan(Cell current, Cell goal) {
        double value = Math.abs(current.position.X - goal.position.X) + Math.abs(current.position.Y - goal.position.Y);
        current.setCost(value);
        return value;
    }

    public double euclideanAndCost(Cell current, Cell goal) {
        double value = euclidean(current, goal) + 1;
        current.setCost(value);
        return value;
    }

    public double manhattanAndCost(Cell current, Cell goal) {
        double value = manhattan(current, goal) + 1;
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

    public Cell bestFirstSearch(int function) {

        fringe.add(current);
        while(!fringe.isEmpty()) {

            current = fringe.poll();
            path++;

            if(current == goal) {
                return current;
            }
            LinkedList<Cell> neighbors = environment.getNeighbors(current);

            for(Cell cell : neighbors) {

                if(visited.contains(cell)) {
                    continue;
                }

                cell.setParent(current);
                evaluationFunctions(function, cell, goal);
                fringe.add(cell);
            }

            visited.add(current);

        }

        return current;

    }


    public int getVisitedSize() {
        return visited.size();
    }

    public double createSolution(Cell goal, int function) {

        Cell parent = goal.getParent();
        Double cost = 0.0;
        int count = 1;
        while(parent.getState() != Cell.State.INITIAL) {
            if(FileManager.cells[parent.position.X][parent.position.Y].getState().equals(Cell.State.OPEN))
                FileManager.cells[parent.position.X][parent.position.Y].setState(Cell.State.PATH);
            cost = cost + parent.getCost();
            parent = parent.getParent();
            count++;
        }
        /*if(function == 2)
            return count;
        else if(function == 3 || function == 4)
            return cost + count;
        else
            return cost;*/
        return cost;
    }

}
