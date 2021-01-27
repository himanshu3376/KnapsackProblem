/**************************************************************************
 *  Student Name: Himanshu Sehgal
 *  Student Number: 8688440
 *  Course: CSI 2120 Winter 2021
 *  Java Comprehensive Assignment 1: Knapsack Problem
 * 
 *  Description: The purpose of this class is to form the structure of the
 *               items that are to be placed in the knapsack. The Item 
 *               objects are used for both the Brute Force Programming and
 *               Dynamic Programming with an emphasis on a Object-Oriented
 *               solution.
 *************************************************************************/

public class Item {
    
    
    String itemName;
    int value;
    int weight;
    
    /**
     * The following is the getter for the item name (ie. A, B, C, D)
     * @return itemName
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * The following is the getter for the item value (ie. 1, 6, 10, 15)
     * @return value
     */
    public int getValue(){
        return value;
    }

    /**
     * The following is the getter for the item weight (ie. 1, 2, 3, 5)
     * @return weight
     */
    public int getWeight(){
        return weight;
    }

    /**
     * The following is the toString method to format the placement of 
     * the result when it is referenced
     * @return 
     */
    public String toString() {
        return "[" + itemName + ", " + value + ", " + weight + "]";
    }

    /**
     * The following is the constructor for the Item class.
     * 
     */
    public Item(String itemName, int value, int weight){
        this.itemName = itemName;
        this.value = value;
        this.weight = weight;
    }

}
