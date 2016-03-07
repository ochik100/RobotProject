package main;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Kelly on 3/3/16.
 */
public class FileManager {

    public static int size = 0;
    public static Cell[][] cells;

    public static Environment createEnvironment(String filename) {

        String line = null;
        String data = "";

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);

            if((line = br.readLine()) != null)
            {
                size = Integer.valueOf(line);
            }
            while((line = br.readLine()) != null)
            {
                data += line;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        cells = new Cell[size][size];
        Cell initial = null, goal = null;

        int position = 0;
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell.State s = Cell.getStateFromChar(data.charAt(position));
                cells[j][i] = new Cell(s, new CellPosition(j, i));
                if(s.equals(Cell.State.INITIAL)) {
                    initial = cells[j][i];
                } else if(s.equals(Cell.State.GOAL)) {
                    goal = cells[j][i];
                }
                position++;
            }
        }

        //printEnvironment();

        return new Environment(cells, initial, goal);
    }

    public static void printEnvironment() {

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(cells[j][i].state.getValue());
            }
            System.out.println();
        }

        System.out.println();
    }
}
