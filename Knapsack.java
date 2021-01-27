/**************************************************************************
 *  Student Name: Himanshu Sehgal
 *  Student Number: 8688440
 *  Course: CSI 2120 Winter 2021
 *  Java Comprehensive Assignment 1: Knapsack Problem
 * 
 *  Description: The purpose of this class is to perform the preliminary tasks
 *               from read the file, to the brute force and dynamic programming
 *               methods. The methods are stored here and the class is 
 *               instantiated in the main method located in KnapsackProblem.java
 *
 *  References:
 *  Lines 82-90 were referenced from the following https://www.baeldung.com/java-knapsack
 * 
*************************************************************************/

import java.util.*;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Knapsack {

    //  List of global variables used throughout various methods.
    int numOfItems;
    int capacity;
    int maxValue;
    Item[] listOfItems;
    ArrayList<Integer> itemValues = new ArrayList<Integer>();
    ArrayList<String> nameOfItems = new ArrayList<String>();

    /**
     *  This method takes in the first argument passed through the 
     *  console as the filename.
     * @return void
     */
    public void readFile(String filename) throws Exception {
        
        //  Input text file content transferred into ArrayList for use
        ArrayList<String> fileElements = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
       
        // local testing
        
        // fileElements.add("4");
        // fileElements.add("A  1  1");
        // fileElements.add("B  6  2");
        // fileElements.add("C  10 3");
        // fileElements.add("D  15 5");
        // fileElements.add("7");

      
        // Obtain the number of items in the given text file.
        numOfItems = Integer.parseInt(fileElements.get(0));
        capacity = Integer.parseInt(fileElements.get(fileElements.size() - 1));

        listOfItems = new Item[numOfItems];

        int index = 0;
        for (int i = 1; i <= numOfItems; i++) {
            String temp = fileElements.get(i);
            String[] arrTemp = temp.trim().split("(?<=\\D)(?=\\d)");    //  Splits the string by delimiters

            //System.out.println(Arrays.toString(arrTemp));

            listOfItems[index] = new Item(arrTemp[0].replaceAll("\\s+",""),
                    Integer.parseInt(arrTemp[1].replaceAll("\\s+","")),
                    Integer.parseInt(arrTemp[2].replaceAll("\\s+","")));    //  Item objects are created for each item available

            index++;
        }

    }

    /** Brute Force Method
     *  This method takes in arguments for the number of items, the total
     *  available capacity and the array of Item objects from which the
     *  getters are used to retrieve the weight and value. It processes 
     *  the variables to output the maximum value the bag can contain.
     *  During the recurrsive call, the namesOfItems arrayList is populated
     *  and determines which items need to be placed in the bag.
     * @return maxValue
     */
    public int bruteForce(int numOfItems, int capacity, Item[] listOfItems) {
        if (numOfItems <= 0) {
            itemValues.clear();
            return 0;
            
        } else if (listOfItems[numOfItems - 1].getWeight() > capacity) {
            itemValues.add(numOfItems);
            return bruteForce(numOfItems - 1, capacity, listOfItems);
        } else {
            
            maxValue = Math.max(bruteForce(numOfItems - 1, capacity, listOfItems), 
                                       listOfItems[numOfItems - 1].getValue() + bruteForce(numOfItems - 1, 
                                       capacity - listOfItems[numOfItems - 1].getWeight(), 
                                       listOfItems));
            itemValues.add(numOfItems - 1);
            
        }
        
            for(int i = 0; i < itemValues.size(); i++){
                nameOfItems.add(listOfItems[itemValues.get(i)].getItemName());
            }
            return maxValue;
    }

    /** Dynamic Programming Method
     *  This method takes in arguments for the number of items, the total
     *  available capacity and the array of Item objects from which the
     *  getters are used to retrieve the weight and value. It processes 
     *  the variables to output the maximum value the bag can contain.
     *  Throughout the method the kTable is populated for every iteration
     *  of the item and the maximum weight. This determines the maximum 
     *  value the individual can carry while being under the weight limmit.
     *  The namesOfItems arraylist is populated to store the items that 
     *  need to be placed in the bag.
     * @return kTable[numOfItems][capacity]
     */
    public int dynamicProgramming(int numOfItems, int capacity, Item[] listOfItems){
        int highestValue;
        int colm;
        
        if(numOfItems <= 0 || capacity <= 0){
            return 0;
        }

        KTable table = new KTable(numOfItems + 1, capacity + 1);
        int[][] kTable = table.getTable();
        for(int i = 0; i <= capacity; i++ ){
            kTable[0][i] = 0;
        }

        for(int i = 1; i <= numOfItems; i++){
            for(int j = 0; j <= capacity; j++){
                if(listOfItems[i - 1].getWeight() > j){
                    kTable[i][j] = kTable[i - 1][j];
                }else {
                    kTable[i][j] = Math.max(kTable[i - 1][j], kTable[i - 1][j - listOfItems[i - 1].getWeight()] + listOfItems[i - 1].getValue());
                }
            }
        }

        highestValue = kTable[numOfItems][capacity];
        
        // Storing which items are chosen
        colm = capacity;
        for(int i = numOfItems; i > 0 && highestValue > 0; i--){
            if (highestValue == kTable[i - 1][colm]){
                continue;
            } else {
                nameOfItems.add(listOfItems[i - 1].getItemName());
                highestValue = highestValue - listOfItems[i - 1].getValue();
                colm = colm - listOfItems[i - 1].getWeight();
            }
        }
        return kTable[numOfItems][capacity];
    }

    /** Output File method
     *  This method takes in arguments for the total value of the items 
     *  obtained through either the brute force or dynamic programming 
     *  method. The first file name argument passed by the user is 
     *  split by delimiters to gather the name of the solution file.
     *  The desired outputs are written to the file. 
     * @return 
     */
    public void outputFile(int totalValue, ArrayList<String> nameOfItems, String filename) throws Exception {
        //An exception is placed if there is an error in the name of the output file
        String[] fnameContents = filename.split(".txt");
        String content = fnameContents[0];
        
        try (PrintWriter out = new PrintWriter(content + ".sol")) {
            out.println(totalValue);
            for(int i = 0; i < nameOfItems.size(); i++ ){
                out.print(nameOfItems.get(i) + " ");
                out.flush();
                }
            }catch (Exception e){
        
        } 
    }  
}
