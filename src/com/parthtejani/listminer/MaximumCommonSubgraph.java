package com.parthtejani.listminer;

/**
 * A class used for calculating the maximal common subgraph between two graphs
 */
public class MaximumCommonSubgraph {

    //the possible scenarios to watch for, TODO make enum
    //F is the new graph and G is the old graph to compare against
    public static final int NO_INTERSECTION = 1;
    public static final int EQUAL_INTERSECTION = 2;
    public static final int PARTIAL_INTERSECTION_EQUALS_G = 3;
    public static final int PARTIAL_INTERSECTION_EQUALS_F = 4;
    public static final int PARTIAL_INTERSECTION_EQUALS_NONE = 5;

    //buffer for holding common elements between two graphs
    public static int[] commonElements = new int[0];

    /**
     * Perform maximal common subgraph computation
     * 
     * <p>F = new graph, G = graph currently in list
     * <br>
     * <br>1) F AND G = NULL                flush g, and the rest of the descriptors out, add F, then exit
     * <br>2) F AND G = F = G               update all the descriptors, do NOT add F, then exit
     * <br>3) F AND G = G                   update all the descriptors, add F, then exit
     * <br>4) F AND G = F                   flush g, do NOT add F, repeat MCS with other descriptors with intersected result
     * <br>5) F AND G = H != (F OR G)       flush g, add F, repeat MCS with other descriptors with intersected result
     * 
     * @param f represents graph F
     * @param g represents graph G
     * @return MCSResult object that contains intersected data and scenario id
     */
    public static Result compute(Graph f, Graph g) {
        int[] set1 = f.elements;
        int[] set2 = g.elements;

        //empty graphs are not recorded, so send a signal to flush everything
        if (set1 == null || set2 == null) {
            return new Result(MaximumCommonSubgraph.NO_INTERSECTION, null);
        }

        //make sure buffer is able to hold all the elements
        int maxSize = Math.max(set1.length, set2.length);
        if (commonElements.length < maxSize) {
            int[] buffer = new int[maxSize];
            commonElements = buffer;
        }

        //counters for set1, set2, and commonElements
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        while (c1 < set1.length && c2 < set2.length) {
            if (set1[c1] == set2[c2]) {                 //set1 and set2 have the same element
                commonElements[c3] = set1[c1];
                c1++;
                c2++;
                c3++;
            } else if (set1[c1] < set2[c2]) {           //set1 has an extra element not in set2
                c1++;
            } else {                                    //set2 has an extra element not in set1
                c2++;
            }
        }

        int[] intersectedData = new int[c3];
        System.arraycopy(commonElements, 0, intersectedData, 0, c3);

        if (c3 == 0) {
            return new Result(NO_INTERSECTION, null);
        }

        if (c3 == set1.length && c3 == set2.length) {
            return new Result(EQUAL_INTERSECTION, intersectedData);
        }

        if (c3 < set1.length && c3 == set2.length) {
            return new Result(PARTIAL_INTERSECTION_EQUALS_G, intersectedData);
        }

        if (c3 < set2.length && c3 == set1.length) {
            return new Result(PARTIAL_INTERSECTION_EQUALS_F, intersectedData);
        }

        //only remaining scenario, where c3 is not 0, but not set1/set2
        //        if (c3 > 0 && c3 < set1.length && c3 < set2.length) {
        return new Result(PARTIAL_INTERSECTION_EQUALS_NONE, intersectedData);
        //        }

    }

    public static class Result {

        public final int scenario;
        public final int[] elements;

        public Result(int scenario, int[] elements) {
            this.scenario = scenario;
            this.elements = elements;
        }

    }

}
