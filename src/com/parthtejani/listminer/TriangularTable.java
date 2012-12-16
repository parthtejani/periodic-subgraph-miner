package com.parthtejani.listminer;

/**
 * Represents a triangular 2d table
 *      0   1   2   3   4
 * 0    0   0   0   0   0
 * 1    x   0   0   0   0
 * 2    x   x   0   0   0
 * 3    x   x   x   0   0
 * 4    x   x   x   x   0
 * 
 * x represents available positions, 0 represents invalid positions for an element
 * 
 * The number of columns in a given row is equal to the row index.
 * 
 * @param <E> The element type of this 2d table
 */
public class TriangularTable<E> {

    private E[][] data;
    private int numRows;

    @SuppressWarnings("unchecked")
    public TriangularTable() {
        data = (E[][]) new Object[1][];
    }

    @SuppressWarnings("unchecked")
    public void addRow() {
        if (numRows >= data.length) {
            int newSize = data.length * 2;
            E[][] newData = (E[][]) new Object[newSize][];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;

        }
        data[numRows] = (E[]) new Object[numRows];
        numRows++;
    }

    public void set(int row, int col, E object) {
        data[row][col] = object;
    }

    public E get(int row, int col) {
        return data[row][col];
    }

    public int numRows() {
        return numRows;
    }

    public int numCols(int row) {
        return row;
    }

}
