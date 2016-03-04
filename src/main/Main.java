package main;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];
        Environment environment = FileManager.createEnvironment(filename);
        Search go = new Search(environment);
        go.bestFirstSearchManhattan();
        FileManager.printEnvironment();
    }
}
