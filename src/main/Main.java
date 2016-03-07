package main;

import com.sun.tools.doclint.Env;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];

        Environment environment1 = FileManager.createEnvironment(filename);
        FileManager.printEnvironment();
        Search go1 = new Search(environment1);
        go1.bestFirstSearch2(1);
        System.out.println("Strategy 1: Euclidean Distance");
        FileManager.printEnvironment();

        Environment environment2 = FileManager.createEnvironment(filename);
        Search go2 = new Search(environment2);
        go2.bestFirstSearch2(2);
        System.out.println("Strategy 2: Manhattan Distance");
        FileManager.printEnvironment();

        Environment environment3 = FileManager.createEnvironment(filename);
        Search go3 = new Search(environment3);
        go3.bestFirstSearch2(3);
        System.out.println("Strategy 3: Euclidean Distance + Cost");
        FileManager.printEnvironment();

        Environment environment4 = FileManager.createEnvironment(filename);
        Search go4 = new Search(environment4);
        go4.bestFirstSearch2(4);
        System.out.println("Strategy 4: Manhattan Distance + Cost");
        FileManager.printEnvironment();


    }
}
