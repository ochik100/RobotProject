package main;

import com.sun.tools.doclint.Env;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];

        Environment environment1 = FileManager.createEnvironment(filename);
        FileManager.printEnvironment();
        Search go1 = new Search(environment1);
        Cell c = go1.bestFirstSearch3(1);
        System.out.println("Strategy 1: Euclidean Distance");
        //FileManager.printEnvironment();
        //System.out.println(c.position.X + ":" + c.position.Y);
        go1.createSolution2(c);
        FileManager.printEnvironment();

        Environment environment2 = FileManager.createEnvironment(filename);
        Search go2 = new Search(environment2);
        Cell c2 = go2.bestFirstSearch3(2);
        System.out.println("Strategy 2: Manhattan Distance");
        go2.createSolution2(c2);
        FileManager.printEnvironment();

        Environment environment3 = FileManager.createEnvironment(filename);
        Search go3 = new Search(environment3);
        Cell c3 = go3.bestFirstSearch3(3);
        System.out.println("Strategy 3: Euclidean Distance + Cost");
        go3.createSolution2(c3);
        FileManager.printEnvironment();

        Environment environment4 = FileManager.createEnvironment(filename);
        Search go4 = new Search(environment4);
        Cell c4 = go4.bestFirstSearch3(4);
        System.out.println("Strategy 4: Manhattan Distance + Cost");
        go4.createSolution2(c4);
        FileManager.printEnvironment();


    }
}
