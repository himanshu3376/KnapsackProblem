/**************************************************************************
 *  Student Name: Himanshu Sehgal
 *  Student Number: 8688440
 *  Course: CSI 2120 Winter 2021
 *  Java Comprehensive Assignment 1: Knapsack Problem
 * 
 *  Description: The purpose of this class is to gather all revelant data
 *               and pass it to the respective classes and method to acheive
 *               the results of either the brute force or dynamic programming
 *               methods.
 *************************************************************************/

public class KnapsackProblem {

    /**
     *  This is the main method which instantiated the Item class and the
     *  Knapsack class to access non-static methods. Throughout all files,
     *  the main method is the only static method as required by the outline.
     *  The arguments passed by the user are interpolated and passed respectfully,
     *  the args[0] is the file name necessary for the readFile() method, whereas 
     *  the args[1] checks whether the user has requested for the brute force or 
     *  dynamic programming method. Depending on the requested solution, the
     *  output file generating method is called and the necessary arguments are
     *  passed. If the user enters any argument other than "F" or "D", the user
     *  is notified via the console.
     * @return void
     */

    public static void main(String args[]) throws Exception {

        /*
         * Number of items : int Value of items : array[int] Weight of item : array[int]
         * Total capacity : int
         */

        int totalValueBF;
        int totalValueDP;
        boolean DP = true;

        Knapsack newSack = new Knapsack();
        newSack.readFile(args[0]);

        if (args[1].equals("F")){
            DP = false;
        }

        if(DP == false){
            //Brute Force
            totalValueBF = newSack.bruteForce(newSack.numOfItems, newSack.capacity, newSack.listOfItems);
            newSack.outputFile(totalValueBF, newSack.nameOfItems, args[0]);
            
        } else if (DP == true ){
            //Dynamic Programming
            totalValueDP = newSack.dynamicProgramming(newSack.numOfItems, newSack.capacity, newSack.listOfItems);
            newSack.outputFile(totalValueDP, newSack.nameOfItems, args[0]);

            } else {
                System.out.println("Incorrect programming type, please try again.");
            }
        }

}
