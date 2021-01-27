/**************************************************************************
 * Student Name: Himanshu Sehgal 
 * Student Number: 8688440 
 * Course: CSI 2120 Winter 2021 
 * Java Comprehensive Assignment 1: Knapsack Problem
 * 
 * Description: The purpose of this class is to form the structure of the
 * desired kTable used for Dynamic Programming.
 *************************************************************************/

public class KTable {

    int[][] kTable;

    /**
     * The following is the constructor for the kTable with the set parameters
     * 
     */
    public KTable(int numOfItems, int capacity) {
        kTable = new int[numOfItems][capacity];
    }

    /**
     * The following is the getter for the kTable as a two-dimensional array.
     * 
     * @return value
     */
    public int[][] getTable() {
        return kTable;
    }

}
