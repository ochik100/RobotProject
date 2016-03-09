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
        Cell initial = null, goal = null;

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);

            if((line = br.readLine()) != null)
            {
                size = Integer.valueOf(line);
            }
            while((line = br.readLine()) != null)
            {
                cells = new Cell[size][size];

                int position = 0;
                for(int i = 0; i < size;) {
                    for (int j = 0; j < size; j++) {
                        Cell.State s = Cell.getStateFromChar(line.charAt(position));
                        cells[j][i] = new Cell(s, new CellPosition(j, i));
                        if(s.equals(Cell.State.INITIAL)) {
                            initial = cells[j][i];
                        } else if(s.equals(Cell.State.GOAL)) {
                            goal = cells[j][i];
                        }
                        position++;
                        if(position == size) {
                            position = 0;
                            i++;
                            line = br.readLine();
                        }
                    }
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

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
