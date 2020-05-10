class Network {
    Node[] nodes;

    /**
     * Create a new network with n nodes (0..n-1).
     * 
     * @param n the size of the network.
     */
    public Network(int n) {
        nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
    }

    /**
     * Add a connection to the network, with all the corresponding in and out edges.
     * 
     * @param fromNode
     * @param toNode
     * @param capacity
     */
    public void addConnection(int fromNode, int toNode, int capacity) {
        // System.out.printf("%d %d %d\n", fromNode, toNode, capacity);
        Edge e = new Edge(fromNode, toNode, capacity);
        nodes[fromNode].outEdges.add(e);
        nodes[toNode].inEdges.add(e);
    }

    /*
     * * Reset all the marks of the algorithm, before the start of a new iteration.
     */
    private void resetMarks() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].marked = false;
            nodes[i].augmEdge = null;
            nodes[i].incFlow = -1;
            nodes[i].visited = false;
            nodes[i].type = "";
            nodes[i].delta = -1;
        }
    }


    private int minFlow(int a, int b) {
        if (a == -1)
            return b;
        if (b == -1)
            return a;
        return Math.min(a, b);
    }

    private Node findMarkedUnvisited() {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].marked && !nodes[i].visited) {
                return nodes[i];
            }
        }
        return null;
    }

    private void markPlus(Node tempV) {
        for (Edge tempE: tempV.outEdges) {
            if (!nodes[tempE.endID].marked && tempE.currFlow < tempE.capacity) {
                nodes[tempE.endID].marked = true;
                nodes[tempE.endID].delta = tempV.id;
                nodes[tempE.endID].type = "+";
                nodes[tempE.endID].incFlow = minFlow(tempV.incFlow, tempE.capacity - tempE.currFlow);
                nodes[tempE.endID].augmEdge = tempE;
            }
        }
    }


    private void markMinus(Node tempV) {
        for (Edge tempE: tempV.inEdges) {
            if (!nodes[tempE.startID].marked && tempE.currFlow > 0) {
                nodes[tempE.startID].marked = true;
                nodes[tempE.startID].delta = tempV.id;
                nodes[tempE.startID].type = "-";
                nodes[tempE.startID].incFlow = minFlow(tempV.incFlow, tempE.currFlow);
                nodes[tempE.startID].augmEdge = tempE;
            }
        }
    }

    private void increaseFlow(Node end) {
        Node temp = end;
        int flow = end.incFlow;
        while (temp.delta != -1) {
            if (temp.type.equals("+"))
                temp.augmEdge.currFlow += flow;
            else
                temp.augmEdge.currFlow -= flow;
            temp = nodes[temp.delta];
        }
    }

    private boolean findZasicena(Node start, Node end) {
        resetMarks();
        start.marked = true;
        Node temp = findMarkedUnvisited();
        while (temp != null) {
            temp.visited = true;
            markPlus(temp);
            markMinus(temp);
            if (end.marked) {
                return true;
            }
            temp = findMarkedUnvisited();
        }
        return false;
    }

    /**
     * Ford-Fulkerson
     */
    public Network FordFulkerson(Node start, Node end) {
        while (findZasicena(start, end)) {
            increaseFlow(end);
            // System.out.println("IZHOD");
            izpisiPot(end);
        }
        return this;
    }

    private void izpisiPot(Node end) {
        System.out.printf("%d: ", end.incFlow);
        Node temp = end;
        while (temp.delta != -1) {
            if (temp.type.equals("+"))
                System.out.printf("%d%s  ", temp.id, temp.type);
            else
                System.out.printf("%d%s ", temp.id, temp.type);
            temp = nodes[temp.delta];
        }
        System.out.printf("%d\n", temp.id);
    }
}