package com.parthtejani.listminer;

/**
 * A descriptor represents a graph with a given start time and the number of occurences
 * found so far.
 */
public class Descriptor {

    public final Graph graph;
    public final int startTime;
    private int count;

    public Descriptor(Graph graph, int count, int startTime) {
        this.graph = graph;
        this.count = count;
        this.startTime = startTime;
    }

    public Descriptor(Graph graph, Descriptor descriptor) {
        this.graph = graph;
        count = descriptor.count+1;
        startTime = descriptor.startTime;
    }

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

}
