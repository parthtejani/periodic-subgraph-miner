package com.parthtejani.listminer;

/**
 * Represents a graph in a serialized format where each vertex or edge is
 * represented as an integer: vertex "A" is mapped to 0, vertex "B" is mapped to 1,
 * and edge "A-B" is mapped as 2.
 */
public class Graph {

    public final int[] elements;

    /**
     * Creates a graph from a line containing data
     * 
     * @param line String with an initial timestamp token and the rest being
     * ordered integers separated by whitespace
     */
    public Graph(String line) {
        //split on whitespace
        String[] tokens = line.split("\\s+");
        if (tokens.length == 1 && tokens[0].equals("")) {
            elements = null;
        } else {
            elements = new int[tokens.length-1];
            //first token is timestamp so skip it
            for (int i = 0; i < tokens.length-1; i++) {
                elements[i] = Integer.parseInt(tokens[i+1]);
            }

        }
    }

    /**
     * Creates a graph from the intersection of other graphs
     * 
     * @param elements Intersected data
     */
    public Graph(int[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < elements.length-1; i++) {
            sb.append(elements[i] + " ");
        }
        if (elements.length > 0) {
            sb.append(elements[elements.length-1]);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Checks if a graph contains all the elements of another graph
     * 
     * @param graph Graph to compare against
     * @return True if the graph contains all the elements of the other graph
     */
    public boolean contains(Graph graph) {
        int[] set1 = elements;
        int[] set2 = graph.elements;

        //quick check
        if (set2.length > set1.length) {
            return false;
        }

        //counters
        int c1 = 0;
        int c2 = 0;

        while (c1 < set1.length && c2 < set2.length) {
            if (set1[c1] == set2[c2]) {                 //set1 and set2 have the same element
                c1++;
                c2++;
            } else if (set1[c1] < set2[c2]) {           //set1 has an extra element not in set2
                c1++;
            } else {                                    //set2 has an extra element not in set1
                return false;
            }
        }

        return true;
    }

}
