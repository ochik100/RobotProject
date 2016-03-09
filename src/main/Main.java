package main;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];

        Environment environment1 = FileManager.createEnvironment(filename);
        System.out.println("Original Map:");
        FileManager.printEnvironment();
        Search go1 = new Search(environment1);
        Cell c1 = go1.bestFirstSearch(1);
        System.out.println("Strategy 1: Euclidean Distance");
        Double cost1 = go1.createSolution(c1, 1);
        System.out.println("Cost: " + String.format("%.2f", cost1));
        System.out.println("Nodes in Search Tree: " + go1.getVisitedSize());
        FileManager.printEnvironment();

        Environment environment2 = FileManager.createEnvironment(filename);
        Search go2 = new Search(environment2);
        Cell c2 = go2.bestFirstSearch(2);
        System.out.println("Strategy 2: Manhattan Distance");
        Double cost2 = go2.createSolution(c2, 2);
        System.out.println("Cost: " + String.format("%.2f", cost2));
        System.out.println("Nodes in Search Tree: " + go2.getVisitedSize());
        FileManager.printEnvironment();

        Environment environment3 = FileManager.createEnvironment(filename);
        Search go3 = new Search(environment3);
        Cell c3 = go3.bestFirstSearch(3);
        System.out.println("Strategy 3: Euclidean Distance + Cost");
        Double cost3 = go3.createSolution(c3, 3);
        System.out.println("Cost: " + String.format("%.2f", cost3));
        System.out.println("Nodes in Search Tree: " + go3.getVisitedSize());
        FileManager.printEnvironment();

        Environment environment4 = FileManager.createEnvironment(filename);
        Search go4 = new Search(environment4);
        Cell c4 = go4.bestFirstSearch(4);
        System.out.println("Strategy 4: Manhattan Distance + Cost");
        Double cost4 = go4.createSolution(c4, 4);
        System.out.println("Cost: " + String.format("%.2f", cost4));
        System.out.println("Nodes in Search Tree: " + go4.getVisitedSize());
        FileManager.printEnvironment();


    }
}
