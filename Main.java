import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stV = sc.nextInt();
        Network graf = new Network(stV);

        while (sc.hasNextInt())
            graf.addConnection(sc.nextInt(), sc.nextInt(), sc.nextInt());

        sc.close();
        graf.FordFulkerson(graf.nodes[0], graf.nodes[stV - 1]);
    }
}
