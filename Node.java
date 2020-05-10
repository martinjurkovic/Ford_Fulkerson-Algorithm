import java.util.ArrayList;

class Node {
    int id;
    // marks for the algorithm
    // ------------------------------------
    boolean marked = false;
    boolean visited = false;
    Edge augmEdge = null; // the edge over which we brought the flow increase
    int incFlow = -1; // -1 means a potentially infinite flow
    String type = "";
    int delta = -1;
    // ------------------------------------
    ArrayList<Edge> inEdges;
    ArrayList<Edge> outEdges;

    public Node(int i) {
        id = i;
        inEdges = new ArrayList<Edge>();
        outEdges = new ArrayList<Edge>();
    }
}