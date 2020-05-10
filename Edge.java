class Edge {
    int startID;
    int endID;
    int capacity;
    int currFlow;

    public Edge(int fromNode, int toNode, int capacity2) {
        startID = fromNode;
        endID = toNode;
        capacity = capacity2;
        currFlow = 0;
    }

    public String toString() {
        return String.format("startID: %d, endID: %d, currflow: %d, capacity %d", startID, endID, currFlow, capacity);
    }
}